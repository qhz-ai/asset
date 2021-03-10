<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="代码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['sys:chart:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['sys:chart:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['sys:chart:remove']"
        >删除</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="chartList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="代码" align="center" prop="code" />
      <el-table-column label="名称" align="center" prop="title" />
      <el-table-column label="数据集" align="center" prop="dsName" />
      <el-table-column label="类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="排序" align="center" prop="listSort" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" width="220" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sys:chart:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sys:chart:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-unfold"
            @click="handleMenu(scope.row)"
            v-hasPermi="['sys:chart:add']"
          >加入菜单</el-button>&nbsp;
          <router-link :to="'/sys/chart/view/' + scope.row.id" class="link-type">
             &nbsp;<i class="el-icon-view"></i>预览
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改报表管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="代码" prop="code">
          <el-input v-model="form.code" placeholder="请输入代码" />
        </el-form-item>
        <el-form-item label="名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="数据集" prop="dsId">
          <el-select v-model="form.dsId" filterable placeholder="请选择数据集">
            <el-option
              v-for="d in dsList"
              :key="d.id"
              :label="d.title"
              :value="d.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="横向坐标" prop="xaxis">
          <el-input v-model="form.xaxis" placeholder="字段=标题" />
        </el-form-item>
        <el-form-item label="纵向坐标" prop="yaxis">
          <el-input v-model="form.yaxis" placeholder="字段=标题" />
        </el-form-item>
        <el-form-item label="说明" prop="comment">
          <el-input v-model="form.comment" type="textarea" placeholder="请输入说明" />
        </el-form-item>
        <el-form-item label="排序" prop="listSort">
          <el-input v-model="form.listSort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listChart, getChart, delChart, addChart, updateChart, exportChart, menuChart } from "@/api/sys/chart";
import { listDataset} from "@/api/sys/dataset";

export default {
  name: "Chart",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 数据集表格数据
      dsList: [],
      // 报表管理表格数据
      chartList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类型字典
      typeOptions: [],
      // 状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        code: null,
        title: null,
        type: null,
        status: null,
        orderByColumn:"id",
        isAsc:"DESC"
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        code: [
          { required: true, message: "代码不能为空", trigger: "blur" }
        ],
        title: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        dsId: [
          { required: true, message: "数据源不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "类型不能为空", trigger: "change" }
        ],
        listSort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("chart_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("ext_status").then(response => {
      this.statusOptions = response.data;
    });
    listDataset().then(response => {
      this.dsList = response.rows;
    });
  },
  methods: {
    /** 查询报表管理列表 */
    getList() {
      this.loading = true;
      listChart(this.queryParams).then(response => {
        this.chartList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      listDataset().then(response => {
        this.dsList = response.rows;
      });
      this.form = {
        id: null,
        code: null,
        title: null,
        dsId: null,
        type: null,
        xaxis: null,
        yaxis: null,
        comment: null,
        listSort: null,
        status: "1"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加报表管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getChart(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改报表管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateChart(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addChart(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除报表管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delChart(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    handleMenu(row){
      const ids = row.id || this.ids;
      this.$confirm('是否确认将选中的报表加入到菜单项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return menuChart(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("加入菜单成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有报表管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportChart(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资产分类" prop="cateId">
         <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
      </el-form-item>
      <el-form-item label="审核结果" prop="checkStatus">
        <el-select v-model="queryParams.checkStatus" placeholder="请选择审核结果" clearable size="small">
          <el-option
            v-for="dict in checkStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="使用时间" prop="startTime">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="是否归还" prop="isRevert">
        <el-input
          v-model="queryParams.isRevert"
          placeholder="请输入是否归还"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['asset:use:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="useList">
      <el-table-column label="申请分类" align="center" prop="cate.title" />
      <el-table-column label="分配资产名" align="center" prop="asset.name" />
      <el-table-column label="分配资产编号" align="center" prop="asset.num" />
      <el-table-column label="使用时间" align="center" prop="startTime" width="200">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }} 至 {{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" prop="checkUser.nickName" />
      <el-table-column label="审核结果" align="center" prop="checkStatus" :formatter="checkStatusFormat" />
      <el-table-column label="审核时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否归还" align="center" prop="isRevert" :formatter="isRevertFormat"/>
      <el-table-column label="归还时间" align="center" prop="revertTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.revertTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.checkStatus != '1'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:use:edit']"
          >修改</el-button>
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

    <!-- 添加或修改使用申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资产分类" prop="cateId">
           <treeselect v-model="form.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
        </el-form-item>
        <el-form-item label="参数要求" prop="param">
          <el-input v-model="form.param" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="申请原由" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入申请原由" />
        </el-form-item>
        <el-form-item label="申请说明" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入申请说明" />
        </el-form-item>
        <el-form-item label="使用时间" prop="startTime">
          <el-date-picker clearable size="small"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="归还时间" prop="finishTime">
          <el-date-picker clearable size="small"
            v-model="form.finishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择结束时间">
          </el-date-picker>
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
import { listMyUse, getUse, delUse, addUse, updateUse, exportUse } from "@/api/asset/use";
import { listCategory } from "@/api/asset/category";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "MyUse",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 使用申请表格数据
      useList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 审核结果字典
      checkStatusOptions: [],
      // 资产分类树选项
      categoryOptions: [],
      // 是否字典
      yesnoOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cateId: null,
        checkUserId: null,
        checkStatus: null,
        startTime: null,
        finishTime: null,
        assetId: null,
        isRevert: null,
        createBy: null,
        orderByColumn:"t.id",
        isAsc:"DESC"
      },
      // 表单参数
      form: {},
      dateRange: [],
      // 表单校验
      rules: {
        cateId: [
          { required: true, message: "资产分类不能为空", trigger: "blur" }
        ],
        param: [
          { required: true, message: "参数要求不能为空", trigger: "blur" }
        ],
        reason: [
          { required: true, message: "申请原由不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("check_status").then(response => {
      this.checkStatusOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.yesnoOptions = response.data;
    });
    this.getTreeselect();
  },
  methods: {
    /** 查询使用申请列表 */
    getList() {
      this.loading = true;
      listMyUse(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.useList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 审核结果字典翻译
    checkStatusFormat(row, column) {
      return this.selectDictLabel(this.checkStatusOptions, row.checkStatus);
    },
    // 是否完成字典翻译
    isRevertFormat(row, column) {
      return this.selectDictLabel(this.yesnoOptions, row.isRevert);
    },
    /** 转换资产分类数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.title,
        children: node.children
      };
    },
    /** 查询部门下拉树结构 */
    getTreeselect() {
      listCategory({type:'1'}).then(response => {
        this.categoryOptions = [];
        const data = { id: 0, title: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "id", "pid");
        this.categoryOptions.push(data);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        cateId: null,
        param: null,
        reason: null,
        comment: null,
        checkUserId: null,
        checkStatus: "0",
        checkTime: null,
        checkReason: null,
        startTime: null,
        finishTime: null,
        assetId: null,
        isRevert: null,
        isDelivery: 'N',
        revertTime: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加使用申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUse(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改使用申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUse(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUse(this.form).then(response => {
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
      this.$confirm('是否确认删除使用申请编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delUse(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有使用申请数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportUse(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

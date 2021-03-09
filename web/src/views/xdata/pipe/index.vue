<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="应用" prop="clientId">
        <el-select v-model="queryParams.clientId" placeholder="请选择应用" filterable clearable size="small">
          <el-option
            v-for="dict in clientList"
            :key="dict.id"
            :label="dict.title"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="数据模型" prop="modelId">
        <el-select v-model="queryParams.modelId" placeholder="请选择数据模型" filterable clearable size="small">
          <el-option
            v-for="dict in modelList"
            :key="dict.id"
            :label="dict.title"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="权限" prop="mode">
        <el-select v-model="queryParams.mode" placeholder="请选择权限" clearable size="small">
          <el-option
            v-for="dict in modeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="同步时间" prop="lastTime">
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
          v-hasPermi="['xdata:pipe:add']"
        >新增</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pipeList" @selection-change="handleSelectionChange">
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="应用" align="center" prop="client.title" />
      <el-table-column label="模型名称" align="center" prop="model.title" />
      <el-table-column label="权限" align="center" prop="mode" :formatter="modeFormat" />
      <el-table-column label="最后同步时间" align="center" prop="lastTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后同步结果" align="center" prop="lastResult" />
      <el-table-column label="优先级" align="center" prop="listSort" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['xdata:pipe:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['xdata:pipe:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleRun(scope.row)"
            v-hasPermi="['xdata:pipe:add']"
          >执行</el-button>
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

    <!-- 添加或修改通道对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
       <el-tabs type="card" >
        <el-tab-pane label="基本信息">
         <el-form-item label="应用" prop="clientId">
           <el-select v-model="form.clientId" placeholder="请选择应用" filterable clearable size="small">
             <el-option
               v-for="dict in clientList"
               :key="dict.id"
               :label="dict.title"
               :value="dict.id"
             />
           </el-select>
         </el-form-item>
         <el-form-item label="数据模型" prop="modelId">
           <el-select v-model="form.modelId" placeholder="请选择数据模型" filterable clearable size="small">
             <el-option
               v-for="dict in modelList"
               :key="dict.id"
               :label="dict.title"
               :value="dict.id"
             />
           </el-select>
         </el-form-item>
          <el-form-item label="权限" prop="mode">
            <el-radio-group v-model="form.mode">
              <el-radio
                v-for="dict in modeOptions"
                :key="dict.dictValue"
                :label="dict.dictValue"
              >{{dict.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="优先级" prop="listSort">
            <el-input v-model="form.listSort" placeholder="请输入优先级" />
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
        </el-tab-pane>
        <el-tab-pane label="数据操作">
          <el-form-item label="读取操作" prop="readSql">
            <el-input type="textarea" v-model="form.readSql" placeholder="请输入读取操作" />
          </el-form-item>
          <el-form-item label="新增操作" prop="insertSql">
            <el-input type="textarea" v-model="form.insertSql" placeholder="请输入新增操作SQL" />
          </el-form-item>
          <el-form-item label="更新操作" prop="updateSql">
            <el-input type="textarea" v-model="form.updateSql" placeholder="请输入更新操作SQL" />
          </el-form-item>
          <el-form-item label="删除操作" prop="delSql" v-if="deleted">
            <el-input type="textarea" v-model="form.delSql" placeholder="请输入删除操作SQL" />
          </el-form-item>
          <el-form-item label="验证操作" prop="existSql">
            <el-input type="textarea" v-model="form.existSql" placeholder="请输入验证操作SQL" />
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPipe, getPipe, delPipe, addPipe, updatePipe, runPipe, exportPipe } from "@/api/xdata/pipe";
import { selectClient } from "@/api/xdata/client";
import { selectModel } from "@/api/xdata/model";

export default {
  name: "Pipe",
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
      // 通道表格数据
      pipeList: [],
      // 模型表格数据
      modelList: [],
      // 应用表格数据
      clientList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      deleted: false,
      // 权限字典
      modeOptions: [],
      // 状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clientId: null,
        modelId: null,
        mode: null,
        lastTime: null,
        lastResult: null,
        dbPass: null,
        listSort: null,
        status: null
      },
      // 表单参数
      form: {},
      // 日期范围
      dateRange: [],
      // 表单校验
      rules: {
        clientId: [
          { required: true, message: "应用不能为空", trigger: "blur" }
        ],
        modelId: [
          { required: true, message: "模型名称不能为空", trigger: "blur" }
        ],
        mode: [
          { required: true, message: "权限不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("xdata_pipe_mode").then(response => {
      this.modeOptions = response.data;
    });
    this.getDicts("ext_status").then(response => {
      this.statusOptions = response.data;
    });
    selectModel(this.queryParams).then(response => {
      this.modelList = response.rows;
    });
    selectClient(this.queryParams).then(response => {
      this.clientList = response.rows;
    });
  },
  methods: {
    /** 查询通道列表 */
    getList() {
      this.loading = true;
      listPipe(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.pipeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 权限字典翻译
    modeFormat(row, column) {
      return this.selectDictLabel(this.modeOptions, row.mode);
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
      this.form = {
        id: null,
        clientId: null,
        modelId: null,
        mode: null,
        readSql: null,
        writeSql: null,
        delSql: null,
        existSql: null,
        lastTime: null,
        lastResult: null,
        listSort: 1,
        status: "0"
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
      this.title = "添加通道";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPipe(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改通道";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePipe(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPipe(this.form).then(response => {
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
      this.$confirm('是否确认删除通道编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPipe(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 执行按钮操作 */
    handleRun(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认执行一次选中的数据通道?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return runPipe(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("操作成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有通道数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportPipe(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

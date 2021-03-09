<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资产分类" prop="cateId">
         <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
      </el-form-item>
      <el-form-item label="资产编号" prop="assetId">
        <el-input
          v-model="queryParams.assetId"
          placeholder="请输入资产ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维护类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择维护类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="负责人" prop="masterUserId">
        <el-input
          v-model="queryParams.masterUserId"
          placeholder="请输入负责人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维护时间" prop="startTime">
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
      <el-form-item label="是否完成" prop="isComplete">
        <el-select v-model="queryParams.isComplete" placeholder="请选择是否完成" clearable size="small">
          <el-option
            v-for="dict in isCompleteOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="维修结果" prop="result">
        <el-select v-model="queryParams.result" placeholder="请选择维修结果" clearable size="small">
          <el-option
            v-for="dict in resultOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="maintainList">
      <el-table-column label="资产分类" align="center" prop="cate.title" />
      <el-table-column label="资产名称" align="center" prop="asset.name" />
      <el-table-column label="资产编号" align="center" prop="asset.num" />
      <el-table-column label="维护类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="维护原由" align="center" prop="reason" />
      <el-table-column label="审核人" align="center" prop="checkUser.nickName" />
      <el-table-column label="审核结果" align="center" prop="checkStatus" :formatter="checkStatusFormat" />
      <el-table-column label="审核时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="不通过原因" align="center" prop="checkReason" />
      <el-table-column label="负责人" align="center" prop="masterUser.nickName" />
      <el-table-column label="维修时间" align="center" prop="startTime" width="200">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }} 至 {{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否完成" align="center" prop="isComplete" :formatter="isCompleteFormat" />
      <el-table-column label="维修结果" align="center" prop="result" :formatter="resultFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.isComplete != 'Y'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:maintain:edit']"
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

    <!-- 添加或修改资产维护对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
         <el-tabs type="card" >
          <el-tab-pane label="基本信息">
        <el-form-item label="资产信息" prop="assetId">
          <span v-if="form.asset">{{ form.asset.num }}</span>
        </el-form-item>
        <el-form-item label="维护类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择维护类型">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="维修说明">
          <editor v-model="form.comment" :min-height="192"/>
        </el-form-item>
        <el-form-item label="是否完成" prop="isComplete">
          <el-select v-model="form.isComplete" placeholder="请选择是否完成">
            <el-option
              v-for="dict in isCompleteOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="维修结果" prop="result">
          <el-select v-model="form.result" placeholder="请选择维修结果">
            <el-option
              v-for="dict in resultOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="详细信息">
            <el-form-item label="资产分类" prop="cateId">
               <treeselect v-model="form.cateId" :options="categoryOptions" @input="remoteAsset" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
            </el-form-item>
            <el-form-item label="维护原由" prop="reason">
              <el-input v-model="form.reason" placeholder="请输入维护原由" disabled/>
            </el-form-item>
            <el-form-item label="申请人" prop="checkUserId">
              <span v-if="form.applyUser">{{ form.applyUser.nickName }}</span>
            </el-form-item>
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker clearable size="small"
                v-model="form.startTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择开始时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="完成时间" prop="finishTime">
              <el-date-picker clearable size="small"
                v-model="form.finishTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择完成时间">
              </el-date-picker>
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
import { listMaintainTask, getMaintain, delMaintain, addMaintain, updateMaintain, exportMaintain } from "@/api/asset/maintain";
import { listInfo } from "@/api/asset/info";
import { listCategory } from "@/api/asset/category";
import Editor from '@/components/Editor';
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "MaintainTask",
  components: {
    Editor,Treeselect
  },
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
      // 资产维护表格数据
      maintainList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 维护类型字典
      typeOptions: [],
      // 审核结果字典
      checkStatusOptions: [],
      // 是否完成字典
      isCompleteOptions: [],
      // 维修结果字典
      resultOptions: [],
      // 资产分类树选项
      categoryOptions: [],
      assetList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cateId: null,
        assetId: null,
        type: null,
        reason: null,
        checkUserId: null,
        masterUserId: null,
        startTime: null,
        checkStatus: null,
        finishTime: null,
        checkTime: null,
        checkReason: null,
        isComplete: null,
        comment: null,
        result: null,
      },
      // 表单参数
      form: {},
      dateRange: [],
      // 表单校验
      rules: {
        cateId: [
          { required: true, message: "资产分类不能为空", trigger: "blur" }
        ],
        reason: [
          { required: true, message: "维护原由不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateBy: [
          { required: true, message: "更新人不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("maintain_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("check_status").then(response => {
      this.checkStatusOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.isCompleteOptions = response.data;
    });
    this.getDicts("maintain_result").then(response => {
      this.resultOptions = response.data;
    });
    this.getTreeselect();
  },
  methods: {
    /** 查询资产维护列表 */
    getList() {
      this.loading = true;
      listMaintainTask(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.maintainList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 维护类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 审核结果字典翻译
    checkStatusFormat(row, column) {
      return this.selectDictLabel(this.checkStatusOptions, row.checkStatus);
    },
    // 是否完成字典翻译
    isCompleteFormat(row, column) {
      return this.selectDictLabel(this.isCompleteOptions, row.isComplete);
    },
    // 维修结果字典翻译
    resultFormat(row, column) {
      return this.selectDictLabel(this.resultOptions, row.result);
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
    remoteAsset() {
      listInfo({cateId:this.form.cateId}).then(response => {
        this.assetList = response.rows;
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
        assetId: null,
        type: null,
        reason: null,
        checkUserId: null,
        masterUserId: null,
        startTime: null,
        checkStatus: "0",
        finishTime: null,
        checkTime: null,
        checkReason: null,
        createBy: null,
        isComplete: null,
        comment: null,
        result: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.title = "添加资产维护";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMaintain(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资产维护";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMaintain(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaintain(this.form).then(response => {
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
      this.$confirm('是否确认删除资产维护编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMaintain(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有资产维护数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMaintain(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

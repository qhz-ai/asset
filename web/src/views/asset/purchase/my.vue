<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资产分类" prop="cateId">
        <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类" style="width:240px"/>
      </el-form-item>
      <el-form-item label="产品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入产品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
        <el-input
          v-model="queryParams.isComplete"
          placeholder="请输入是否完成"
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
          v-hasPermi="['asset:purchase:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="purchaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="资产分类" align="center" prop="cate.title" />
      <el-table-column label="产品名称" align="center" prop="name" />
      <el-table-column label="厂商" align="center" prop="factory" />
      <el-table-column label="购买渠道" align="center" prop="proxy" />
      <el-table-column label="购买数量" align="center" prop="amount" />
      <el-table-column label="市场价格" align="center" prop="price1" />
      <el-table-column label="购买价格" align="center" prop="price2" />
      <el-table-column label="审核人" align="center" prop="checkUser.nickName" />
      <el-table-column label="审核结果" align="center" prop="checkStatus" :formatter="checkStatusFormat" />
      <el-table-column label="采购负责人" align="center" prop="masterUser.nickName" />
      <el-table-column label="完成时间" align="center" prop="finishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否完成" align="center" prop="isComplete" :formatter="checkCompleteFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.checkStatus != '1'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:purchase:edit']"
          >修改</el-button>
          <el-button v-if="scope.row.checkStatus != '1'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:purchase:remove']"
          >删除</el-button>
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

    <!-- 添加或修改采购申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资产分类" prop="cateId">
          <treeselect v-model="form.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类" />
        </el-form-item>
        <el-form-item label="产品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="参数要求" prop="param">
          <el-input v-model="form.param" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="厂商" prop="factory">
          <el-input v-model="form.factory" placeholder="请输入厂商" />
        </el-form-item>
        <el-form-item label="购买渠道" prop="proxy">
          <el-input v-model="form.proxy" placeholder="请输入购买渠道" />
        </el-form-item>
        <el-form-item label="购买数量" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入购买数量" />
        </el-form-item>
        <el-form-item label="市场价格" prop="price1">
          <el-input v-model="form.price1" placeholder="请输入市场价格" />
        </el-form-item>
        <el-form-item label="购买价格" prop="price2">
          <el-input v-model="form.price2" placeholder="请输入购买价格" />
        </el-form-item>
        <el-form-item label="申请原由" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入申请原由" />
        </el-form-item>
        <el-form-item label="申请说明" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入申请说明" />
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
import { listMyPurchase, getPurchase, delPurchase, addPurchase, updatePurchase, exportPurchase } from "@/api/asset/purchase";
import { listCategory } from "@/api/asset/category";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "MyPurchase",
  components: {
    Treeselect
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
      // 采购申请表格数据
      purchaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 审核结果字典
      checkStatusOptions: [],
      // 是否字典
      yesnoOptions: [],
      // 资产分类树选项
      categoryOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cateId: null,
        name: null,
        checkStatus: null,
        isComplete: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        cateId: [
          { required: true, message: "资产分类不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "产品名称不能为空", trigger: "blur" }
        ],
        param: [
          { required: true, message: "参数要求不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "购买数量不能为空", trigger: "blur" }
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
    /** 查询采购申请列表 */
    getList() {
      this.loading = true;
      listMyPurchase(this.queryParams).then(response => {
        this.purchaseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 审核结果字典翻译
    checkStatusFormat(row, column) {
      return this.selectDictLabel(this.checkStatusOptions, row.checkStatus);
    },
    // 是否完成字典翻译
    checkCompleteFormat(row, column) {
      return this.selectDictLabel(this.yesnoOptions, row.isComplete);
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
      listCategory().then(response => {
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
        name: null,
        param: null,
        factory: null,
        proxy: null,
        amount: null,
        price1: null,
        price2: null,
        reason: null,
        comment: null,
        checkUserId: null,
        checkStatus: "0",
        checkTime: null,
        checkReason: null,
        createBy: null,
        masterUserId: null,
        finishTime: null,
        isComplete: null,
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
      this.title = "添加采购申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPurchase(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改采购申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePurchase(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPurchase(this.form).then(response => {
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
      this.$confirm('是否确认删除采购申请编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPurchase(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有采购申请数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportPurchase(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

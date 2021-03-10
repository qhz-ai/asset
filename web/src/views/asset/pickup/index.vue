<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资产分类" prop="cateId">
        <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类" style="width:240px"/>
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
      <el-form-item label="申请时间" prop="createTime">
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
          v-hasPermi="['asset:pickup:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['asset:pickup:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:pickup:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pickupList">
      <el-table-column label="资产分类" align="center" prop="cate.title" />
      <el-table-column label="耗材" align="center" prop="material.name" />
      <el-table-column label="申请人" align="center" prop="applyUser.nickName" />
      <el-table-column label="申请时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="领用数量" align="center" prop="amount" />
      <el-table-column label="申请原由" align="center" prop="reason" />
      <el-table-column label="申请说明" align="center" prop="comment" />
      <el-table-column label="审核人" align="center" prop="checkUser.nickName" />
      <el-table-column label="审核结果" align="center" prop="checkStatus" :formatter="checkStatusFormat" />
      <el-table-column label="审核时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核不通过原因" align="center" prop="checkReason" />
      <el-table-column label="是否出库" align="center" prop="isRevert" :formatter="isDeliveryFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.checkStatus != '1'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleCheck(scope.row)"
            v-hasPermi="['asset:pickup:edit']"
          >审核</el-button>
          <el-button v-if="scope.row.checkStatus != '1'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:pickup:remove']"
          >删除</el-button>
          <el-button v-if="scope.row.checkStatus == '1' && scope.row.isDelivery == 'N'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelivery(scope.row)"
            v-hasPermi="['asset:pickup:edit']"
          >出库</el-button>
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

    <!-- 添加或修改耗材领用对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资产分类" prop="cateId">
           <treeselect v-model="form.cateId" :options="categoryOptions" @select="remoteMaterial" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
        </el-form-item>
        <el-form-item label="耗材" prop="materialId">
          <el-select v-model="form.materialId" filterable placeholder="请输入名称选择" :loading="loading">
            <el-option
              v-for="item in materialList"
              :key="item.id"
              :label="item.name + ' 库存:' + item.amount"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="领用数量" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入领用数量" />
        </el-form-item>
        <el-form-item label="申请原由" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入申请原由" />
        </el-form-item>
        <el-form-item label="申请说明" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入申请说明" />
        </el-form-item>
        <el-form-item label="审核结果" v-if="action == 'check' ">
          <el-radio-group v-model="form.checkStatus">
            <el-radio
              v-for="dict in checkStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="不通过原因" prop="checkReason" v-if="form.checkStatus == '2'">
          <el-input v-model="form.checkReason" placeholder="请输入审核不通过原因" />
        </el-form-item>
        <el-form-item label="出库领用单" v-if="action == 'delivery' ">
          <imageUpload v-model="form.imgId"/>
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
import { listPickup, getPickup, delPickup, checkPickup, updatePickup, exportPickup } from "@/api/asset/pickup";
import { listCategory } from "@/api/asset/category";
import { listMaterial} from "@/api/asset/material";
import { addDelivery } from "@/api/asset/delivery";
import { selectUser } from "@/api/sys/public";
import ImageUpload from '@/components/ImageUpload';
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Pickup",
  components: {
    Treeselect,ImageUpload
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
      // 耗材领用表格数据
      pickupList: [],
      materialList:[],
      // 资产分类树选项
      categoryOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 审核结果字典
      checkStatusOptions: [],
      // 是否字典
      yesnoOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cateId: null,
        materialId: null,
        checkStatus: null,
        orderByColumn:"t.id",
        isAsc:"DESC"
      },
      // 表单参数
      form: {},
      action: 'check',
      dateRange:[],
      // 表单校验
      rules: {
        cateId: [
          { required: true, message: "资产分类不能为空", trigger: "blur" }
        ],
        materialId: [
          { required: true, message: "耗材ID不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "领用数量不能为空", trigger: "blur" }
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
    /** 查询耗材领用列表 */
    getList() {
      this.loading = true;
      listPickup(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.pickupList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 审核结果字典翻译
    checkStatusFormat(row, column) {
      return this.selectDictLabel(this.checkStatusOptions, row.checkStatus);
    },
    // 是否出库字典翻译
    isDeliveryFormat(row, column) {
      return this.selectDictLabel(this.yesnoOptions, row.isDelivery);
    },
    remoteMaterial() {
      listMaterial({cateId:this.form.cateId}).then(response => {
        this.materialList = response.rows;
      });
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
        materialId: null,
        amount: null,
        reason: null,
        comment: null,
        checkUserId: null,
        checkStatus: "0",
        checkTime: null,
        checkReason: null,
        imgId: null
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
      this.dateRange = [];
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
      this.action = 'add';
      this.reset();
      this.open = true;
      this.title = "添加耗材领用";
    },
    /** 审核按钮操作 */
    handleCheck(row) {
      this.action = 'check';
      this.reset();
      const id = row.id || this.ids
      getPickup(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "审核耗材领用";
      });
    },
    /** 出库按钮操作 */
    handleDelivery(row) {
      this.reset();
      this.action = 'delivery';
      const id = row.id || this.ids
      getPickup(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "耗材出库";
      });
    },
    /** 提交按钮
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.action == 'check') {
            checkPickup(this.form).then(response => {
              this.msgSuccess("审核成功");
              this.open = false;
              this.getList();
            });
          } else if (this.action == 'delivery'){
            let delivery = {};
            delivery.imgId = this.form.imgId;
            delivery.cateId = this.form.cateId;
            delivery.materialId = this.form.materialId;
            delivery.amount = this.form.amount;
            delivery.recvUserId = this.form.applyUser.userId;
            delivery.applyId = this.form.id;

            addDelivery(delivery).then(response => {
              this.msgSuccess("出库成功");
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
      this.$confirm('是否确认删除耗材领用编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPickup(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有耗材领用数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportPickup(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资产分类" prop="cateId">
        <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类" style="width:240px"/>
      </el-form-item>
      <el-form-item label="仓库" prop="houseId">
        <el-select v-model="queryParams.houseId" filterable placeholder="请选择仓库">
          <el-option
            v-for="item in warehouseList"
            :key="item.id"
            :label="item.title"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="出库时间" prop="createTime">
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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:delivery:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deliveryList">
      <el-table-column label="资产分类" align="center" prop="cate.title" />
      <el-table-column label="耗材/耗材" align="center" prop="materialId">
      <template slot-scope="scope">
          <span v-if="scope.row.material">{{ scope.row.material.name }} </span>
          <span v-if="scope.row.asset">{{ scope.row.asset.name + ' ' +  scope.row.asset.num}}</span>
        </template>
      </el-table-column>
      <el-table-column label="出库数量" align="center" prop="amount" />
      <el-table-column label="仓库" align="center" prop="house.title" />
      <el-table-column label="接收人" align="center" prop="recvUser.nickName" />
      <el-table-column label="确认单" align="center" prop="imgId">
        <template slot-scope="scope">
          <span v-html="getDownLink(scope.row)"></span>
        </template>
      </el-table-column>
      <el-table-column label="操作人" align="center" prop="user.nickName" />
      <el-table-column label="出库时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
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

    <!-- 添加或修改出库管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资产分类" prop="cateId">
          <el-input v-model="form.cateId" placeholder="请输入资产分类" />
        </el-form-item>
        <el-form-item label="耗材ID" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入耗材ID" />
        </el-form-item>
        <el-form-item label="资产ID" prop="assetId">
          <el-input v-model="form.assetId" placeholder="请输入资产ID" />
        </el-form-item>
        <el-form-item label="出库数量" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入出库数量" />
        </el-form-item>
        <el-form-item label="仓库" prop="houseId">
          <el-input v-model="form.houseId" placeholder="请输入仓库" />
        </el-form-item>
        <el-form-item label="接收人" prop="recvUserId">
          <el-input v-model="form.recvUserId" placeholder="请输入接收人" />
        </el-form-item>
        <el-form-item label="确认单">
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
import { listDelivery, getDelivery, delDelivery, addDelivery, updateDelivery, exportDelivery } from "@/api/asset/delivery";
import ImageUpload from '@/components/ImageUpload';
import { listCategory } from "@/api/asset/category";
import { selectWarehouse } from "@/api/asset/warehouse";
import { selectUser } from "@/api/sys/public";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Delivery",
  components: {
    ImageUpload,Treeselect
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
      // 出库管理表格数据
      deliveryList: [],
      // 资产分类树选项
      categoryOptions: [],
      warehouseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cateId: null,
        materialId: null,
        assetId: null,
        houseId: null,
        recvUserId: null,
      },
      // 表单参数
      form: {},
      dateRange:[],
      // 表单校验
      rules: {
        cateId: [
          { required: true, message: "资产分类不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "出库数量不能为空", trigger: "blur" }
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
    selectWarehouse({status:'1'}).then(response => {
      this.warehouseList = response.rows;
    });
    this.getTreeselect();
  },
  methods: {
    /** 查询出库管理列表 */
    getList() {
      this.loading = true;
      listDelivery(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.deliveryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 下载附件链接 */
    getDownLink(row) {
      if(row.img && row.img.id){
        return '<a href="'+process.env.VUE_APP_BASE_API+'/sys/files/download/'+row.img.id+'" target="_blank">'+row.img.title+'</a>';
      }
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
        assetId: null,
        amount: null,
        houseId: null,
        recvUserId: null,
        imgId: null,
        createBy: null,
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
      this.reset();
      this.open = true;
      this.title = "添加出库管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDelivery(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改出库管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDelivery(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDelivery(this.form).then(response => {
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
      this.$confirm('是否确认删除出库管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDelivery(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有出库管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDelivery(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

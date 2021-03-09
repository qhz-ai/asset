<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资产分类" prop="cateId">
         <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
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
      <el-form-item label="产品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入产品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接收人" prop="recvUserId">
        <el-select v-model="queryParams.useUserId" filterable placeholder="请输入姓名选择" :loading="loading">
          <el-option
            v-for="item in userList"
            :key="item.userId"
            :label="item.nickName"
            :value="item.userId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="接收时间" prop="recvTime">
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
          v-hasPermi="['asset:entering:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enteringList">
      <el-table-column label="资产分类" align="center" prop="cate.title" />
      <el-table-column label="产品名称" align="center" prop="name" />
      <el-table-column label="参数要求" align="center" prop="param" />
      <el-table-column label="仓库" align="center" prop="house.title" />
      <el-table-column label="厂商" align="center" prop="factory" />
      <el-table-column label="购买渠道" align="center" prop="proxy" />
      <el-table-column label="入库数量" align="center" prop="amount" />
      <el-table-column label="市场价格" align="center" prop="price1" />
      <el-table-column label="购买价格" align="center" prop="price2" />
      <el-table-column label="接收人" align="center" prop="recvUser.nickName" />
      <el-table-column label="接收时间" align="center" prop="recvTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recvTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收货单" align="center" prop="imgId">
        <template slot-scope="scope">
          <span v-html="getDownLink(scope.row)"></span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-eye"
            @click="handleView(scope.row)"
            v-hasPermi="['asset:entering:edit']"
          >查看</el-button>
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

    <!-- 添加或修改入库管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资产分类" prop="cateId">
          <el-input v-model="form.cateId" placeholder="请输入资产分类" />
        </el-form-item>
        <el-form-item label="采购订单" prop="purchaseId">
          <el-input v-model="form.purchaseId" placeholder="请输入采购订单" />
        </el-form-item>
        <el-form-item label="仓库" prop="houseId">
          <el-input v-model="form.houseId" placeholder="请输入仓库" />
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
        <el-form-item label="入库数量" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入入库数量" />
        </el-form-item>
        <el-form-item label="市场价格" prop="price1">
          <el-input v-model="form.price1" placeholder="请输入市场价格" />
        </el-form-item>
        <el-form-item label="购买价格" prop="price2">
          <el-input v-model="form.price2" placeholder="请输入购买价格" />
        </el-form-item>
        <el-form-item label="接收人" prop="recvUserId">
          <el-input v-model="form.recvUserId" placeholder="请输入接收人" />
        </el-form-item>
        <el-form-item label="接收时间" prop="recvTime">
          <el-date-picker clearable size="small"
            v-model="form.recvTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择接收时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="收货单">
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
import { listEntering, getEntering, delEntering, addEntering, updateEntering, exportEntering } from "@/api/asset/entering";
import { selectWarehouse } from "@/api/asset/warehouse";
import { listCategory } from "@/api/asset/category";
import { selectUser } from "@/api/sys/public";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import ImageUpload from '@/components/ImageUpload';
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "EnteringQuery",
  components: {
    ImageUpload,Treeselect
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
      // 入库管理表格数据
      enteringList: [],
      // 资产分类树选项
      categoryOptions: [],
      warehouseList: [],
      // 用户数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cateId: null,
        purchaseId: null,
        houseId: null,
        name: null,
        param: null,
        recvUserId: null,
        recvTime: null,
      },
      // 表单参数
      form: {},
      dateRange: [],
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
          { required: true, message: "入库数量不能为空", trigger: "blur" }
        ],
        recvTime: [
          { required: true, message: "接收时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    selectUser({status:'0'}).then(response => {
      this.userList = response.rows;
    });
    selectWarehouse({status:'1'}).then(response => {
      this.warehouseList = response.rows;
    });
    this.getTreeselect();
  },
  methods: {
    /** 查询入库管理列表 */
    getList() {
      this.loading = true;
      listEntering(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.enteringList = response.rows;
        this.total = response.total;
        this.loading = false;
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
    /** 下载附件链接 */
    getDownLink(row) {
      if(row.img && row.img.id){
        return '<a href="'+process.env.VUE_APP_BASE_API+'/sys/files/download/'+row.img.id+'" target="_blank">'+row.img.title+'</a>';
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
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
    /** 修改按钮操作 */
    handleView(row) {
      this.reset();
      const id = row.id || this.ids
      getEntering(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "查看入库信息";
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有入库管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportEntering(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

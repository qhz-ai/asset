<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="查询条件" prop="where">
        <el-input
          v-model="queryParams.where"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="info" icon="el-icon-download" size="mini"
            @click="handleExport" >导出</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="dataList" v-if="ds.type != '3' ">
      <el-table-column type="number" width="55" align="center" />
      <el-table-column :label="c.label" align="center" :prop="c.prop" v-for="c in cols" />
    </el-table>
    <div v-if="ds.type == '3' ">查询结果：{{ dataList}}</div>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { getDataset, queryData, exportDataset} from "@/api/sys/dataset";

export default {
  name: "DatasetQuery",
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
      dataList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        code: null,
        where: null
      },
      // 表单参数
      ds: {},
      cols: []
    };
  },
  created() {
    const dsId = this.$route.params && this.$route.params.dsId;
    getDataset(dsId).then(response => {
      this.ds = response.data;
      this.queryParams.code = this.ds.code;

      this.getList();
    });
  },
  methods: {
    /** 查询数据集列表 */
    getList() {
      this.loading = true;
      queryData(this.queryParams).then(response => {
        this.dataList = response.data;
        if(this.ds.type != '3'){
          this.total = response.total;
          this.cols = [];
          for(var p in this.dataList[0]){
            this.cols.push({label:p,prop:p});
          }
        }

        this.loading = false;
      });
    },

    // 表单重置
    reset() {
      this.form = {
        id: null,
        code: null,
        type: null,
        title: null,
        sqls: null,
        param: null,
        listSort: null,
        comment: null,
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
    handleExport() {
      const queryParams = this.queryParams;
      exportDataset(queryParams).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>

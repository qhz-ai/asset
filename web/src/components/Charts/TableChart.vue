<template>
  <div>
    <el-table v-loading="loading" :data="dataList" :max-height="maxHeight">
      <el-table-column type="number" width="55" align="center" />
      <el-table-column :label="c.title" align="center" :prop="c.field" v-for="c in cols" />
    </el-table>
    <pagination v-if="pager"
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { getDataset, queryData, addDataset} from "@/api/sys/dataset";

export default {
  name: "TableChart",
  props: {
    chartData: {
      type: Object,
      required: true
    },
    params: {
      type: Object,
      required: false
    },
    maxHeight: {
      type: Number,
      required: false
    },
    pager: {
      type: Boolean,
      required: false
    }
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
      // 数据集表格数据
      dataList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        params:{}
      },
      // 表单参数
      ds: {},
      cols: []
    };
  },
  created() {
    this.getList(this.params);
    var cs = this.chartData.comment.split("\n");
    for(var i=0;i<cs.length;i++){
      var c = cs[i].split("=");
      this.cols.push({field:c[0],title:c[1]});
    }
  },
  watch: {
    params: {
      deep: true,
      handler(params) {
        this.getList(params)
      }
    }
  },
  methods: {
    /** 查询数据集列表 */
    getList(params) {
      this.queryParams.id = this.chartData.dsId;
      this.queryParams.params = params;
      this.loading = true;
      
      queryData(this.queryParams).then(response => {
        this.dataList = response.data;
        this.total = response.total;
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
    }
  }
};
</script>

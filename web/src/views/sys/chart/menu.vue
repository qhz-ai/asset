<template>
  <div class="app-container">
    <el-row :gutter="32" v-if="chart.type == 'table'">
      <el-col :xs="24" :lg="{span: 20, offset: 2}" >
      <el-button type="info" icon="el-icon-download" size="mini"
          @click="handleExport" >导出</el-button>
      </el-col>
    </el-row>
    <el-row :gutter="32">
      <el-col :xs="24" :lg="{span: 20, offset: 2}" >
        <div class="chart-wrapper">
          <line-chart :chartData="chart" :params="query"  v-if="chart.type == 'line' "/>
          <bar-chart :chartData="chart" :params="query"  v-if="chart.type == 'bar' "/>
          <pie-chart :chartData="chart" :params="query"  v-if="chart.type == 'pie' "/>
          <gauge-chart :chartData="chart" :params="query"  v-if="chart.type == 'gauge' "/>
          <table-chart :chartData="chart" :params="query"  v-if="chart.type == 'table' "/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import LineChart from '@/components/Charts/LineChart.vue'
import BarChart from '@/components/Charts/BarChart.vue'
import PieChart from '@/components/Charts/PieChart.vue'
import GaugeChart from '@/components/Charts/GaugeChart.vue'
import TableChart from '@/components/Charts/TableChart.vue'
import { getChartByCode} from "@/api/sys/chart";
import { exportDataset} from "@/api/sys/dataset";

export default {
  name: "ChartMenu",
  components: {LineChart, BarChart, PieChart, GaugeChart, TableChart},
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
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        where: null
      },
      query: {
        pageNum: 1,
        pageSize: 10,
        where: null
      },
      // 表单参数
      chart: {}
    };
  },
  created() {
    var path = this.$route.path.split('/');
    const chartCode = path[path.length-1];
    getChartByCode(chartCode).then(response => {
      if(!response.data){
        this.msgError("报表不存在");
        this.chart.type = 'none';
      }else{
        this.chart = response.data;
      }
    });
  },
  methods: {
    /** 搜索按钮操作 */
    handleQuery() {
      this.query.pageNum = 1;
      this.query.where = this.queryParams.where;
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleExport() {
      this.handleQuery();
      exportDataset({id:this.chart.dsId, where:this.query.where, params:{chartId:this.chart.id}}).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>

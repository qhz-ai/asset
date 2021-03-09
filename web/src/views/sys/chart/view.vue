<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="查询条件" prop="where">
        <el-input
          v-model="queryParams.where"
          clearable
          size="small"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
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
import { getChart} from "@/api/sys/chart";

export default {
  name: "ChartView",
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
      // 显示搜索条件
      showSearch: false,
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
    const chartId = this.$route.params && this.$route.params.chartId;
    getChart(chartId).then(response => {
      this.chart = response.data;
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

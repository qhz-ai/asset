<template>
  <div class="dashboard-editor-container">

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;" v-show="lineType == '13'">
      <bar-chart :chartData="line13" v-if="lineType == '13'"/>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;" v-show="lineType == '14'">
      <bar-chart :chartData="line14" v-if="lineType == '14'"/>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;" v-show="lineType == '15'">
      <bar-chart :chartData="line15" v-if="lineType == '15'"/>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;" v-show="lineType == '16'">
      <bar-chart :chartData="line16" v-if="lineType == '16'"/>
    </el-row>



  </div>
</template>

<script>
import { getChart} from "@/api/sys/chart";
import PanelGroup from './dashboard/PanelGroup'
import BarChart from '@/components/Charts/BarChart.vue'

export default {
  name: 'Index',
  components: {
    PanelGroup,
    BarChart
  },
  data() {
    return {
      line13:null,
      line14:null,
      line15:null,
      line16:null,
      lineType: null,
    }
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineType = type;
    }
  },
  created() {
    getChart(13).then(response => {
      this.line13 = response.data;
      this.lineType = "13";
    });
    getChart(14).then(response => {
      this.line14 = response.data;
    });
    getChart(15).then(response => {
      this.line15 = response.data;
    });
    getChart(16).then(response => {
      this.line16 = response.data;
    });
  }
}
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

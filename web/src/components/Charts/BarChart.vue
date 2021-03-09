<template>
  <div :class="className" :style="{height:height,width:width}">

  </div>
</template>

<script>
import { queryData} from "@/api/sys/dataset";
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme

export default {
  name: "BarChart",
  props: {
    chartData: {
      type: Object,
      required: true
    },
    params: {
      type: Object,
      required: false
    },
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '350px'
    }
  },
  data() {
    return {
      chart: null,
      xaxis: null,
      yaxis: null,
      options: {
        title: { text: '一般柱状图', left: 'center'},
        tooltip: {  trigger: 'axis'  },
        toolbox: {
          show: true,
          feature: {
            saveAsImage: { show: true , title: '下载'}
          }
        },
        xAxis:
          {
            type: 'category',
            boundaryGap: true,
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          }
        ,
        yAxis: [
          {
            type: 'value',
            axisLabel: {
              formatter: '{value} '
            }
          }
        ],
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'bar'
          }
        ]
      }
    }
  },
  watch: {
    params: {
      deep: true,
      handler(params) {
        this.initChart(params)
      }
    }
  },
  mounted() {
    this.options.title.text = this.chartData.title;

    if(this.chartData.xaxis){
      var xs = this.chartData.xaxis.split("=");
      this.xaxis = xs[0];
    }else{
      this.xaxis = "x";
    }

    if(this.chartData.yaxis){
      var ys = this.chartData.yaxis.split("=");
      this.yaxis = ys[0];
      this.options.series[0].name = ys[1];
    }else{
      this.yaxis = "y";
      this.options.series[0].name = '数量';
    }

    this.$nextTick(() => {
      this.chart = echarts.init(this.$el, 'macarons');

      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart(params) {
      if(!params) params = {};
      params.id = this.chartData.dsId;
      queryData(params).then(response => {
        this.options.xAxis.data = [];
        this.options.series[0].data = [];
        for(var i=0;i<response.data.length;i++){
          this.options.xAxis.data.push(response.data[i][this.xaxis]);
          this.options.series[0].data.push(response.data[i][this.yaxis]);
        }
        this.chart.setOption(this.options)
      });

    }
  }
}
</script>

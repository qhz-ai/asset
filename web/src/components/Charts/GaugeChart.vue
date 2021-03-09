<template>
  <div :class="className" :style="{height:height,width:width}">

  </div>
</template>

<script>
import { queryData} from "@/api/sys/dataset";
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme

export default {
  name: "GaugeChart",
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
        tooltip: {
            formatter: '{a} <br/>{b} : {c}%'
        },
        toolbox: {
            feature: {
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '指标',
                type: 'gauge',
                detail: {formatter: '{value}%'},
                data: [{value: 50, name: '完成率'}]
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
    this.options.series[0].data[0]['name'] = this.chartData.title;

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
        this.options.series[0].data[0]['value'] = response.data;
        this.chart.setOption(this.options)
      });

    }
  }
}
</script>

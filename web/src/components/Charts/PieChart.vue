<template>
  <div :class="className" :style="{height:height,width:width}">
  </div>
</template>

<script>
  import { queryData } from "@/api/sys/dataset";
  import echarts from 'echarts'
  require('echarts/theme/macarons') // echarts theme

  export default {
    name: "PieChart",
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
          title: {
            text: '某站点用户访问来源',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          toolbox: {
            show: true,
            feature: {
              saveAsImage: {
                show: true,
                title: '下载'
              }
            }
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
          },
          series: [{
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [{
                value: 335,
                name: '直接访问'
              },
              {
                value: 310,
                name: '邮件营销'
              },
              {
                value: 234,
                name: '联盟广告'
              },
              {
                value: 135,
                name: '视频广告'
              },
              {
                value: 1548,
                name: '搜索引擎'
              }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }]
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

      if (this.chartData.xaxis) {
        var xs = this.chartData.xaxis.split("=");
        this.xaxis = xs[0];
      } else {
        this.xaxis = "x";
      }

      if (this.chartData.yaxis) {
        var ys = this.chartData.yaxis.split("=");
        this.yaxis = ys[0];
      } else {
        this.yaxis = "y";
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
        if (!params) params = {};
        params.id = this.chartData.dsId;
        queryData(params).then(response => {
          this.options.legend.data = [];
          this.options.series[0].data = [];
          this.options.series[0].name = '数量';
          for (var i = 0; i < response.data.length; i++) {
            this.options.legend.data.push(response.data[i][this.xaxis]);
            this.options.series[0].data.push({
              value: response.data[i][this.yaxis],
              name: response.data[i][this.xaxis]
            });
          }
          this.chart.setOption(this.options)
        });

      }
    }
  }
</script>

<template>
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>数据统计</el-breadcrumb-item>
            <el-breadcrumb-item>数据报表</el-breadcrumb-item>
        </el-breadcrumb>

        <el-card style="line-height: 10px;">
            <div id="main" style="width: 1000px; height:500px; margin: 20px;"></div>
        
        </el-card>
     </div>
</template>

<script>

  //npm install echarts --save
  import * as echarts from 'echarts';

   export default {
       name: "Report",
       data(){
          return{
             data: ''
          }
       },
       created(){

       },
       async mounted(){
            var dataList = ''
              await  this.$http.get("/getReportData").then((res) => {
                    console.log(res.data);
                    dataList = res.data
                })
 

            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
            title: {
                text: '用户来源 - User Source'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
                }
            },
            legend: {
                data: []
            },
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
           },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                type: 'category',
                boundaryGap: false,
                data: []
                }
            ],
            yAxis: [
                { type: 'value' }
            ],
            series: []
            };

            option && myChart.setOption(option);
            myChart.setOption({
                legend:{
                    data: dataList.areas
                },
                xAxis:[{
                    data: dataList.dates
                }],
                series: dataList.series
            })
            console.log(dataList.series)

       },
       methods:{
           
       }
   }
</script>
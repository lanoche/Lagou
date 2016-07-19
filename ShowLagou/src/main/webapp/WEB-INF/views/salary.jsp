<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="salary"  style="width: 600px;height:400px;"></div>
<script type="text/javascript">
var s=${salary};
var min=JSON.parse(s.min);
var max=JSON.parse(s.max);
var avg=JSON.parse(s.avg);
var Mychart=echarts.init(document.getElementById("salary"));
var option = {
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['最小值','最大值','平均值']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['应届生','1至3年','3至5年','5年以上']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'最小值',
            type:'bar',
            data:min
        },
        {
            name:'最大值',
            type:'bar',
            data:max
        },
        {
            name:'平均值',
            type:'bar',
            data:avg
        }
    ]
};
Mychart.setOption(option);

</script>

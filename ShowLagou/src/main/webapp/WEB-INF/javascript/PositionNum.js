	
    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('positionNum')); 
            var datas=${position};
			datas=JSON.stringify(datas);
            var option = {
                timeline : {
			        data : [
			            '2013-01-01' , '2013-02-01', '2013-03-01'
			        ],
			        label : {
			            formatter : function(s) {
			                return s.slice(0, 7);
			            }
			        }
			    },
			    options : [
			        {
			            title : {
			                text: '求职工作年限对比',
			                subtext: 'java岗位'
			            },
			            tooltip : {
			                trigger: 'item',
			                formatter: "{a} <br/>{b} : {c} ({d}%)"
			            },
			            legend: {
			                data:['Chrome','Firefox','Safari','IE9+','IE8-']
			            },
			            toolbox: {
			                show : true,
			                feature : {
			                    mark : {show: true},
			                    dataView : {show: true, readOnly: false},
			                    magicType : {
			                        show: true, 
			                        type: ['pie', 'funnel'],
			                        option: {
			                            funnel: {
			                                x: '25%',
			                                width: '50%',
			                                funnelAlign: 'left',
			                                max: 1700
			                            }
			                        }
			                    },
			                    restore : {show: true},
			                    saveAsImage : {show: true}
			                }
			            },
			            series : [
			                {
			                    name:'浏览器（数据纯属虚构）',
			                    type:'pie',
			                    center: ['50%', '45%'],
			                    radius: '50%',
			                    data:datas
			                }			
			            ]
			        }
	    		]
	        };
            // 为echarts对象加载数据 
            myChart.setOption(option); 
        }
    );

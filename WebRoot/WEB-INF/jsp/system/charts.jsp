 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%  
		String loginName = session.getAttribute("loginName").toString();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="msapplication-tap-highlight" content="no" />
	<meta name="robots" content="noindex" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
	<meta name="renderer" content="webkit" />
	<title>图表</title>	
</head>
<body>
	
<div class="basemian">
    <div id="main" style="width: 600px;height:400px;"></div>
   <div id="mainLine" style="width: 600px;height:400px;margin-top:50px;"></div>
   <div id="mainpie" style="width: 600px;height:400px;margin-top:50px;"></div>
   <div id="mainarea" style="width: 600px;height:400px;margin-top:50px;"></div>
</div>
	        	
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../common/js/echarts.min.js"></script>
<script type="text/javascript">	
		var categories = ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"];
        var data = [5, 20, 36, 10, 10, 20];
        
		DrawCharts();
        ///将画多个图表的进行函数封装
        function DrawCharts(ec) {
            DrawColumnEChart(ec,categories,data);
            DrawLineEChart(ec);
            DrawpieEChart(ec);
            DrawareaEChart(ec);
        }
        //创建ECharts柱状图图表
        function DrawColumnEChart(ec,categories,data) {
            //--- 柱状图 ---
            var myChart = echarts.init(document.getElementById('main'));           
            myChart.setOption({
            	title: {
                    text: '今日新增',
                    link: 'http://www.baidu.com',
                    target: 'blank',
                    textAlign: 'center',
                    left: 300
                },
                backgroundColor: '#fff',
                color: ['#3398DB'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
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
                        data : categories,//['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        /*axisLabel : {
                            formatter: [0,200,400,600,800,1000]
                        },*/
                        splitNumber:6,
                        interval: 20,//y轴分割间隔倍数。
                        min: 0,//y轴分割从0开始。
                        max: 100//y轴分割从1000结束。
                    }
                ],
                series : [
                    {
                        name:'今日新增资源',
                        type:'bar',
                        barWidth: '60%',
                        data: data//[10, 52, 200, 334, 390, 330, 220]
                    }
                ]
            });
        }

        //创建ECharts折线图图表
        function DrawLineEChart(ec) {
            //--- 折线图 ---
            var myLineChart = echarts.init(document.getElementById('mainLine'));            
            
            myLineChart.setOption({
                title: {
                    text: '堆叠区域图'
                },                
                tooltip : {
                    trigger: 'axis'
                },
                legend: {//图例组件
                    data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎'],
                    left: 100,
                    top: 3,
                },
                toolbox: {//工具栏
                    feature: {//各工具配置项。
                        saveAsImage: {},//保存为图片
                         magicType: {//动态类型切换 
                            type: ['line', 'bar', 'stack', 'tiled']
                        }
                    }
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
                        boundaryGap : false,
                        data : ['周一','周二','周三','周四','周五','周六','周日']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'邮件营销',
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:[120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name:'联盟广告',
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:[220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name:'视频广告',
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:[150, 232, 201, 154, 190, 330, 410]
                    },
                    {
                        name:'直接访问',
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:[320, 332, 301, 334, 390, 330, 320]
                    },
                    {
                        name:'搜索引擎',
                        type:'line',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        areaStyle: {normal: {}},
                        data:[820, 932, 901, 934, 1290, 1330, 1320]
                    }
                ]
            });
        }

        //创建ECharts饼图图表
        function DrawpieEChart(ec) {
            //--- 折线图 ---
            var myLineChart = echarts.init(document.getElementById('mainpie'));            
            
            myLineChart.setOption({
                title : {
                    text: '某站点用户访问来源',
                    subtext: '纯属虚构',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
                },
                series : [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:335, name:'直接访问'},
                            {value:310, name:'邮件营销'},
                            {value:234, name:'联盟广告'},
                            {value:135, name:'视频广告'},
                            {value:1548, name:'搜索引擎'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });
        }



        //创建ECharts折线图图表
        function DrawareaEChart(ec) {
            //--- 折线图 ---
            var myareaChart = echarts.init(document.getElementById('mainarea'));            
            myareaChart.setOption({
                title: {
                    text: "折线图"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['stepday.com', 'tuiwosa.com']
                },
                toolbox: {
                    show: false
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                    }
                ],
                yAxis: [
                    {
                        type : 'value',
                        /*axisLabel : {
                            formatter: [0,200,400,600,800,1000]
                        },*/
                        splitNumber:6,
                        interval: 200,      //y轴分割间隔倍数。
                        min: 0,             //y轴分割从0开始。
                        max: 1000,          //y轴分割从1000结束。
                        splitArea: { show: true }
                    }
                ],
                series: [
                    {
                        name: 'stepday.com',
                        type: 'line', //序列展现类型为折线图
                        data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                    },
                    {
                        name: 'tuiwosa.com',
                        type: 'line',
                        data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                    }
                ]
            });
        }
</script>
</body>
</html>
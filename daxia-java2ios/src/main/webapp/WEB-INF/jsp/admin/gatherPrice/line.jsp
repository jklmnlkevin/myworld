<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<script type="text/javascript" src="${ctx }/res/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${ctx }/res/echarts/echarts-plain.js"></script>

<div class="pageContent">

	<div id="main" style="width: 700px; height: 500px; margin: auto;">
		
	</div>
	
</div>

<script type="text/javascript">

$(document).ready(function(){ 


	var myChart = echarts.init(document.getElementById('main'));
	myChart.showLoading({
	    text: '正在努力的读取数据中...',    //loading话术
	});
	//ajax getting data...............

	//ajax callback
	myChart.hideLoading();

	option = {
		    title : {
		        text: '价格变化',
		        subtext: ${productName}
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['最高价格','最低价格']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ${xData}
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value}'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'价格',
		            type:'line',
		            data:${price},
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
	myChart.setOption(option);
	
});
</script>

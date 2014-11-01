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
		        text: '在线调查：${question.questionName}',
		        subtext: '调查结果',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:${legend}
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    series : [
		        {
		            name:'${question.questionName}',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:${answers}
		        }
		    ]
		};
		                    
	myChart.setOption(option);
	
});
</script>

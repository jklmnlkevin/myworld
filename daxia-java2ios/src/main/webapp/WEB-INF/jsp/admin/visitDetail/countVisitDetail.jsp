<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="visitDetail"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/list">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/admin/${module}/list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
				<li>
				<label>走访人员:</label>
				<input type="text" name="visit.user.username" value="${dto.visit.user.username}"/>	
			</li>
			<li>
				<label>走访记录标题:</label>
				<input type="text" name="visit.title" value="${dto.visit.title}"/>	
			</li>
			<li>
				<label>商户营业状态:</label>
				<select name = "status">
					<option value = "">--请选择--</option>
					<option value = "0">未经营</option>
					<option value = "1">已经营</option>
				</select>
			</li>
		</ul>
		<ul  class="searchContent">
				<li>
					<label>开始时间：</label>
					<input type="text"  class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" name="startDate" value="<fmt:formatDate value="${dto.startDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</li>
				<li>
					<label>结束时间：</label>
					<input type="text"  class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" name="endDate" value="<fmt:formatDate value="${dto.endDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</li>
		</ul>	
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
<%-- 			<sec:authorize ifAnyGranted="visitDetail.add"> --%>
<%-- 			<li><a class="add" href="${ctx}/admin/${module}/detail?navTabId=${param.navTabId}" target="navTab" mask="true"><span>添加</span></a></li> --%>
<%-- 			</sec:authorize> --%>
			
<%-- 			<sec:authorize ifAnyGranted="visitDetail.delete"> --%>
<%-- 			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/delete?navTabId=${param.navTabId}" class="delete"><span>删除</span></a></li> --%>
<%-- 			</sec:authorize> --%>
			
<%-- 			<sec:authorize ifAnyGranted="visitDetail.update"> --%>
<%-- 			<li><a class="edit" href="${ctx}/admin/${module}/detail?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>查看或修改</span></a></li> --%>
<%-- 			</sec:authorize> --%>
			
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th  align="left"  width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center" width="100">客户经理</th>
				<th align="center" width="100">名下客户总数</th>
				<th align="center" width="100">已经走访</th>
				<th align="center" width="100">未走访</th>
				<th align="center" width="100">操作</th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="n">
			<tr target="sid_user" rel="">
				<td><input name="ids" value="" type="checkbox"></td>
			
				<td>${n.username}</td>
				<td>${n.merNum}</td>
				<td>${n.visitedNum}</td>
				<td>${n.unVisitedNum}</td>
				<td><c:if test="${n.unVisitedNum > 0 }"><a href="${ctx }/admin/visitDetail/showUnVisitMerchant?merchantNames=${n.unVisitStr}"  target="dialog">查看未走访记录商户</a></c:if></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="${page.numPerPage}" selected>${page.numPerPage}</option>
				<c:if test="${page.numPerPage != 15}">
					<option value="15">15</option>
				</c:if>
				<c:if test="${page.numPerPage != 50}">
					<option value="50">50</option>
				</c:if>
				<c:if test="${page.numPerPage != 100}">
					<option value="100">100</option>
				</c:if>
			</select>
			<span>条，共${page.totalRecords}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalRecords}" numPerPage="${page.numPerPage}" pageNumShown="10" currentPage="${page.pageNum}"></div>

	</div>
</div>
<script type="text/javascript">
$(document).ready(function() { 
	$("table tbody tr").each(function(){ 
		var unVisited = $(this).find('td').eq(2).text(); 
		if (parseInt(unVisited) > 0) {
			$(this).css('background-color','red');
		}
	}); 
});	
</script>

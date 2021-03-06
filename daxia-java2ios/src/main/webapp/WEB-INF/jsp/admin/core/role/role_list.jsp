<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 


<c:set var="module" value="role"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<!--  
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}" method="post">
	<div class="searchBar">
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				 <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> 
			</ul>
		</div>
	</div>
	</form>
</div>
-->

<div id="${module}_page" class="pageContent leftBar_page">
	<div class="panelBar">
		<ul class="toolBar">
			<sec:authorize ifAnyGranted="system.authority.add">
			<li><a class="add" href="${ctx}/admin/${module}/detail?navTabId=${param.navTabId}" target="ajax" rel="${module }_jbsxBox" mask="true"><span>添加</span></a></li>
			</sec:authorize>
			<!--<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/delete?navTabId=${param.navTabId}" class="delete"><span>删除</span></a></li>
			<li><a class="edit" href="${ctx}/admin/${module}/detail?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>修改</span></a></li>
			 <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<div class="${module}_div" layoutH="20" style="float:left; display:block; overflow:auto; width:224px; border:solid 1px #CCC; line-height:21px; background:#fff">
		<table class="table" nowrapTD="false">
			<thead>
				<tr>
					<th align="left"  width="0px"  style="display:none;"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th align="center"  style="font-size:16px;font-weight:bold;">角色名</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roles }" var="n">
					<tr onclick="$('.role_input').attr('checked',false); $(this).find('.role_input').attr('checked',true);" target="sid_user" rel="${n.id }">
						<td style="display:none;"><input class="role_input" name="ids" value="${n.id }" type="checkbox"></td>
						<td><a style="display:block;height:100%;line-height:28px;" href="${ctx }/admin/role/detail?id=${n.id }" target="ajax"  rel="${module }_jbsxBox">${n.name }</a></td>
					</tr>
				</c:forEach>
			</tbody>
	</table>
	
		<div class="panelBar" style="position:fixed;bottom:35px;">		
			<div class="pagination" style="padding-left:0px;" targetType="navTab" totalCount="${page.totalRecords}" numPerPage="${page.numPerPage}" pageNumShown="10" currentPage="${page.pageNum}">
			</div>
		</div>
	</div>	
	
	<div id="${module }_jbsxBox" class="unitBox" style="margin-left:226px;">
		<!--#include virtual="list1.html" -->
	</div>
	

</div>
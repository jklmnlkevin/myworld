<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="user"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/list">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="userType" value="${param.userTpye}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/admin/${module}/list?userType=${param.userType }" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>用户名：</label>
				<input type="text" name="username" value="${user.username}"/>	
			</li>
			<li>
				<label>手机号码：</label>
				<input type="text" name="telephone" value="${user.telephone}"/>	
			</li>
			<li>
				<label>用户所在部门：</label>
				<select name="dept.id">
				 <option value = "">--请选择--</option>
				 <c:forEach items="${depts }" var = "dept">
				 	<option value = "${dept.id }">${dept.name }</option>
				 </c:forEach>
				</select>
			</li>
			<li>
				<label>用户类型：</label>
				<select name="userType">
					 <option value = "">--请选择--</option>
					 <option value = "1">普通用户</option>
					 <option value = "2">商户</option>
					 <option value = "3">主管领导</option>
					 <option value = "4">主任</option>
					 <option value = "5">市场员</option>
					 <option value = "6">客户经理</option>
					 <option value = "7">普通员工</option>
				</select>
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
			<sec:authorize ifAnyGranted="user.add">
			<li><a class="add" href="${ctx}/admin/${module}/detail?navTabId=${param.navTabId}" target="navTab" mask="true"><span>添加</span></a></li>
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="user.delete">
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/delete?navTabId=${param.navTabId}" class="delete"><span>删除</span></a></li>
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="user.update">
			<li><a class="edit" href="${ctx}/admin/${module}/detail?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>修改</span></a></li>
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="user.update">
			<li><a class="edit" href="${ctx}/admin/${module}/resetPassword?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要重置密码的记录"><span>重置密码</span></a></li>
			</sec:authorize>
			
			<c:if test="${param.userType eq 7}">
			<li><a class="edit" href="${ctx}/admin/merchant/list?manager.id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请先选择员工"><span>查看员工的商户</span></a></li>
			</c:if>
			
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th  align="left"  width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center" width="100">用户名</th>
				<th align="center" width="100">电话</th>
				<th align="center" width="100">用户类型</th>
				<c:if test="${param.userType eq 7}">
				<th align="center" width="100">所属部门</th>
				</c:if>
				<th align="center" width="30">性别</th>
				<th align="center" width="50">烟龄</th>
				<th align="center" width="100">生日</th>
				<th align="center" width="50">头像</th>
				<th align="center" width="100">地址</th>
				<th align="center" width="100">吸烟主吸价位</th>
				<th align="center" width="100">日均吸烟量</th>
				<th align="center" width="100">钟爱第一品牌</th>
				<th align="center" width="100">钟爱第一产品</th>
				<th align="center" width="100">钟爱第二品牌</th>
				<th align="center" width="100">钟爱第二产品</th>

<!-- 				<th align="center" width="100">密码</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users }" var="n">
			<tr target="sid_user" rel="${n.id }">
				<td><input name="ids" value="${n.id }" type="checkbox"></td>
				<td>${n.username}</td>
				<td>${n.telephone}</td>
				<td>
					<c:choose>
						<c:when test = "${n.userType == 1 }">普通用户</c:when>
						<c:when test = "${n.userType == 2 }">商户</c:when>
						<c:when test = "${n.userType == 3 }">主管领导</c:when>
						<c:when test = "${n.userType == 4 }">主任</c:when>
						<c:when test = "${n.userType == 5 }">市管员</c:when>
						<c:when test = "${n.userType == 6 }">客户经理</c:when>
						<c:when test = "${n.userType == 7 }">普通员工</c:when>
					</c:choose>
				</td>
				<c:if test="${param.userType eq 7}">
				<td>${n.dept.name }</td>
				</c:if>
				<td><c:if test= "${n.sex == 0 }">男</c:if><c:if test= "${n.sex == 1 }">女</c:if></td>
				<td>${n.smokeAge }</td>
				<td><fmt:formatDate value="${n.birthday }" pattern="yyyy-MM-dd"/> </td>
				<td><c:if test="${!empty(n.headImage) }"><a href="${ctx }/admin/image/preview?imageName=${n.headImage}" target ="dialog">查看</a></c:if></td>
				<td>${n.address }</td>
				<td>${n.spread }</td>
				<td>${n.dailySmoke }</td>
				<td>${n.firstBrand }</td>
				<td>${n.firstProduct }</td>
				<td>${n.secondBrand }</td>
				<td>${n.secondProduct }</td>
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

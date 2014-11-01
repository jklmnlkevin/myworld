<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="user"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${user.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="56">
			 <dl>
			 	<dt>用户名:</dt>
			 	<dd><input type="text" name="username" value="${user.username }" size="30" /></dd>
			 </dl>
			 <c:if test="${empty(user.id) }">
			 <dl>
			 	<dt>用户密码:</dt>
			 	<dd><input type="text" name="password" value="${user.password }" size="30" /></dd>
			 </dl>
			 </c:if>
			  <c:if test="${!empty(user.id) }">
			 	 <input type="text" name="password" value="${user.password }" size="30" hidden="true" />
			  </c:if>
			  <dl>
			 	<dt>电话:</dt>
			 	<dd><input type="text" name="telephone" value="${user.telephone }" size="30" /></dd>
			 </dl>
			  <dl>
			 	<dt>联系地址:</dt>
			 	<dd><input type="text" name="address" value="${user.address }" size="30" /></dd>
			 </dl>
			  <dl>
			 	<dt>生日:</dt>
			 	<dd>
			 		<input type="text" name="birthday" value="<fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/>" class="date textInput readonly" datefmt="yyyy-MM-dd" readonly="true" size="30"  />
				    <a class="inputDateButton" href="javascript:;">选择</a>
			 	</dd>
			 </dl>
			  <dl>
			 	<dt>烟龄:</dt>
			 	<dd><input type="text" name="smokeAge" value="${user.smokeAge }" size="30" /></dd>
			 </dl>
			  <dl>
			 	<dt>用户类型:</dt>
			 	<dd>
			 		<select name="userType" id="userType" onchange="displayDetp(this.id)">
						<c:forEach items="${userTypes }" var="u">
							<option value = "${u.key }" <c:if test="${user.userType == u.key }">selected = "selected"</c:if> >${u.value }</option>
						</c:forEach>
					</select>
			 	</dd>
			 </dl>
			  <dl id = "dept" hidden="hidden">
			 	<dt>部门:</dt>
			 	<dd>
				 	<select  name="dept.id" id="dept" class="combox">
						<option value="" selected>请选择</option>
						<c:forEach items="${depts }" var="p">
							<option value="${p.id }" <c:if test="${user.dept.id == p.id }">selected="selected"</c:if> >${p.name }</option>
						</c:forEach>
					</select>
			 	</dd>
			 </dl>
			  <dl>
			 	<dt></dt>
			 	<dd></dd>
			 </dl>
			 
			  <dl>
			 	<dt></dt>
			 	<dd></dd>
			 </dl>
		
	
			
		
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(user.id)}">
						<sec:authorize ifAnyGranted="user.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(user.id)}">
						<sec:authorize ifAnyGranted="user.update">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

<script type="text/javascript">
	
	$(document).ready(function(){ 
		  $("#dept").hide();
		}); 

	function displayDetp(id) {
		$("#dept").hide();
		var userTypeId = $("#userType").val();
		if (7 == userTypeId) {
			$("#dept").show();
		}
		//alert(userTypeId);
	}
</script>

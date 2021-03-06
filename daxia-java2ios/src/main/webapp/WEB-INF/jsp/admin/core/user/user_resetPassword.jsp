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
	<form method="post" action="${ctx }/admin/${module }/doResetPassword?id=${user.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>用户名:</label>
				<input type="hidden" name="id" value="${user.id}" size="30" />
				<input type="text" value="${user.username }" readonly="readonly"/>
			</div>
			<div class="unit">
				<label>密码:</label>
				<input id="w_validation_pwd" type="password" name="password" class="required alphanumeric" minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位"/>
			</div>
			<div class="unit">
				<label>确认密码:</label>
				<input type="password" name="repassword" class="required" equalto="#w_validation_pwd"/>
			</div>
			
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="favoriteBrand"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户:</label>
				<input type="text" name="user" value="${n.user }" size="30" />
			</p>
			<p>
				<label>:</label>
				<input type="text" name="first_brand" value="${n.first_brand }" size="30" />
			</p>
			<p>
				<label>:</label>
				<input type="text" name="first_product" value="${n.first_product }" size="30" />
			</p>
			<p>
				<label>:</label>
				<input type="text" name="second_brand" value="${n.second_brand }" size="30" />
			</p>
			<p>
				<label>:</label>
				<input type="text" name="second_product" value="${n.second_product }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(favoriteBrand.id)}">
						<sec:authorize ifAnyGranted="favoriteBrand.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(favoriteBrand.id)}">
						<sec:authorize ifAnyGranted="favoriteBrand.update">
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

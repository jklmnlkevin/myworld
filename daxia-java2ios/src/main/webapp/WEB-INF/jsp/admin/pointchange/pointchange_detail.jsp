<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="pointchange"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>变动积分:</label>
				<input type="text" name="piont" value="${n.point }" size="30" />
			</p>
			<p>
				<label>期初:</label>
				<input type="text" name="beginPoint" value="${n.beginPoint }" size="30" />
			</p>
			<p>
				<label>期末:</label>
				<input type="text" name="endPoint" value="${n.endPoint }" size="30" />
			</p>
			<p>
				<label>变动时间:</label>
					<input type="text" name="time" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${n.time }'/>" class="date textInput readonly" datefmt="yyyy-MM-dd HH:mm:ss" readonly="true" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>关联的账号ID:</label>
				<input type="text" name="account.user.username" value="${n.account.user.username }" size="30" readonly="readonly" />
			</p>
			<p>
				<label>积分改变类型.0-积分兑换,1-签到积分,2-消费积分:</label>
				<input type="text" name="type" value="${n.type }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(pointchange.id)}">
						<sec:authorize ifAnyGranted="pointchange.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(pointchange.id)}">
						<sec:authorize ifAnyGranted="pointchange.update">
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 




<div class="pageContent">

	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th align="center" width="100">商户名称</th>
				
			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="n">
			<tr target="sid_user" rel="">
				
				<td>${n}</td>
			
			</tr>
			</c:forEach>
		</tbody>
	</table>


</div>
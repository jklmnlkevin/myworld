<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="apiTest"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${apiTest.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return _onsubmit(this);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>api模块:</label>
				<select name="apiModule.id">
					<c:forEach items="${apiModules}" var="m">
						<option value="${m.id }" ${m.id eq apiTest.apiModule.id ? 'selected' : '' }>${m.name }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>api名称:</label>
				<input type="text" name="name" value="${apiTest.name }" size="30" />
			</p>
			<div class="unit">
				<label>url:</label>
				<input type="text" name="url" value="${apiTest.url }" size="130" placeholder="不要包括${ctx }/"/>
			</div>
			<p>
				<label>请求方式:</label>
				<select name="requestMethod">
					<option value="POST" ${apiTest.requestMethod eq 'POST' ? 'selected' : '' }>POST</option>
					<option value="GET" ${apiTest.requestMethod eq 'GET' ? 'selected' : '' }>GET</option>
				</select>
			</p>
			<div class="unit">
				<label>描述:</label>
				<textarea name="description" style="width:70%; height: 100px;">${apiTest.description }</textarea>
			</div>
			<div class="unit">
				<label>示例url:</label>
				<input type="text" name="exampleUrl" value="${apiTest.exampleUrl }" size="130" placeholder="不要包括${ctx }/"/>
			</div>
			<div class="unit">
				<label>示例返回:</label>
				<textarea name="exampleResponse" style="width:70%; height: 100px;">${apiTest.exampleResponse }</textarea>

			</div>
			<div class="unit">
				<label>参数:</label>
				<button onclick="return add();">增加参数</button>
				<div>
					<table class="table" width="100%" layoutH="138" nowrapTD="false">
						<thead>
							<tr>
								<th align="center" width="100">
									参数名
								</th>
								<th align="center" width="100">
									是否必须
								</th>
								<th align="center" width="100">
									默认值
								</th>
								<th align="center" width="100">
									说明
								</th>
								<th align="center" width="100">
									操作
								</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<tr id="templateTr">
								<td>
									<input type="text" name="names"/>
								</td>
								<td>
									<select name="requires">
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
								</td>
								<td>
									<input type="text" name="defaultValues"/>
								</td>
								<td>
									<textarea style="width:100%;" name="descriptions">这个参数主要是</textarea>
								</td>
								<td>
									<button onclick="return _delete(this);">删除</button>
								</td>
							</tr>
							<c:forEach items="${apiTest.apiTestParameters }" var="p">
							<tr>
								<td>
									<input type="text" name="names" value="${p.name }"/>
								</td>
								<td>
									<select name="requires">
										<option value="0" ${p.required ? '' : 'selected'}>否</option>
										<option value="1" ${p.required ? 'selected' : ''}>是</option>
									</select>
								</td>
								<td>
									<input type="text" name="defaultValues" value="${p.defaultValue }"/>
								</td>
								<td>
									<textarea style="width:100%;" name="descriptions">${p.description }</textarea>
								</td>
								<td>
									<button onclick="return _delete(this);">删除</button>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(apiTest.id)}">
						<sec:authorize ifAnyGranted="apiTest.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(apiTest.id)}">
						<sec:authorize ifAnyGranted="apiTest.update">
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
<script>
	var templateTr = $('#templateTr').html();
	$('#templateTr').remove();
	function _delete(btn) {
		$(btn).parent().parent().remove();
	}
	
	function add() {
		var tbody = $('#tbody');
		tbody.append('<tr>' + templateTr + '</tr>');
		return false;
	}
	
	function _onsubmit(form) {
		/*var names = new Array();
		$('input[flag=name]').each(function() {
			names.push($(this).val());
		});
		
		var requires = new Array();
		$('select[flag=required]').each(function() {
			requires.push($(this).val());
		});
		var defaultValues = new Array();
		$('input[flag=defaultValue]').each(function() {
			defaultValues.push($(this).val())
		});
		var descriptions = new Array();
		$('textarea[flag=description]').each(function() {
			descriptions.push($(this).val());
		});
		
		form. */
		return validateCallback(form, navTabAjaxDone);
	}
</script>

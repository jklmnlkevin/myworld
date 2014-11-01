<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<style type="text/css">
	table {margin:auto;}
	table tr td{ border-top:black solid 1px; border-left:black solid 1px; width:25%; text-align:left;}
	.borderRight{border-right:black solid 1px;}
	.borderBottom{border-bottom:black solid 1px;}
</style>

<script src="${ctx }/res/js/print.js"></script>
<div>
	<a href="###" onclick="printDiv('printDiv')">打印</a>
</div>
<div id="printDiv">
	<table align="centor" style="border-spacing:0px;">
		<tr>
			<td colspan="4" height="100px;" style="text-align:center" class="borderRight"><h1>永州市烟草专卖局</h1></td>
		</tr>
		<tr>
			<td colspan="4" height="10px;" style="text-align:center; padding-top: 20px;" class="borderRight"><h3>卷烟零售户基础信息变更申请审批表</h3></td>
		</tr>
		<tr>
			<td height="60px;">许可证号码</td>
			<td>${mcl.merchant.merId }</td>
			<td>申请人姓名</td>
			<td class="borderRight">${mcl.merchant.user.username }</td>
		</tr>
		<tr>
			<td height="60px;">经营地址：</td>
			<td colspan="3" class="borderRight">${mcl.merchant.address }</td>
		</tr>
		<tr>
			<td height="60px;">申请事项：</td>
			<td colspan="3" class="borderRight">电话号码变更</td>
		</tr>
			<tr>
			<td height="60px;">现有状态</td>
			<td>
				<c:if test="${!empty mcl.oriCallingNum }">原始主叫号码:${mcl.oriCallingNum } <br /></c:if>
				<c:if test="${!empty mcl.oriCalledNum }">原始被叫号码:${mcl.oriCalledNum } <br /></c:if>
			</td>
			<td>申请变更为</td>
			<td class="borderRight">
			    <c:if test="${!empty mcl.callingNum }">新主叫号码:${mcl.callingNum } <br /></c:if>
				<c:if test="${!empty mcl.calledNum }">新被叫号码:${mcl.calledNum } <br /></c:if>
			</td>
		</tr>
		<tr>
			<td height="60px;">变更原因</td>
			<td colspan="3" class="borderRight"></td>
		</tr>
		<tr>
			<td height="60px;">客户经理核查结果</td>
			<td colspan="3" class="borderRight"></td>
		</tr>
		<tr>
			<td height="60px;">市管员核查结果</td>
			<td colspan="3" class="borderRight"></td>
		</tr>
		<tr>
			<td height="60px;">销售部门审核意见</td>
			<td colspan="3" class="borderRight"></td>
		</tr>
		<tr>
			<td height="60px;" class="borderBottom">专卖部门审核意见</td>
			<td colspan="3" class="borderRight borderBottom"></td>
		</tr>
	</table>
</div>
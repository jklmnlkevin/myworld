<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<html>

<head>
<meta name=标题 content=客户走访明细表>
<meta name=关键词 content=客户走访明细表>
<meta http-equiv=Content-Type content="text/html; charset=x-mac-chinesesimp">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 14">
<title>客户走访明细表</title>
<style>
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{margin:.75in .71in .75in .71in;
	mso-header-margin:.31in;
	mso-footer-margin:.31in;
	mso-page-orientation:landscape;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体, sans-serif;
	mso-font-charset:134;
	mso-char-type:none;
	display:none;}
-->
</style>
<![if !supportTabStrip]><script language="JavaScript">
<!--
function fnUpdateTabs()
 {
  if (parent.window.g_iIEVer>=4) {
   if (parent.document.readyState=="complete"
    && parent.frames['frTabs'].document.readyState=="complete")
   parent.fnSetActiveSheet(0);
  else
   window.setTimeout("fnUpdateTabs();",150);
 }
}

//-->
</script>
<![endif]>

<script src="${ctx}/res/js/print.js"></script>
</head>

<body link=blue vlink=purple>

<div>
	<a href="###" onclick="printDiv('printDiv')">打印</a>
</div>
<div id="printDiv" style="width:700px">

<table border=1 cellpadding=0 cellspacing=0  style='border-collapse:
 collapse;table-layout:fixed;width:794px'>
 <col class=xl66 width=164 style='
 width:164pt'>
 <col class=xl66 width=164 style='
 width:164pt'>
 <col class=xl66 width=168 style='
 width:68pt'>
 <col class=xl66 width=344 style='
 width:300pt'>
 <tr height=35 style='mso-height-source:userset;height:35.0pt'>
  <td colspan=4 height=35 class=xl67  style='height:35.0pt;width:794px; text-align:center; font-size: 15pt;'>
  <b>
   【${user.username}】 <font class="font7">客户走访明细表</font>
   </b>
   </td>
   
 </tr>
 <tr height=30 style='mso-height-source:userset;height:30.0pt'>
  <td colspan=4 height=30 class=xl69 style='height:30.0pt;text-align:right;'><span
  style="mso-spacerun:yes;">
  </span>客户经理： ${managerName }</td>
 </tr>
 <tr height=22 style='mso-height-source:userset;height:22.0pt; text-align:center;'>
  <td height=22 class=xl65 style='height:22.0pt'>日期</td>
  <td class=xl65 style='border-left:none'>地点</td>
  <td class=xl65 style='border-left:none'>客户数</td>
  <td class=xl65 style='border-left:none'>客户名单</td>
 </tr>
 <c:forEach items="${list }" var="v">
 <tr height=22 style='mso-height-source:userset;height:22.0pt; text-align:center;'>
  <td height=22 class=xl65 style='height:22.0pt;'>
	${v.day }  
  </td>
  <td class=xl65 style=''>${v.address }</td>
  <td class=xl65 style=''>${v.merchantCount }</td>
  <td class=xl65 style=''>${v.merchantNames }</td>
 </tr>
 </c:forEach>

 
</table>
</div>
</body>

</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理系统</title>

<link href="${ctx}/res/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/res/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/res/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${ctx}/res/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/res/dwz/themes/css/custom.css" rel="stylesheet" type="text/css"></link>

<link href="${ctx}/res/dev/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css"></link>

<link rel="stylesheet" href="${ctx }/res/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">

<link rel="stylesheet" href="${ctx }/res/css/k.css" type="text/css">

<!--[if IE]>
<link href="${ctx}/res/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${ctx}/res/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->


<script src="${ctx}/res/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>

<script src="${ctx }/res/dev/jquery-ui-1.10.3.min.js"></script>
<script src="${ctx }/res/js/k.js"></script>

<script src="${ctx}/res/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="${ctx}/res/dwz/chart/raphael.js"></script>
<script type="text/javascript" src="${ctx}/res/dwz/chart/g.raphael.js"></script>
<script type="text/javascript" src="${ctx}/res/dwz/chart/g.bar.js"></script>
<script type="text/javascript" src="${ctx}/res/dwz/chart/g.line.js"></script>
<script type="text/javascript" src="${ctx}/res/dwz/chart/g.pie.js"></script>
<script type="text/javascript" src="${ctx}/res/dwz/chart/g.dot.js"></script>

<script src="${ctx}/res/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx}/res/dwz/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${ctx}/res/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript" src="${ctx }/res/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/res/ztree/js/jquery.ztree.excheck-3.5.js"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${ctx}/res/dwz/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="###">标志</a>
				<ul class="nav">
					<!-- <li id="switchEnvBox"><a href="javascript:">（<span>北京</span>）切换城市</a>
						<ul>
							<li><a href="sidebar_1.html">北京</a></li>
							<li><a href="sidebar_2.html">上海</a></li>
							<li><a href="sidebar_2.html">南京</a></li>
							<li><a href="sidebar_2.html">深圳</a></li>
							<li><a href="sidebar_2.html">广州</a></li>
							<li><a href="sidebar_2.html">天津</a></li>
							<li><a href="sidebar_2.html">杭州</a></li>
						</ul>
					</li>
					<li><a href="https://me.alipay.com/dwzteam" target="_blank">捐赠</a></li>
					<li><a href="changepwd.html" target="dialog" width="600">设置</a></li>
					<li><a href="http://www.cnblogs.com/dwzjs" target="_blank">博客</a></li>
					<li><a href="http://weibo.com/dwzui" target="_blank">微博</a></li>
					<li><a href="http://bbs.dwzjs.com" target="_blank">论坛</a></li> -->
                    <li><a href="javascript:void(0);">${user.username}</a></li>
					<li><a href="${ctx }/j_spring_security_logout">退出</a></li>
				</ul>
				<!-- <ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<li theme="red"><div>红色</div></li>
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul> -->
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<c:forEach items="${menus }" var="level0Menu">
						<c:forEach items="${level0Menu.children }" var="level1Menu">
							<c:if test="${level1Menu.show }">
							<!-- 一级菜单开始 -->
									<div class="accordionHeader">
										<h2><span>Folder</span>${level1Menu.name }</h2>
									</div>	
									<div class="accordionContent">
										<ul class="tree treeFolder expand">
										<c:forEach items="${level1Menu.children }" var="level2Menu">
											<c:if test="${level2Menu.show }">
											<c:set var="joinC" value="${fn:contains(level2Menu.href, '?') ? '&' : '?' }"/>
											<li><a href="${ctx}/${level2Menu.href }${joinC }navTabId=${level2Menu.id }" target="navTab" rel="${level2Menu.id }">${level2Menu.name }</a></li>
											</c:if>
										</c:forEach>
										</ul>
									</div>
							</c:if>		
						</c:forEach>
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">系统概况</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">系统概况</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<%--<div class="alertInfo">
								<h2><a href="doc/dwz-user-guide.pdf" target="_blank">DWZ框架使用手册(PDF)</a></h2>
								<a href="doc/dwz-user-guide.swf" target="_blank">DWZ框架演示视频</a>
							</div>--%>
							<%--<div class="right">
								<p><a href="doc/dwz-user-guide.zip" target="_blank" style="line-height:19px">DWZ框架使用手册(CHM)</a></p>
								<p><a href="doc/dwz-ajax-develop.swf" target="_blank" style="line-height:19px">DWZ框架Ajax开发视频教材</a></p>
							</div>--%>
							<p><span>运营管理系统</span></p>
							<p>集中管理</p>
						</div>
						
						<div style="margin-left:10px;margin-top:10px;margin-bottom: 10px;;">
							<span style="color:red">官方微博 <a href="###" target="_blank">http://weibo.com/xxx</a></span>
							<span style="color:red; margin-left:100px;">官方QQ群 <a href="###" target="_blank">xxxxxx</a></span>
							</div>
						
						<div class="pageFormContent" layoutH="80" style="background:url(${ctx}/res/dwz/themes/css/images/pageForm-background22.png) no-repeat;background-size:100% 100%;">
							<div class="pageFormContent2"  style="margin-right:230px">
							</div>		


	</div>

	<div id="footer">Copyright &copy; 2013 <a href="demo_page2.html" target="dialog"></a></div>

<script>
	//setInterval(checkNewOrder, 10000);
	
	function checkNewOrder() {
		$.ajax({
			url : "${ctx}/admin/index/checkNewOrder",
			dataType : "text",
			global: false,
			success : function(data) {
				if ("" == data) {
				} else if (data.length > 50) {
				} else {
					 alertMsg.info(data);
				}
			}
		});
	}
</script>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-11-25
  Time: 下午5:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理平台</title>
<link href="${ctx}/res/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<%-- <a href="javascript:void(0);">
				<img src="${ctx}/res/image/logo.png" /></a> --%>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#"></a></li>
					</ul>
				</div>
				<div style="margin-left:20px;padding-top:10px;">
				<img src="${ctx }/res/image/lock.jpg"/><h2 class="login_title" style="display:inline;font-weight:bold;font-size:15pt;padding-left:0px;">登陆运营管理平台</h2>
				</div>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form id="form" action="${ctx }/j_spring_security_check" method="post">
					<p>
						<label>用户名：</label>
						<input type="text" name="j_username" size="20" value="" class="login_input"/>
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="j_password" size="20" class="login_input" value="" />
					</p>
					<%-- <p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="${ctx}/res/dwz/themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p> --%>
					
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
				<p></p>
				<span style="color:red;">
					<c:if test="${!empty(param.error) }">
						用户名或密码错误
					</c:if>
				</span>
			</div>
			<div class="login_banner"><img src="${ctx}/res/dwz/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				
				<!-- <ul class="helpList">
					<li><a href="#">下载驱动程序</a></li>
					<li><a href="#">如何安装密钥驱动程序？</a></li>
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
				</ul>
				<div class="login_inner">
					<p>您可以使用 网易网盘 ，随时存，随地取</p>
					<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>
					<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p>
				</div> -->
			</div>
		</div>
		<div id="login_footer">
		</div>
	</div>
	<script type="text/javascript">
		//var form = document.getElementById('form');
		//form.submit();
	</script>
</body>
</html>
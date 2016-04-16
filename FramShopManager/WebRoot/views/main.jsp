<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>欢迎使用畅农后台管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script language="javascript" type="text/javascript">
  function resizeIframe(obj) {
    obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
  }
</script>

</head>

<body>
	<div id="container">
		<div id="leftMenu">

			<div id="text_nav">导航</div>
			<ul id="list_nav">
				<li><a href="User/UserManager.action" target="rightFrame">用户管理</a></li>
				<li><a href="Product/ProductManager.action" target="rightFrame">商品管理</a></li>
				<li><a href="Order/OrderManager.action" target="blank">订单管理</a></li>
				<li><a href="Access/AccessManager.action" target="rightFrame">评价管理</a></li>
				<li><a href="FeedBack/FeedBackManager.action" target="rightFrame">用户反馈</a></li>
				<li><a href="Information/InformationManager.action" target="rightFrame">信息发布</a></li>
			</ul>

		</div>

		<div id="rightContent">
			<div align="right">
				欢迎您&nbsp;<a href="Admin/PasswordChange.action"><%=session.getAttribute("userID")%></a>&nbsp;&nbsp;
				<a href="Admin/SystemExit.action" style="margin-right: 10px">退出</a>
			</div>
			<iframe name="rightFrame" src="views/right_bg.jsp" scrolling="no"
				width="100%" height="100%" frameborder="0" onload='javascript:resizeIframe(this);'></iframe>
		</div>
	</div>
</body>
</html>

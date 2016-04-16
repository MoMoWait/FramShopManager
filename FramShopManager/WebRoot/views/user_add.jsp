<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户信息修改</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/user_add.css">
<script type="text/javascript">
	function cancel_add() {
		history.back();
	}

	function isMobile(tag) {
		var v = document.getElementById("tf_user_phone").value;
		//支持13*,15*,18*
		var r = /^1[3458]\d{9}$/;
		if ("" != v) {

			if (!r.test(v)) {
				alert("请填写正确的手机号码");
				return false;
			} else {
				return true;
			}

		}
	}
</script>
</head>

<body bgcolor="#e4e4e4">
	<div id="container">

		<div id="title" align="center">用户信息添加</div>

		<s:form action="CommitAdd" method="post" namespace="/User">

			<div align="left" style="margin-left: 10px">
				<div>
					姓名:
					<s:textfield id="tf_user_name" name="user.name" />
				</div>
				<div>
					性别:
					<s:select id="sl_user_sex" list="{'男','女'}" name="user.sex"></s:select>
				</div>
				<div>
					地址:
					<s:textfield id="tf_user_address" name="user.address" />
				</div>
				<div>
					号码:
					<s:textfield id="tf_user_phone" name="user.phoneNumber" onchange="isMobile()"/>
				</div>
				<s:submit id="sb_user_sub" value="确认添加" align="left"></s:submit>
				<button id="btn_cancel_change" type="button" onclick="cancel_add()">取消添加</button>
			</div>
		</s:form>

	</div>
</body>
</html>

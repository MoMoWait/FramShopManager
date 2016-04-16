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

<title>密码修改</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/password/password_show.css">

<script type="text/javascript">
	function cancel_change(){
		history.back();
	}
</script>
</head>

<body bgcolor="#e4e4e4">
	<div align="center">
		<div>密码修改</div>
		<s:form action="PasswordChange" namespace="/Admin" method="post">
			<div>
				<s:password id="tf_orgin_password" type="text" name="orignPassword" placeholder="原密码"></s:password>
			</div>

			<div>
				<s:password id="tf_new_password" type="password" name="newPassword" placeholder="新密码"></s:password>
			</div>

			<div>
				<s:password id="tf_commit_new_password" type="password" name="commitNewPassword" placeholder="确认新密码"></s:password>
			</div>
			
			<s:submit id="sb_password_change" value="确认更改" style="margin-top:10px;"></s:submit>
			<button type="button" id="btn_cancel_change" style="margin-top: 10px;" onclick="cancel_change()">取消更改</button>
		</s:form>
	</div>
</body>
</html>

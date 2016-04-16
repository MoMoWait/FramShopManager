<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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

<title>信息发布</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/information/information_show.css">
<script type="text/javascript">
 function checkNull(){
 	if(document.information_form.des.value.length==0){
 		alert("请输入内容");
 		document.information_form.des.focus();
 		return false;
 	}
 	return true;
 }
 </script>
</head>

<body bgcolor="#e4e4e4">
	<div id="container" align="left">
		<div id="title">信息发布</div>
		<s:form  name="information_form" action="CommitAdd" method="post" enctype="multipart/form-data" namespace="/Information"
		  onsubmit="return checkNull()">
			<div>
				图片:
				<s:file id="photo_id" name="photoFile"></s:file>
			</div>

			<div>
				描述:<br />
				<s:textarea id="tf_information_des" name="des"></s:textarea>

			</div>
			<s:submit id="sb_information_sub" value="确认发布"></s:submit>
		</s:form>
	</div>
</body>
</html>

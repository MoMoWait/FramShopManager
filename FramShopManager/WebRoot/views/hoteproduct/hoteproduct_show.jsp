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

<title>最热商品管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css"
	href="css/feedback/feedback_show.css">
</head>

<body bgcolor="#e4e4e4">
	<div id="container">
		<div id="title">最热商品管理</div>
		<s:form action="CommitChange" namespace="/HoteProduct" method="post">
			<table id="tb_feedbacks">
				<tr id="tb_head">
					<td width="10%">类别</td>
					<td width="10%">位置1</td>
					<td width="40%">位置2</td>
					<td width="40%">位置3</td>
					<td width="10%">位置4</td>
				</tr>
				<tr>
					<td>花果山</td>
					<s:if test="">
					
					</s:if>
				</tr>
				<s:iterator value="feedbacks">
					<tr class="tr_feedbacks_content">
						<td><s:property value="name" /></td>
						<td><s:property value="content" /></td>
						<td><s:property value="time" /></td>
						<s:url action="FeedBackManager" id="deleteUrlID">
							<s:param name="changeID">${id}</s:param>
							<s:param name="type">${type}</s:param>
							<s:param name="deleteFeedBack">true</s:param>
							<s:param name="currentPage">${currentPage}</s:param>
							<s:param name="searchType">${searchType}</s:param>
							<s:param name="searchValue">${searchValue}</s:param>
						</s:url>
						<td><s:a href="%{deleteUrlID}">删除</s:a></td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
	</div>



	<s:else>
		<div>暂无记录</div>
	</s:else>



</body>
</html>

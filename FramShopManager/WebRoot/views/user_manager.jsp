<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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

<title>用户管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/user_manager.css">
<script type="text/javascript" charset="utf-8">
	function goSpecialPage(){
		var page=document.getElementById("tf_go_page").value;
		window.location.href="User/UserManager.action?goPage="+page+"&goSpecial=true&type=<%=request.getParameter("type")%>"+
		"&searchType=<%=request.getParameter("searchType")%>"+"&searchValue=<%=request.getParameter("searchValue")%>";
	}

	function search(){
		var type=document.getElementById("sl_search_id").value;
		var searchValue=document.getElementById("search_id").value;
		window.location.href="User/UserManager.action?type=search&searchType="+type+"&searchValue="+searchValue;
		
	}
	
	function changeSearchName(){
		var searchValue=document.getElementById("search_id").value;
		if(searchValue=="null"){
			document.getElementById("search_id").value="";
		}
	}
	
</script>

</head>

<body bgcolor="#e4e4e4">
	<div id="container">
		<div id="title">用户管理</div>
		<div>
			<s:select  id="sl_search_id" name="searchType" list="{'帐号','姓名','地址','手机号码'}"/>
			<s:textfield id="search_id" name="searchValue" placeholder="输入搜索内容" onchange="changeSearchName()"></s:textfield>
			<button onclick="search()">搜索</button>
			&nbsp;&nbsp;&nbsp;
			<s:a href="views/user_add.jsp">添加</s:a>
			<br />

		</div>
	</div>
	<s:if test="userNumber>0">
		<table id="tb_users">
		<tr id="tb_head">
			<td width="10%">帐号</td>
			<td width="10%">姓名</td>
			<td width="10%">性别</td>
			<td width="30%">地址</td>
			<td width="10%">账户余额</td>
			<td width="10%">手机号码</td>
			<td width="20%">操作</td>
		</tr>
		<s:iterator value="users">
			<tr class="tr_users_content">
				<td><s:property value="ID" /></td>
				<td><s:property value="name" /></td>
				<td><s:property value="sex" /></td>
				<td><s:property value="address" /></td>
				<td><s:property value="account" /></td>
				<td><s:property value="phoneNumber" /></td>
				<s:url action="UserChange" id="changeUrlID">
					<s:param name="changeID">${ID}</s:param>
					<s:param name="type">${type}</s:param>
					<s:param name="currentPage">${currentPage}</s:param>
					<s:param name="searchType">${searchType}</s:param>
					<s:param name="searchValue">${searchValue}</s:param>
				</s:url>
					<s:url action="UserManager" id="deleteUrlID">
					<s:param name="changeID">${ID}</s:param>
					<s:param name="type">${type}</s:param>
					<s:param name="deleteUser">true</s:param>
					<s:param name="currentPage">${currentPage}</s:param>
					<s:param name="searchType">${searchType}</s:param>
					<s:param name="searchValue">${searchValue}</s:param>
				</s:url>
				<td><s:a href="%{changeUrlID}">修改</s:a>&nbsp;&nbsp;&nbsp;<s:a
					href="%{deleteUrlID}">删除</s:a></td>
			</tr>
		</s:iterator>
	</table>
	<br />

	<div align="center">
		共
		<s:property value="userNumber" />
		条&nbsp;&nbsp;第
		<s:property value="currentPage" />
		/
		<s:property value="pages" />
		页 <a href="User/UserManager.action?currentPage=${currentPage-1}&type=${type}&searchType=${searchType}&searchValue=${searchValue}">上一页</a>&nbsp;&nbsp;<a
			href="User/UserManager.action?currentPage=${currentPage+1}&type=${type}&searchType=${searchType}&searchValue=${searchValue}">下一页</a>
		<s:textfield id="tf_go_page" name="goPage" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></s:textfield>
		<button onclick="goSpecialPage()">跳转</button>
	</div>
	</s:if>
	<s:else>
		<div>暂无记录</div>
	</s:else>
	

</body>
</html>

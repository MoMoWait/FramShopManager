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

<title>商品管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/product/product_show.css">
<script type="text/javascript" charset="utf-8">
	function goSpecialPage(){
		var page=document.getElementById("tf_go_page").value;
		window.location.href="Product/ProductManager.action?goPage="+page+"&goSpecial=true&type=<%=request.getParameter("type")%>"+
		"&searchType=<%=request.getParameter("searchType")%>"+"&searchValue=<%=request.getParameter("searchValue")%>";
	}

	function search(){
		var type=document.getElementById("sl_search_id").value;
		var searchValue=document.getElementById("search_id").value;
		window.location.href="Product/ProductManager.action?type=search&searchType="+type+"&searchValue="+searchValue;
		
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
		<div id="title">商品管理</div>
		<div>
			<s:select  id="sl_search_id" name="searchType" list="{'编号','名称','类别','描述'}"/>
			<s:textfield id="search_id" name="searchValue" placeholder="输入搜索内容" onchange="changeSearchName()"></s:textfield>
			<button onclick="search()">搜索</button>
			&nbsp;&nbsp;&nbsp;
			<s:a href="views/product/product_add.jsp">添加</s:a>
			<br />

		</div>
	</div>
	<s:if test="productNumber>0">
		<table id="tb_products">
		<tr id="tb_head">
			<td width="20%">图片</td>
			<td width="10%">编号</td>
			<td width="10%">名称</td>
			<td width="10%">类别</td>
			<td width="20%">描述</td>
			<td width="10%">单价</td>
			<td width="20%">操作</td>
		</tr>
		<s:iterator value="products">
			<tr class="tr_products_content">
				<td><img src=" <s:property value="productPhoto"/>" width="80px" height="60px"></td>
				<td><s:property value="productID" /></td>
				<td><s:property value="productName" /></td>
				<td><s:property value="productType" /></td>
				<td><s:property value="productDes" /></td>
				<td><s:if test="productPrice>0.0001"><s:property value="productPrice" />元/斤</s:if><s:if test="productBagPrice>0.0001"><br/><s:property value="productBagPrice" />元/袋</s:if></td>
				<s:url action="ProductChange" id="changeUrlID">
					<s:param name="changeID">${productID}</s:param>
					<s:param name="type">${type}</s:param>
					<s:param name="currentPage">${currentPage}</s:param>
					<s:param name="searchType">${searchType}</s:param>
					<s:param name="searchValue">${searchValue}</s:param>
				</s:url>
					<s:url action="ProductManager" id="deleteUrlID">
					<s:param name="changeID">${productID}</s:param>
					<s:param name="type">${type}</s:param>
					<s:param name="deleteProduct">true</s:param>
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
		<s:property value="productNumber" />
		条&nbsp;&nbsp;第
		<s:property value="currentPage" />
		/
		<s:property value="pages" />
		页 <a href="Product/ProductManager.action?currentPage=${currentPage-1}&type=${type}&searchType=${searchType}&searchValue=${searchValue}">上一页</a>&nbsp;&nbsp;<a
			href="Product/ProductManager.action?currentPage=${currentPage+1}&type=${type}&searchType=${searchType}&searchValue=${searchValue}">下一页</a>
		<s:textfield id="tf_go_page" name="goPage" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></s:textfield>
		<button onclick="goSpecialPage()">跳转</button>
	</div>
	</s:if>
	<s:else>
		<div>暂无记录</div>
	</s:else>
	

</body>
</html>

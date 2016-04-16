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

<title>订单管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/order/order_show.css">
<script type="text/javascript" charset="utf-8">
	function goSpecialPage(){
		var page=document.getElementById("tf_go_page").value;
		window.location.href="Order/OrderManager.action?goPage="+page+"&goSpecial=true&type=<%=request.getParameter("type")%>"+
		"&searchType=<%=request.getParameter("searchType")%>"+"&searchValue=<%=request.getParameter("searchValue")%>";
	}

	function search(){
		var type=document.getElementById("sl_search_id").value;
		var searchValue=document.getElementById("search_id").value;
		window.location.href="Order/OrderManager.action?type=search&searchType="+type+"&searchValue="+searchValue;
		
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
		<div id="title">订单管理</div>
		<div>
			<s:select  id="sl_search_id" name="searchType" list="{'姓名','下单时间','期望时间','订单状态'}"/>
			<s:textfield id="search_id" name="searchValue" placeholder="输入搜索内容" onchange="changeSearchName()"></s:textfield>
			<button onclick="search()">搜索</button>
			<br />

		</div>
	</div>
	<s:if test="orderNumber>0">
		<table id="tb_orders">
		<tr id="tb_head">
			<td width="10%">姓名</td>
			<td width="10%">下单时间</td>
			<td width="10%">期望时间</td>
			<td width="10%">订单状态</td>
			<td width="10%">订单总额</td>
			<td width="10%">购买商品</td>
			<td width="10%">联系电话</td>
			<td width="20%">送货地址</td>
			<td width="10%">操作</td>
		</tr>
		<s:iterator value="orders">
			<tr class="tr_orders_content">
				<td><s:property value="name"/></td>
				<td><s:property value="orderTime" /></td>
				<td><s:property value="expireTime" /></td>
				<td><s:property value="orderState" /></td>
				<td><s:property value="orderMoney" /></td>
				<td><s:property value="products" /></td>
				<td><s:property value="phoneNumber"/></td>
				<td><s:property value="address"/><s:if test="city!=null&&city!=''">(配送:<s:property value="city"/>)</s:if></td>
				<s:url action="OrderChange" id="changeUrlID">
					<s:param name="changeID">${orderID}</s:param>
					<s:param name="type">${type}</s:param>
					<s:param name="currentPage">${currentPage}</s:param>
					<s:param name="searchType">${searchType}</s:param>
					<s:param name="searchValue">${searchValue}</s:param>
				</s:url>
					<s:url action="OrderManager" id="deleteUrlID">
					<s:param name="changeID">${orderID}</s:param>
					<s:param name="type">${type}</s:param>
					<s:param name="deleteOrder">true</s:param>
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
		<s:property value="orderNumber" />
		条&nbsp;&nbsp;第
		<s:property value="currentPage" />
		/
		<s:property value="pages" />
		页 <a href="Order/OrderManager.action?currentPage=${currentPage-1}&type=${type}&searchType=${searchType}&searchValue=${searchValue}">上一页</a>&nbsp;&nbsp;<a
			href="Order/OrderManager.action?currentPage=${currentPage+1}&type=${type}&searchType=${searchType}&searchValue=${searchValue}">下一页</a>
		<s:textfield id="tf_go_page" name="goPage" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></s:textfield>
		<button onclick="goSpecialPage()">跳转</button>
	</div>
	</s:if>
	<s:else>
		<div>暂无记录</div>
	</s:else>
	

</body>
</html>

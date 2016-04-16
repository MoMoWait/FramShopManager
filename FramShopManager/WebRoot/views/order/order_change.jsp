<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/order/order_change.css">
	<script type="text/javascript">
		function cancel_change(){
			history.back();
		}
	
	</script>
  </head>
  
  <body bgcolor="#e4e4e4">
   	<div id="container" align="left">
   	
   		<div id="title" align="center">订单信息修改</div>
   		
   		<s:form action="CommitChange" method="post"  namespace="/Order">
   		<s:set name="type">${type }</s:set>
   		<s:set name="currentPage">${currentPage }</s:set>
   		<s:set name="searchType">${searchType}</s:set>
   		<s:set name="searchValue">${searchValue}</s:set>
   		<s:set name="order.orderID">${order.orderID}</s:set>
   		<s:hidden name="type" value="%{type}"></s:hidden>
   		<s:hidden name="currentPage" value="%{currentPage}"></s:hidden>
   		<s:hidden name="searchType" value="%{searchType}"></s:hidden>
   		<s:hidden name="searchValue" value="%{searchValue}"></s:hidden>	
   		<s:hidden name="order.orderID" value="%{order.orderID}"></s:hidden>	
   		<div>下单时间:<s:textfield id="tf_order_id" name="order.orderTime"  readonly="true"/>
   			   期望时间:<s:textfield id="tf_order_password" name="order.expireTime"/>
   		</div>
   		
   		<div>订单总额:<s:textfield id="tf_order_name" name="order.orderMoney" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/>
   			   订单状态:<s:select id="sl_order_sex" list="{'未发货','已发货','已完成'}" name="order.orderState"></s:select>
   		</div>
   		
		<s:submit id="sb_order_sub" value="确认修改" style="margin-left:10px;"></s:submit>
		
		<button id="btn_cancel_change" type="button" style="margin-left: 10px" onclick="cancel_change()">取消修改</button>
   		</s:form>
   		
   	</div>
  </body>
</html>

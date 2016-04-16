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
    
    <title>商品信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/product/product_change.css">
	<script type="text/javascript">
		function cancel_change(){
			history.back();
		}
	
	</script>
  </head>
  
  <body bgcolor="#e4e4e4">
   	<div id="container" align="left">
   	
   		<div id="title" align="center">商品信息修改</div>
   		
   		<s:form action="CommitChange" method="post" enctype="multipart/form-data" namespace="/Product">
   		<s:set name="type">${type }</s:set>
   		<s:set name="currentPage">${currentPage }</s:set>
   		<s:set name="searchType">${searchType}</s:set>
   		<s:set name="searchValue">${searchValue}</s:set>
   		<s:set name="product.productPhoto">${product.productPhoto}</s:set>
   		<s:hidden name="type" value="%{type}"></s:hidden>
   		<s:hidden name="currentPage" value="%{currentPage}"></s:hidden>
   		<s:hidden name="searchType" value="%{searchType}"></s:hidden>
   		<s:hidden name="searchValue" value="%{searchValue}"></s:hidden>	
   		<s:hidden name="product.productPhoto" value="%{product.productPhoto}"></s:hidden>	
   		<div>编号:&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="tf_user_id" name="product.productID"  readonly="true"/>
   			   名称:<s:textfield id="tf_user_password" name="product.productName"/>
   		</div>
   		
   		<div>单价(斤):<s:textfield id="tf_user_name" name="product.productPrice" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/>
   			   类别:<s:select id="sl_user_sex" list="{'花果山','朋香阁','其他'}" name="product.productType"></s:select>
   		</div>
   		
   		<div>单价(袋):<s:textfield id="tf_product_bagprice" name="product.productBagPrice" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/></div>
   		<div>图片:&nbsp;&nbsp;&nbsp;&nbsp;<s:file name="photoFile" ></s:file><br/><br/>
   			<img width="300px" height="225px" src="${product.productPhoto }">
   		</div>
   		
   		<div>描述:<br/>
   			<s:textarea id="tf_user_account" name="product.productDes"></s:textarea>
   			<!--<s:textfield id="tf_user_account" name="product.productDes" />  -->
   			
   		</div>
		<s:submit id="sb_user_sub" value="确认修改" style="margin-left:10px;"></s:submit>
		
		<button id="btn_cancel_change" type="button" style="margin-left: 10px" onclick="cancel_change()">取消修改</button>
   		</s:form>
   		
   	</div>
  </body>
</html>

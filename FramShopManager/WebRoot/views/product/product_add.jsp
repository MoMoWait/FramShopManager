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
    <title>商品信息添加</title>
    
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
   	
   		<div id="title" align="center">商品信息添加</div>
   		
   		<s:form action="CommitAdd" method="post" enctype="multipart/form-data" namespace="/Product">
   		<div>名称:&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="tf_user_password" name="product.productName"/>
   			   类别:<s:select id="sl_user_sex" list="{'花果山','朋香阁','其他'}" name="product.productType"></s:select>
   		</div>
   		
   		<div>单价(斤):<s:textfield id="tf_user_name" name="product.productPrice" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/>
   			   图片:<s:file name="photoFile" ></s:file>
   		</div>
   		<div>单价(袋):<s:textfield id="tf_product_bagprice" name="product.productBagPrice" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/></div>
   		
   		<div>描述:<br/>
   			<s:textarea id="tf_user_account" name="product.productDes"></s:textarea>
   			<!--<s:textfield id="tf_user_account" name="product.productDes" />  -->
   			
   		</div>
		<s:submit id="sb_user_sub" value="确认添加" style="margin-left:10px;"></s:submit>
		
		<button id="btn_cancel_change" type="button" style="margin-left: 10px" onclick="cancel_change()">取消添加</button>
   		</s:form>
   		
   	</div>
  </body>
</html>

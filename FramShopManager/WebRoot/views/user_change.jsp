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
    
    <title>用户信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/user_change.css">
	<script type="text/javascript">
		function cancel_change(){
			history.back();
		}
	
	</script>
  </head>
  
  <body bgcolor="#e4e4e4">
   	<div id="container" align="center">
   	
   		<div id="title">用户信息修改</div>
   		
   		<s:form action="CommitChange" method="post" namespace="/User">
   		<s:set name="type">${type }</s:set>
   		<s:set name="currentPage">${currentPage }</s:set>
   		<s:set name="searchType">${searchType}</s:set>
   		<s:set name="searchValue">${searchValue}</s:set>
   		<s:hidden name="type" value="%{type}"></s:hidden>
   		<s:hidden name="currentPage" value="%{currentPage}"></s:hidden>
   		<s:hidden name="searchType" value="%{searchType}"></s:hidden>
   		<s:hidden name="searchValue" value="%{searchValue}"></s:hidden>	
   		<div>帐号:<s:textfield id="tf_user_id" name="user.ID"  readonly="true"/>
   			   密码:<s:textfield id="tf_user_password" name="user.password"/>
   		</div>
   		
   		<div>姓名:<s:textfield id="tf_user_name" name="user.name"/>
   			   性别:<s:select id="sl_user_sex" list="{'男','女'}" name="user.sex"></s:select>
   		</div>
   		
   		<div>地址:<s:textfield id="tf_user_address" name="user.address"/>
   		</div>
   		
   		<div>余额:<s:textfield id="tf_user_account" name="user.account" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/>
   			   号码:<s:textfield id="tf_user_phone" name="user.phoneNumber"/>
   		</div>
		<s:submit id="sb_user_sub" value="确认修改"></s:submit>
		
		<button id="btn_cancel_change" type="button" onclick="cancel_change()">取消修改</button>
   		</s:form>
   		
   	</div>
  </body>
</html>

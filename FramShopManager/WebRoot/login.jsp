<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户登录</title>
<link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap-responsive.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">

<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
</head>
<body class="login1">
	<div id="loginModal" class="modal show">
  		<div class="modal-dialog">
    		<div class="modal-content">
    			<div class="modal-header">
        			<h1 class="text-center text-primary" style="color:#ff0000">登录</h1>
      			</div>
      			<div class="modal-body">
                	<div class="col-md-1">
                    </div>
        			<s:form action="Login" method="post" class="form col-md-9 center-block" namespace="/User">
          				<div class="form-group">
                        	<div class="input-group">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-user"></i>
								</span> 
           				 		<input type="text" class="form-control input-lg" name="userID" placeholder="帐号"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                            </div>
          				</div>
          				<div class="form-group">
                        	<div class="input-group">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-lock"></i>
								</span> 
            					<input type="password" class="form-control input-lg" name="password" placeholder="密码">
                            </div>
          				</div>
          				<div class="form-group">
            				<button type="submit" class="btn btn-primary btn-lg btn-block">立即登录&nbsp;<i class="icon-circle-arrow-right"></i></button>
          				</div>
        			</s:form>
      			</div>
      			<div class="modal-footer">
        		</div>
    		</div>
  		</div>
	</div>
    
<script src="http://cdn.bootcss.com/jquery/1.7.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.2/js/bootstrap.js"></script>
</body>
</html>
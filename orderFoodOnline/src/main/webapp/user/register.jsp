<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

%>
<script  type="text/javascript">
basePath =<%=basepath%>;
</script>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>注册页面 - 在线订餐系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="assets/css/ace.min.css" id="main-ace-style" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" />
		<![endif]-->
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
		<script src="assets/js/ace-extra.min.js"></script>
		<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout blur-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<span class="white">在线订餐系统</span>
								</h1>
							</div>

							<div class="space-6">
							</div>

							<div class="position-relative">
			
								<div id="signup-box" class="signup-box widget-box no-border visible" style="color:blue">
									<div class="widget-body">
										<div class="widget-main">
										<h4 class="header blue lighter bigger">
												用户注册
											</h4>
										<div class="space-6"></div>
											<form id='registerForm'>
											<fieldset>
											       <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<span style="color:gray">买家</span>
															<input type="radio" name="usertype"  value="买家"/>
															<span style="color:gray">卖家</span>
															<input type="radio" name="usertype" value="卖家"/>
														</span>
													</label> 
													
													
											<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" name="username"/>
														</span>
												</label>	
													
											   
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control" placeholder="密码" />
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="repeatPassword" placeholder="确认密码" />
														</span>
													</label>
												
												<label class="block clearfix">   
											    <span
													class="block input-icon input-icon-right"> 
													<input type="text" class="form-control" name="cellPhoneNumber" placeholder="手机号码"/>
												</span>
												</label> 
												
												 <label class="block clearfix">   
											    <span
													class="block input-icon input-icon-right"> 
													<input  type="email" class="form-control" name="email" placeholder="电子邮箱" />
												</span>
												</label> 

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<span class="bigger-110">重置</span>
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-primary white" onclick="register()">
															<span class="bigger-110" >注册</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" onclick="login()" style="color:white">
												已有账号，去登录
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.signup-box -->
							</div><!-- /.position-relative -->

						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

		<!-- inline scripts related to this page -->
		
		<script type="text/javascript">
			function  register(){
				var url=path+'/user/register.do';
				var form=$('#registerForm').serialize();
				$.ajax({
					url:url,
					data:form,
					dataType:'json',
					method:'post',
					success:function(data){
						if(data.isSuccess){
							alert('注册成功');
							window.location.href=path+'/'+data.url;
							}else{
								}
					}
				});
			}

			function login(){
				var url=path+'/user/loginPage.do';
				window.location.href=url;
			}
			
			function getRootPath() {
	            var pathName = window.location.pathname.substring(1);
	            var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	            if (webName == "") {
	                return window.location.protocol + '//' + window.location.host;
	            }
	            else {
	                return window.location.protocol + '//' + window.location.host + '/' + webName;
	            }
	        }
            path=getRootPath();
		</script>
	</body>
</html>

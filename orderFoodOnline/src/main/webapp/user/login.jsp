<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

%>
<script  type="text/javascript">
basePath='<%=basepath%>';
</script>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>登录页面 - 在线订餐系统</title>
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
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<div class="space-6"></div>

											<form id="loginForm">
												<fieldset>
												
												   <label class="block clearfix">
														<span class="input-icon input-icon-right">
															<input type="radio" name="userType" value="买家" class="userType" id="buyer"/>买家
														</span>
														<span class="input-icon input-icon-right">
															<input type="radio" name="userType" value="卖家" class="userType" id="seller"/>卖家
														</span>
													</label>
												
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="username" class="form-control" placeholder="用户名" id="username"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control" placeholder="密码" id="password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace"  id="rememberMe"/>
															<span class="lbl"> 记住我</span>
														</label>

														<button type="button" class="width-35 pull-right btn btn-sm btn-primary"  onclick="login()">
															<span class="bigger-110" >登录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										</div><!-- /.widget-main -->

										<div class="toolbar clearfix">
											<!-- <div>
												<a href="#" data-target="#forgot-box" class="forgot-password-link">
													<i class="ace-icon fa fa-arrow-left"></i>
													忘记密码
												</a>
											</div>-->
											<div class="">
												<a href="#" onclick="register()" data-target="#signup-box" class="white">
													用户注册
												</a>
											</div>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->

								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>
												重置密码
											</h4>

											<div class="space-6"></div>
											<p>
												输入您注册时候的email，用以接收密码重置信息
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="ace-icon fa fa-lightbulb-o"></i>
															<span class="bigger-110">发送!</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /.widget-main -->

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												返回登录
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.forgot-box -->
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
        <script type="text/javascript" src="<%=basepath%>/resources/plugins/jquery-cookie/1.4/jquery.cookie.js" >
        </script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
  
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			 
			 autoCompeteUserInfo();
			});

		
			
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
			
			function  login(){
				var url=path+'/user/login.do';
				var form=$('#loginForm').serialize();
				$.ajax({
					url:url,
					data:form,
					dataType:'json',
					method:'post',
					success:function(data){
						if(data.isSuccess){
						            if ($('#rememberMe').prop('checked')) {
						            	var username=$('#username').val();
								        var password=$('#password').val();
								        var isBuyerChecked=$('#buyer').prop('checked');
								        var buyer=$('#buyer').val();
								        var seller=$('#seller').val();
								        var userInfo;
								        if(isBuyerChecked){
								        	userInfo=username+'_'+password+'_'+1;
								        }else{
								        	userInfo=username+'_'+password+'_'+2;
								        }
						            		  $.cookie('userInfo',userInfo, {expires:7,path:'/'});
						            	  } else {
						            		  $.removeCookie('userInfo',{path:'/'});
						            }
							window.location.href=path+data.url;
						}
						else{
							alert("用户名或密码错误");
						}
					}
				});
			}
			
			function register(){
				var url=path+'/user/registerPage.do';
				window.location.href=url;
			}
			
		function	autoCompeteUserInfo(){
			var userInfo=new String($.cookie('userInfo'));
			if(userInfo.split('_').length==3){
				var userDetail=userInfo.split('_');
				var username=userDetail[0];
				var password=userDetail[1];
				var userType=userDetail[2];
				$('#rememberMe').attr('checked','checked');
				$('#username').val(username);
				$('#password').val(password);
				if(userType==1){
					userType="买家";
				}else{
					userType="卖家";
				}
				$("input:radio[value='"+userType+"']").attr('checked','true');
			}
			
			}
		</script>
	</body>
</html>
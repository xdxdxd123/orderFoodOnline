<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>我的店铺</title>

		<meta name="description" content="This is page-header (.page-header &gt; h1)" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/css/ace.min.css" id="main-ace-style" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
		<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
		
		<link rel="stylesheet" href="/orderFoodOnline/resources/plugins/bootstrap-table-v1.12.1/bootstrap-table.css"/>
	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default">
			<div class="navbar-container" id="navbar-container">

				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="javascript:void(0)" class="navbar-brand">
						<small>
							在线订餐系统
						</small>
					</a>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">

						<!-- <li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">5</span>
							</a>

						</li> -->

						<!-- #section:basics/navbar.user_menu -->
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									欢迎您<br />
									<span id="username"></span>
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<!-- 	<li>
									<a href="#">
										<i class="ace-icon fa fa-cog"></i>
										系统设置
									</a>
								</li>

								<li>
									<a href="profile.html">
										<i class="ace-icon fa fa-user"></i>
										个人信息设置
									</a>
								</li>

								<li class="divider"></li>
 -->
								<li>
									<a href="#" id='logout'>
										<i class="ace-icon fa fa-power-off"></i>
										<input type="hidden" id="username"/>
										登出
									</a>
								</li>
							</ul>
						</li>

						<!-- /section:basics/navbar.user_menu -->
					</ul>
				</div>

				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">

			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar responsive">


				<ul class="nav nav-list">
					<li class="active">
						<a href="javascript:void(0)">
							<i class="menu-icon fa fa-tachometer"></i>
							<span class="menu-text"> 店铺管理 </span>
						</a>

						<b class="arrow"></b>
					</li>

					<li >
						<a href="#" onclick="orderManage()">
							<i class="menu-icon fa fa-list"></i>
							<span>订单管理</span>
						</a>
					</li>

					<li >
						<a href="#" id="productManage">
							<i class="menu-icon fa fa-list"></i>
							<span class="menu-text" >商品管理</span>
							<input type="hidden" id="userId" value="${userId}">
							<input type="hidden" id="shopId">
						</a>

						<b class="arrow"></b>
					</li>

					<li >
						<a href="#" onclick="productTypeManage()">
							<i class="menu-icon fa fa-list"></i>
							<span>商品类型管理</span>
						</a>
					</li>
				</ul><!-- /.nav-list -->
			</div>

			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<!-- #section:basics/content.breadcrumbs -->
				<div class="breadcrumbs" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="index.html">店铺首页</a>
						</li>
					</ul><!-- /.breadcrumb -->

					<!-- #section:basics/content.searchbox -->
				<!-- 	<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="请输入关键字 ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div> -->
					<!-- /.nav-search -->

					<!-- /section:basics/content.searchbox -->
				</div>
				<div class="page-content">
					<!-- /section:settings.box -->
					<div class="page-content-area" id='pageContent'>

					</div><!-- /.page-content-area -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			<div class="footer">
				<div class="footer-inner">
					<!-- #section:basics/footer -->
					<div class="footer-content">
						<span class="bigger-120">
							在线订餐系统 &copy;2018
						</span>
					</div>

					<!-- /section:basics/footer -->
				</div>
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div>
		
		<!-- /.main-container -->

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
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="assets/js/bootstrap.min.js"></script>
		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="assets/js/jquery-ui.custom.min.js"></script>
		<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
        <script src="/orderFoodOnline/resources/plugins/bootstrap-table-v1.12.1/bootstrap-table.js"></script>
        <script src="/orderFoodOnline/resources/plugins/bootstrap-table-v1.12.1/bootstrap-table-zh-CN.js"></script>
        <!-- 商品管理 -->
       <script  type="text/javascript">
       $(function(){
    	   path=getRootPath();
    	   getShopId();
    	   loadData()
       });
       
       function loadData(){
			var url=path+"/user/findById.do";
			var userId=$('#userId').val();
				$.ajax({
					url:url,
					data:{
						userId:userId
					},
					method:'get',
					dataType:'json',
					success:function(data){
						if(data){
							$('#username').text(data.returnJson);
						}
					}
				});
			}
       
       //商品列表
    	  $("#productManage").on('click',function(event){
    		var url=path+'/product/index.do';
    	$('#pageContent').load(url);
    		 event.stopPropagation();  
    	  });
       
       
      //商品类型列表
       function productTypeManage(){
    	   var shopId=$('#shopId').val();
    	   alert(shopId);
    	   var url=path+'/producttype/index.do?shopId='+shopId;
    	   $('#pageContent').load(url);
  		 event.stopPropagation(); 
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
  
   //注销
   $('#logout').on('click',function(){
	   var  userId=$('#userId').val();
		  window.location.href=path+"/user/logout.do?userId="+userId;
	   });
   
   //获得店铺id
   function getShopId(){
	 var url=path+"/shop/selectShopByUserId.do";
	 $.ajax({
		 url:url,
		 data:{
			 userId:'${userId}'
		 },
		 dataType:'json',
	     success:function(data){
	    	 $('#shopId').val(data.returnJson);
	    	 
	     }
	 });
   }
   
   function orderManage(){
	   var userId=$('#userId').val();
	   var shopId=$('#shopId').val();
	   var url=path+"/order/listPage.do";
	   $('#pageContent').load(url);
   }
       </script>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<style  rel="stylesheet"  type="text/css">
.myRow{
margin-top: 10px;
margin-bottom:10px;
}
	</style>
	
	<div class="container">
	
	<div class="row myRow" col-md-10>
			<div class="col-md-10">
			<button id="btn_add" type="button" class="btn btn-default btn-primary">
				<span class="glyphicon glyphicon-plus" aria-hidden="true">新增</span>
			</button>
			</div>
	</div>
	
	<div class="row myRow" col-md-10> 
		<div class="col-md-10">
		<table id="addressList" class="table">
		</table>
		<div class="clearfix"></div>
		</div>
	</div>
	</div>
	 </div>
	

			<script type="text/javascript">
			   function loadTable(){
			    	  $('#addressList').bootstrapTable({
			    		  url: path+'/address/list.do',         //请求后台的URL（*）
			              method: 'post',                      //请求方式（*）
			              cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			              pagination: true,                   //是否显示分页（*）
			              sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
			              pageNumber: 1,                       //初始化加载第一页，默认第一页
			              pageSize: 10,                       //每页的记录行数（*）
			              pageList: [10, 25],        //可供选择的每页的行数（*）
			              queryParams : queryParams,//传递参数（*）
			              queryParamsType : '',
			              contentType: "application/x-www-form-urlencoded",
			              minimumCountColumns: 2,             //最少允许的列数
			              uniqueId: "addressId",                     //每一行的唯一标识，一般为主键列
			              showColumns:false,
			              			              columns: [
			              {
			                  field: 'addressId',
			                  visible:false
			              }, {
			                  field: 'province',
			                  title: '省份',
			                  align: 'center'
			              },{
			                  field: 'city',
			                  title: '城市',
			                  align: 'center'
			              },{
			                  field: 'detailAddress',
			                  title: '详细地址',
			                  align: 'center'
			              },{
			                  field: 'contact',
			                  title: '联系方式',
			                  align: 'center'
			              },
			              {
			                  field: 'operate',
			                  title: '操作',
			                  align: 'center',
			                  formatter: operateFormatter //自定义方法，添加操作按钮
			              },
			              ],
			    	  });
			      }

			  
			   
			   function operateFormatter(value,row,index){
				   var operate="<a href='#' onclick=\"modifyAddress(\'"+row.addressId+"\',"+"-1)\">修改</a>&nbsp;&nbsp;"+
		           "<a href='#' onclick=\"delAddress(\'"+row.addressId+"\')\">删除</a>&nbsp;&nbsp;";
				  if(row.defaultAddr=='-1'){
					  operate=operate+ "<a href='#' onclick=\"modifyAddress(\'"+row.addressId+"\',"+"1)\">设为默认</a>";
				  }
				       return    operate;
			   }
			   
			   $('#btn_add').on('click',function(){
				   var userId=$('#userId').val();
				   var url=path+'/address/addPage.do?userId='+userId;
				   $('#pageContent').load(url);
			   });
			 
			 //修改地址
			 function modifyAddress(id,flag){
				 var userId=$('#userId').val();
				 if(flag==1){
					 var url = path + "/address/modify.do?addressId="+id+"&flag=1&userId="+userId;
					 $.ajax({
						 url:url,
						 method:'post',
						 success:function(){
							 $('#addressList').bootstrapTable('destroy');
			            		loadTable();
			            		alert("");
						 }
					 });
					
				 }else{
					 var url=path+"/address/addPage.do?addressId="+id+"&userId="+userId;
					 $('#pageContent').load(url);
				 }
			 };
			 //删除地址
			 function delAddress(id){
				 var url=path+"/address/del.do?addressId="+id;
				 $.ajax({
					 url:url,
		             dataType:'json',
		             success:function(res){
		            	 if(res.isSuccess){
		            		$('#addressList').bootstrapTable('destroy');
		            		loadTable();
		            	 }
		             }
				 });
			 };
	       var  userId=$("#userId").val();
			 function  queryParams(params){
				 params={
						 pageSize:params.pageSize,
						 pageNumber:params.pageNumber,
						 userId:userId
				 }
				 return params;
			 }
			 
			 loadTable();
			</script>
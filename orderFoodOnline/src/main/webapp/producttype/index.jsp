<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<style  rel="stylesheet"  type="text/css">
.myRow{
margin-top: 10px;
margin-bottom:10px;
}
	</style>
	
	<div class="container">
	
	<div class="row myRow col-md-10" style="border:1px solid #E8E8E8;">
	
<form class="form-inline" role="form" id="searchForm"> 
<div class="row myRow">
     <div class="col-md-1">
    </div>
    <div class="form-group col-md-2"> 
     <input type="text" id="productTypeName" name="productTypeName" placeholder="请输入商品类型名称" /> 
    </div> 
    <div class="col-md-9"></div>
</div>

			<div class="row myRow">
			 <div class="col-md-4"></div>
				<button type="reset" class="btn btn-primary" onclick="resetQueryParams()">重置</button>
				<button type="button" class="btn btn-primary" onclick="search()">搜索</button>
			</div>
	</div>


		</form>
  
   
	<div class="row myRow" col-md-10>
			<div class="col-md-10">
			<button id="btn_add" type="button" class="btn btn-default btn-primary">
				<span class="glyphicon glyphicon-plus" aria-hidden="true">新增</span>
			</button>
			</div>
	</div>
	
	<div class="row myRow" col-md-10> 
		<div class="col-md-10">
		<table id="productTypeList" class="table">
		</table>
		<div class="clearfix"></div>
		</div>
	</div>
	</div>
	 </div>
	

			<script type="text/javascript">
			   function loadTable(){
			    	  $('#productTypeList').bootstrapTable({
			    		  url: path+'/producttype/list.do',         //请求后台的URL（*）
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
			              uniqueId: "productTypeId",                     //每一行的唯一标识，一般为主键列
			              showColumns:false,
			              			              columns: [
			              {
			                  field: 'productTypeId',
			                  visible:false
			              }, {
			                  field: 'productTypeName',
			                  title: '商品类型名称',
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
				   return  "<a href='#' onclick=\"modifyProductType(\'"+row.productTypeId+"\')\">修改</a>&nbsp;&nbsp;"+
				           "<a href='#' onclick=\"delProductType(\'"+row.productTypeId+"\')\">删除</a>&nbsp;&nbsp;"
			   }
			   
			   $('#btn_add').on('click',function(){
				   var shopId=$('#shopId').val();
				   var url=path+'/producttype/addPage.do?shopId='+shopId;
				   $('#pageContent').load(url);
			   });
			   
			   var shopId=$('#shopId').val();
			 //查询参数
				 function   queryParams(params) {
					var productTypeName=$('#productTypeName').val();
					params={
				     shopId:shopId,
				     pageNumber:params.pageNumber,
				     pageSize:params.pageSize,
				     productTypeName:productTypeName
					 }
					return params;
				}
			 
			 //修改商品类型
			 function modifyProductType(id){
				 var url=path+"/producttype/addPage.do?productTypeId="+id+"&shopId="+shopId;
				 $('#pageContent').load(url);
			 };
			 //删除商品类型
			 function delProductType(id){
				 var url=path+"/producttype/del.do?productTypeId="+id;
				 $.ajax({
					 url:url,
		             dataType:'json',
		             success:function(res){
		            	 if(res.isSuccess){
		            		$('#productTypeList').bootstrapTable('destroy');
		            		loadTable();
		            	 }
		             }
				 });
			 };

			 function search(){
				 $('#productTypeList').bootstrapTable('destroy');
				 loadTable();
				 }
			 
			function resetQueryParams(){
				$('#searchForm')[0].reset();
				 $('#productTypeList').bootstrapTable('destroy');
				 loadTable();
			 }
			 loadTable();
			</script>
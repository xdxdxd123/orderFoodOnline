<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<style  rel="stylesheet"  type="text/css">
	.rowStyle{
	height: 10px;
	margin-top:10px;
	}
	</style>
	
	<div class="container">
	
	<div class="row row1 col-xs-10" style="border:1px solid #E8E8E8;">
	
<form class="form-inline" role="form" id="searchForm"> 
   <div class="form-group col-md-4"> 
     <input type="text" class="form-control" id="name" placeholder="请输入商品名称" /> 
    </div>
    <div class="form-group col-md-4"> 
     <input type="text" id="inputfile" placeholder="请选择商品类型" /> 
    </div> 
    <div class="form-group col-md-4"> 
     <input type="text" id="inputfile" placeholder="请输入库存" /> 
     
    </div>     <button type="reset" class="btn btn-primary">重置</button> 
    <button type="submit" class="btn btn-primary" onclick="search()">提交</button> 
    
   </form>
   </div>
  
	
	<div style="margin-top: 10px;margin-bottom: 10px">
	<div class="row rowStyle">
			<div class="col-xs-10">
			<button id="btn_add" type="button" class="btn btn-default btn-primary">
				<span class="glyphicon glyphicon-plus" aria-hidden="true">新增</span>
			</button>
			</div>
			<div class="clearfix"></div>
	</div>
	</div>
	
	<div class="row" style="margin-top: 10px"> 
		<div class="col-xs-10">
		<table id="productTypeList" class="table">
		</table>
		<div class="clearfix"></div>
		</div>
	</div>
	</div>
	

			<script type="text/javascript">
			   function loadTable(){
			    	  $('#productTypeList').bootstrapTable({
			    		  url: path+'/producttype/list.do',         //请求后台的URL（*）
			              method: 'get',                      //请求方式（*）
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
			              uniqueId: "productId",                     //每一行的唯一标识，一般为主键列
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
					
					 params={
				     shopId:shopId,
				     pageNumber:params.pageNumber,
				     pageSize:params.pageSize
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
		            	 alert(res);
		            	 if(res.isSuccess){
		            		$('#productTypeList').bootstrapTable('destroy');
		            		loadTable();
		            	 }
		             }
				 });
			 };

			 function search(){
				 var searchForm=$('#searchForm').serialize();
				 var url=path+"/product/list.do";
				 var opt={
                 url:url,
				 query:searchForm
			 };
				 $("#productTypeList").bootstrapTable('refresh', opt);
				 }
			 loadTable();
			</script>
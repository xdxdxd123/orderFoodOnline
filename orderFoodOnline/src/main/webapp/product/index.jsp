<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<style  rel="stylesheet"  type="text/css">
	.rowStyle{
	height: 10px;
	margin-bottom:20px;
	}
	.row1{
	height: 100px;
	border:1px solid #000;
	}
	
	.row3{
	margin-top: 5px;
	margin-bottom: 5px
	}
	
	
	</style>
	
	<div class="container">
	
	<div class="row row1 col-xs-10" style="border:1px solid #E8E8E8;">
<form class="form-inline" role="form" id="searchForm"> 

<div class="row row3">
<div class="form-group col-md-4"> 
     <input type="text" class="form-control" id="name" placeholder="请输入商品名称" /> 
    </div>
    <div class="form-group col-md-4"> 
     <input type="text" id="inputfile" placeholder="请选择商品类型" /> 
    </div> 
    <div class="form-group col-md-4"> 
     <input type="text" id="inputfile" placeholder="请输入库存" /> 
     
    </div>    
</div>
    <div class="row row3">
    <button type="reset" class="btn btn-primary">重置</button> 
    <button type="submit" class="btn btn-primary" onclick="search()">提交</button> 
    </div>
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
		<table id="productList" class="table">
		</table>
		<div class="clearfix"></div>
		</div>
	</div>
	</div>
	

			<script type="text/javascript">
			   function loadTable(){
			    	  $('#productList').bootstrapTable({
			    		  url: path+'/product/list.do',         //请求后台的URL（*）
			              method: 'get',                      //请求方式（*）
			              cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			              pagination: true,                   //是否显示分页（*）
			              sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
			              pageNumber: 1,                       //初始化加载第一页，默认第一页
			              pageSize: 10,                       //每页的记录行数（*）
			              pageList: [10, 25],        //可供选择的每页的行数（*）
			              queryParams : queryParams,//传递参数（*）
			              contentType: "application/x-www-form-urlencoded",
			              minimumCountColumns: 2,             //最少允许的列数
			              uniqueId: "productId",                     //每一行的唯一标识，一般为主键列
			              showColumns:false,
			              			              columns: [
			              {
			                  field: 'productId',
			                  visible:false
			              }, {
			                  field: 'productName',
			                  title: '商品名称',
			                  align: 'center'
			              },{
			                  field: 'productTypeName',
			                  title: '商品类型',
			                  align: 'center'
			              }, {
			                  field: 'stock',
			                  title: '库存',
			                  align: 'center'
			              }, {
			                  field: 'price',
			                  title: '价格（元）',
			                  align: 'center'
			              },{
			                  field: 'salePrice',
			                  title: '销售价（元）',
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
				   return  "<a href='#' onclick=\"modifyProduct(\'"+row.productId+"\')\">修改</a>&nbsp;&nbsp;"+
				           "<a href='#' onclick=\"delProduct(\'"+row.productId+"\')\">删除</a>";
				           
			   }
			   
			   $('#btn_add').on('click',function(){
				   var shopId=$('#shopId').val();
				   var url=path+'/product/addPage.do?shopId='+shopId;
				   $('#pageContent').load(url);
			   });
			   
			   var shopId=$('#shopId').val();
			 //查询参数
				 function   queryParams(params) {
					
					 params={
				     shopId:shopId,
				     pageNumber:1,
				     pageSize:10
					 }
					return params;
				}
			 
			 //修改商品
			 function modifyProduct(id){
				 var url=path+"/product/addPage.do?productId="+id+"&shopId="+shopId;
				 $('#pageContent').load(url);
			 };
			 //删除商品
			 function delProduct(id){
				 var url=path+"/product/del.do?productId="+id;
				$.ajax({
					url:url,
					dataType:'json',
					success:function(data){
						if(data.isSuccess){
							 $('#productList').bootstrapTable('destroy');
							 loadTable();
							 alert("删除商品成功");
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
				 $("#productList").bootstrapTable('refresh', opt);
				 }
			 loadTable();
			</script>
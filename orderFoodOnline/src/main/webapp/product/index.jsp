<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<style  rel="stylesheet"  type="text/css">
.myRow{
margin-top: 10px;
margin-bottom:10px;
}
	</style>

<div class="container">
	<div class="row myRow col-xs-10" style="border: 1px solid #E8E8E8;">
		<form class="form-inline" role="form" id="searchForm">
			<div class="row myRow">
				<div class="form-group col-md-4">
					<input type="text" class="form-control" id="productName" name="productName"
						placeholder="请输入商品名称" />
				</div>
				<div class="form-group col-md-4">
					<select id="productTypeId" name="productTypeId" placeholder="请选择商品类型">
					<option value="">请选择商品类型</option>
					</select>
				</div>
				<div class="form-group col-md-4">
					<input type="text" id="stock" name="stock" placeholder="请输入库存" />

				</div>
			</div>
			<div class="row myRow">
			    <div class="col-md-4"></div>
			    <div>
			    <button type="button"  class="btn btn-primary" onclick="resetQueryParams()">重置</button>
				<button type="button" class="btn btn-primary" onclick="search()">搜索</button>
			    </div>
			     <div class="col-md-4"></div>
			</div>
		</form>
	</div>

	<div class="row myRow">
		<div class="col-xs-10">
			<button id="btn_add" type="button"
				class="btn btn-default btn-primary">
				<span class="glyphicon glyphicon-plus" aria-hidden="true">新增</span>
			</button>
		</div>
		<div class="clearfix"></div>
	</div>

	<div class="row myRow" style="margin-top: 10px">
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
			              method: 'post',                      //请求方式（*）
			              cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			              pagination: true,                   //是否显示分页（*）
			              sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
			              pageNumber: 1,                       //初始化加载第一页，默认第一页
			              pageSize: 10,                       //每页的记录行数（*）
			              pageList: [10, 25],        //可供选择的每页的行数（*）
			              queryParams : queryParams,//传递参数（*）
			              queryParamsType:'',
			              contentType:"application/x-www-form-urlencoded",
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
			   
			 //查询参数
				 function   queryParams(params) {
					   var shopId=$('#shopId').val();
					   var productName=$('#productName').val();
					   var productTypeId=$('#productTypeId').val();
					   var stock=$('#stock').val();
					   if(!stock){
						   stock=-1;
					   }
					 params={
				     shopId:shopId,
				     productName:productName,
				     productTypeId:productTypeId,
				     stock:stock,
				     pageNumber:params.pageNumber,
				     pageSize:params.pageSize
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
				 $('#productList').bootstrapTable('destroy');
				 loadTable();
				 }
			 
			function resetQueryParams(){
				$('#searchForm')[0].reset();
				 $('#productList').bootstrapTable('destroy');
				 loadTable();
			 }
			
			//加载商品类型
			function loadProductType() {
				var shopId=$('#shopId').val();
				var url = path + '/producttype/list.do?shopId='+shopId;
				$.ajax({
					url : url,
					dataType : 'json',
					data:{
						pageSize:0,
						pageNumber:0
					},
					success : function(data) {
						var list = data.rows;
						var options = "";
						var length = list.length;
						for (var i = 0; i < length; i++) {
							options = options
									+ ' <option value=\''+list[i].productTypeId+'\'>'
									+ list[i].productTypeName + '</option>';
						}
						console.log("op" + options);
						$('#productTypeId').append(options);
					}
				});

			}
			loadProductType();
			 loadTable();
			</script>
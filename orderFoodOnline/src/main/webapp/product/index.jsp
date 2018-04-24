<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div>
		<div  class="btn-group">
			<button id="btn_add" type="button" class="btn btn-default btn-primary">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<button id="btn_edit" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>
			<button id="btn_delete" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
		</div>
		<table id="productList">
		</table>
	</div>
			<script type="text/javascript">
			
				loadTable();
			
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
			              height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			              uniqueId: "productId",                     //每一行的唯一标识，一般为主键列
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
				           "<a href='#' onclick='delProduct('"+row.productId+"')'>删除</a>&nbsp;&nbsp;"+
				           "<a href='#' onclick='putaway('"+row.productId+"')'>上架</a>&nbsp;&nbsp;"+
				           "<a href='#' onclick='soldOut('"+row.productId+"')'>下架</a>";
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
				 var url=path+"/product/del.do?id="+id;
				 $('#pageContent').load();
			 };
			 //上架商品
			 function putaway(id){
				 var url=path+"/product/addPage?id="
				 $('#pageContent').load();
			 };
			 //下架商品
			 function putaway(id){
				 var url=path+"/product/addPage?id="
			 };
			</script>
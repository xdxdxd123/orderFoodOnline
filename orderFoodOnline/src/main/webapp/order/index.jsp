<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<style  rel="stylesheet"  type="text/css">
	.rowStyle{
	height: 10px;
	margin-bottom:20px;
	}
	.row1{
	height: 200px;
	border:1px solid #000;
	}
	
	</style>
	
	<div class="container">
	
	<div class="row row1 col-xs-10" style="border:1px solid #E8E8E8;">
	
    <form class="form-inline" role="form" id="searchForm" action="#"> 
   <div class="form-group col-md-4"> 
     <input type="text" class="form-control" id="name" placeholder="请输入商品名称" /> 
    </div>
    <div class="form-group col-md-4"> 
     <input type="text" id="inputfile" placeholder="请选择商品类型" /> 
    </div> 
    <div class="form-group col-md-4"> 
     <input type="text" id="inputfile" placeholder="请输入库存" /> 
     
    </div>     
    <button type="reset" class="btn btn-primary">重置</button> 
    <button type="submit" class="btn btn-primary" onclick="search()">提交</button> 
     </form>
   </div>
 
	
	<div style="margin-top: 10px;margin-bottom: 10px">
	</div>
	
	<div class="row" style="margin-top: 10px"> 
		<div class="col-xs-10">
		<table id="ordertList" class="table">
		</table>
		<div class="clearfix"></div>
		</div>
	</div>
	</div>
	

			<script type="text/javascript">
			   function loadTable(){
			    	  $('#ordertList').bootstrapTable({
			    		  url: path+'/order/list.do',         //请求后台的URL（*）
			              method: 'post',                      //请求方式（*）
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
			              uniqueId: "orderId",                     //每一行的唯一标识，一般为主键列
			              showColumns:false,
			              			              columns: [
			              {
			                  field: 'orderId',
			                  visible:false
			              }, {
			                  field: 'orderCode',
			                  title: '订单号',
			                  align: 'center',
			                  visible:false
			              }, {
			                  field: 'shopName',
			                  title: '店铺名称',
			                  align: 'center',
			                  visible:false
			              },{
			                  field: 'buyer',
			                  title: '买家',
			                  align: 'center',
			                  visible:false
			              },{
			                  field: 'orderTotalPrice',
			                  title: '订单总共支付（元）',
			                  align: 'center'
			              },{
			            	  field:'buyersOrderStatus',
			            	  title:'订单状态',
			            	  align:'center',
			            	  visible:false
			              },{
			            	  field:'shopOrderStatus',
			            	  title:'订单状态',
			            	  align:'center',
			            	  visible:false
			              },{
			                  field: 'operate',
			                  title: '操作',
			                  align: 'center',
			                 /*  formatter: operateFormatter */ //自定义方法，添加操作按钮
			              },
			              ],
			    	  });
			      }

			  
			   
			    function operateFormatter(value,row,index){
			    	if(row.userType==1){
			    		if(buyersOrderStatusValue=='1'){
			    			return "<a href='#' onclick='modifyOrderStatus('"+row.orderId+","+"6"+","+"5"+"')'>取消</a>";
			    		}
			    		else if(buyersOrderStatusValue=='3'){
			    			return  "<a href='#' onclick='modifyOrderStatus('"+row.orderId+","+"4"+","+"4"+"')'>确认收货</a>";
			    		} else {
			    			return "";
			    		}
			    		
			    	}else{
			    		if(shopOrderStatusValue=='1'){	    			
			    			   return "<a href='#' onclick='modify(\'"+row.orderId+","+"2"+","+"2"+"\')\">接单</a>&nbsp;&nbsp;" +
					           "<a href='#' onclick='modifyOrderStatus('"+row.orderId+","+"6"+","+"6"+"')'>取消</a>&nbsp;&nbsp;";
			    		}else if(shopOrderStatusValue=='2'){
			    			return "<a href='#' onclick='modifyOrderStatus(\'"row.orderId+","+"3"+","+"3"+"\')\">发货</a>"
			    		}else{
			    			return "";
			    		}
			    	}
				   
			   }
			  
			   
			   var shopId=$('#shopId').val();
			   var userId=$('#userId').val();
			   alert(userId);
			 //查询参数
				 function   queryParams(params) {
					 params={
				     shopId:shopId,
				     userId:userId,
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
				 $('#pageContent').load();
			 };
			 //上架商品
			 function putaway(id){
				 var url=path+"/product/onSale.do?productId="+id;
				 $('#pageContent').load();
			 };
			 //下架商品
			 function putaway(id){
				 var url=path+"/product/onSale.do?productId="+id;
			 };

			 function search(){
				 var searchForm=$('#searchForm').serialize();
				 var url=path+"/product/list.do";
				 var opt={
                 url:url,
				 query:searchForm
			 };
				 $("#orderList").bootstrapTable('refresh', opt);
				 }
			 loadTable();
			 
			 function modifyOrderStatus(orderId,buyersOrderStatus,shopOrderStatus){
				 var url=path+"order/list.do";
				 $.ajax({
					 url:url,
					 data:{
						 orderId:orderId,
						 buyersOrderStatus:buyersOrderStatus,
						 shopOrderStatus:shopOrderStatus
					 },
					 type:'post',
					 success:function(){
						 loadTable();
					 }
				 });
			 }
			</script>
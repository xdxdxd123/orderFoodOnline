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
	.pagination-info{
	display:none;
	}
	div{
    margin:0 auto;	
	}
	
	.row{
	margin:5px 0px 5px 0px;
	}
	</style>
	
	<div class="container">
	<span style="color:gray">订单列表</span>
	<div style="margin-top: 10px;margin-bottom: 10px">
	</div>
	
	<div class="row">
	<div class="col-md-1"></div>
	<div style="margin-top: 10px;" class="col-md-10"> 
		<table id="ordertList" class="table table-hover"></table>
	</div>
	<div class="col-md-1"></div>
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
			              queryParamsType:'',
			              contentType: "application/x-www-form-urlencoded",
			              minimumCountColumns: 2,             //最少允许的列数
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
			                  width:'10%',
			              }, {
			                  field: 'shopName',
			                  title: '店铺名称',
			                  width:'10%',
			                  align: 'center',
			                  visible:false
			              },{
			                  field: 'buyer',
			                  title: '买家',
			                  align: 'center',
			                  width:'10%',
			                  visible:false
			              },{
			                  field: 'orderTotalPrice',
			                  title: '订单总共支付（元）',
			                  align: 'center',
			                  width:'10%'
			              },{
			            	  field:'buyersOrderStatus',
			            	  title:'订单状态',
			            	  align:'center',
			            	  visible:false,
			            	  width:'10%',
			              },{
			            	  field:'shopOrderStatus',
			            	  title:'订单状态',
			            	  align:'center',
			            	  visible:false,
			            	  width:'10%'
			              },{
			                  field: 'operate',
			                  title: '操作',
			                  align: 'center',
			                  width:'10%',
			                  formatter: operateFormatter  //添加操作
			              }
			              ],
			              onLoadSuccess:function(data){
			            	  if(data.userType==1){
			            		  $('#ordertList').bootstrapTable('showColumn', "buyersOrderStatus");
						    	  $('#ordertList').bootstrapTable('showColumn', "shopName");
			            	  }else{
			            		  $('#ordertList').bootstrapTable('showColumn', "shopOrderStatus");
						    	  $('#ordertList').bootstrapTable('showColumn', "buyer");
			            	  }
			              }
			    	  });
			      }

			  
			   
			    function operateFormatter(value,row,index){
			    	if(row.userType==1){
			    		if(row.buyersOrderStatusValue=='1'){
			    			return "<a href=\'#\' onclick=\"modifyOrderStatus(\'"+row.orderId+"\',"+"\'5\'"+","+"\'5"+"')\">取消</a>";
			    		}
			    		else if(row.buyersOrderStatusValue=='3'){
			    			return  "<a href=\'#\' onclick=\"modifyOrderStatus(\'"+row.orderId+"\',"+"\'4\'"+","+"\'4"+"')\">确认收货</a>";
			    		} else {
			    			return "";
			    		}
			    		
			    	}else{
			    		if(row.shopOrderStatusValue=='1'){	
			    			   return "<a href=\'#\' onclick=\"modifyOrderStatus(\'"+row.orderId+"\',"+"\'2\'"+","+"\'2"+"')\">接单</a>&nbsp;&nbsp;" +
					           "<a href=\'#\' onclick=\"modifyOrderStatus(\'"+row.orderId+"\',"+"\'5\'"+","+"\'5"+"')\">取消</a>";
			    		}else if(row.shopOrderStatusValue=='2'){
			    			return "<a \'#\' onclick=\"modifyOrderStatus(\'"+row.orderId+"\',"+"\'3\'"+","+"\'3"+"')\">发货</a>"
			    		}else{
			    			return "";
			    		}
			    	}
				   
			   }
			  
			   
			   var shopId=$('#shopId').val();
			   var userId=$('#userId').val();
			 //查询参数
				 function   queryParams(params) {
				 console.log(params);
					 params={
				     shopId:shopId,
				     userId:userId,
				     pageNumber:params.pageNumber,
				     pageSize:params.pageSize
					 }
					return params;
				}

			 function reloadTableData(){
				 $("#ordertList").bootstrapTable('destroy');
				 loadTable();
			 }
			 
			 function search(){
				// var searchForm=$('#searchForm').serialize();
				 var url=path+"/product/list.do";
				 var opt={
                 url:url,
				 //query:searchForm
			 };
				 $("#ordertList").bootstrapTable('refresh', opt);
				 }
			 
			 loadTable();
			 function modifyOrderStatus(orderId,buyersOrderStatus,shopOrderStatus){
				 var url=path+"/order/update.do";
				 $.ajax({
					 url:url,
					 data:{
						 orderId:orderId,
						 buyersOrderStatus:buyersOrderStatus,
						 shopOrderStatus:shopOrderStatus
					 },
					 type:'post',
					 success:function(){
						reloadTableData();
						 return false;
						 }
				 });
			 }
			</script>
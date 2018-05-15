<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!--     <div  style="margin: 20px 100px 20px 100px">
					<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="商品" class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
							<span   class="input-icon btn btn-primary">搜索</span>
					</form>
					
					</div> -->
    <div class="col-md-12">
    <input id="shopId" value="${shopId}" type="hidden">
    <c:forEach items="${productList}" var="product">
	<div class="col-md-3" style="height: 200px;border:1px solid #E0E0E0;margin: 5px 5px 5px 5px;">
		<div style="margin:20px 5px 20px 5px">
			<input type="hidden" value="${product.productId}">
		<div class="col-md-6" style="height: 100px;width: 100px">
			<img
				src="/orderFoodOnline/product/getImage.do?imagePath=${product.image}"
				width="100%" height="100%" style="display: inline-block;">
		</div>
		<div class="col-md-6">
			<div>
			<span>商品名称:${product.productName}</p>
			</div>
			<div>
				<div class="col-md-2" style="margin-right: 2px;margin-bottom: 2px">
					<span style="color: red">&yen;${product.salePrice}</span>
				</div>
				<div class="col-md-10" style="width:200px">
				<button class="glyphicon glyphicon-minus" onclick="operateShoppingCart('${product.productId}',-1)" id="${product.productId}minus"  style="display:none"></button>	
				<input id="productCount${product.productId}" type="hidden"  value='0' style="display:inline;width:31px;height:20px" readonly="readonly"/>
				<button class="glyphicon glyphicon-plus" onclick="operateShoppingCart('${product.productId}',1)"  style="display:inline"></button>
				</div>
			</div>
		</div>
		</div>
	
	</div>
</c:forEach>  
    </div>
    <div>
   <!--  <button class="btn btn-primary" style="float:left" onclick="returnShopList()">返回店铺列表</button> -->
    <button class="btn btn-primary" style="float:right;" onclick="goPay()">去结算</button>
    </div>
    <script  type="text/javascript">
    //购物车增加或删除商品
    var productTotalSelected=0;
function operateShoppingCart(productId,flag){
	var productCount=$('#productCount'+productId);
	var count=parseInt(productCount.val());
	var userId=$('#userId').val();
	if(flag==1){
		productTotalSelected++;
		count++;
		}else{
			productTotalSelected;
        count--;
			}
	productCount.val(count);
	if(count>0){
		productCount.attr("type","text");
		$('#'+productId+'minus').css("display","inline");
		}else{
			$('#'+productId+'minus').css("display","none");
			productCount.attr("type",'hidden');
			}
	var url=path+"/shoppingCart/operate.do";
	$.ajax({
     url:url,
     type:'post',
     data:{
     "productId":productId,
     "productQuantity":count,
     "flag":flag,
     "userId":userId
         },
     dataType:'json',
     success:function(data){
     productSum();
         },
     error:function(){
     productSum(); 
     }
		});
	}
    
 function goPay(){
	 if(productTotalSelected>0){
		 var userId=$('#userId').val();
		 var shopId=$('#shopId').val();
		 var url=path+"/shoppingCart/goPay.do?userId="+userId+"&shopId="+shopId;
	    $('#pageContent').load(url);
	 }else {
		 alert("请选择商品");
	 }
	
    }
 
 function returnShopList(){
	 var userId=$('#userId').val();
	 var shopId=$('#shopId').val();
	  var  url=path+"/shop/getShops.do"; 
	$('#pageContent').load(url);
 }
    </script>
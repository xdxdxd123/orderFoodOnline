<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    
    <div  style="margin: 20px 100px 20px 100px">
					<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="商品" class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
							<span   class="input-icon btn btn-primary">搜索</span>
					</form>
					
					</div>
    <div class="col-md-12">
    
    <c:forEach items="${productList}" var="product">

	<div class="col-md-3" style="height: 200px;border:1px solid #E0E0E0;margin: 5px 5px 5px 5px;">
		<input type="hidden" value="${product.productId}">
		<div class="col-md-7" style="height: 100px;width: 100px">
			<img
				src="/orderFoodOnline/product/getImage.do?imagePath=${product.image}"
				width="100%" height="100%" style="display: inline-block;">
		</div>
		<div class="col-md-1"></div>
		<div class="col-md-4">
			<p>${product.productName}</p>
			<div>
				<div class="col-md-2">
					<span style="color: red">&yen;${product.salePrice}</span>
				</div>
				<div class="col-md-2">
				<button class="glyphicon glyphicon-minus" onclick="operateShoppingCart('${product.productId}',-1)" id="${product.productId}minus"  style="display:none"></button>	
				<input id="productCount${product.productId}" type="hidden"  value='0' />
				<button class="glyphicon glyphicon-plus" onclick="operateShoppingCart('${product.productId}',1)" ></button>
				</div>
			</div>
		</div>
	</div>
</c:forEach>  
    </div>
    
    <div>
    <button class="btn-primary" style="float:right;height:20px;width:25">去结算</button>
    </div>
    <script  type="text/javascript">
    //购物车增加或删除商品
function operateShoppingCart(productId,flag){
	var productCount=$('#productCount'+productId);
	console.log(productCount);
	var count=parseInt(productCount.val());
	alert(productCount.val());
	console.log("sps"+count);
	if(flag==1){
		count++;
		}else{
        count--;
			}
	alert(count);
	productCount.val(count);
	if(count>0){
		productCount.type='text';
		$('#'+productId+'minus').css("display","inline");
		}else{
			$('#'+productId+'minus').css("display","none");
			productCount.type='hidden';
			}
	var url=path+"/shoppingCart/operate.do";
	$.ajax({
     url:url,
     data:{
     "productId":productId,
     "productQuantity":count,
     "flag":flag,
         },
     succcess:function(message){
         }
		});
	}
    </script>
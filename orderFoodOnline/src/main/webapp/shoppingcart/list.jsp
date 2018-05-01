<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<form id="form" action="#" method='post'>
    <input type="hidden" id="shoppingCartId" value="${shoppingCartId}"/>
     <input type="hidden" name="shopId" value="${shopId}"/>
     <input type="hidden" name="userId" value="${userId }"/>
     <table class="table table-responsive">
    <tbody>
    <tr>
    <td>商品名称</td>
    <td>商品价格</td>
    <td>数量</td>
    <td>小结</td>
     </tr> 
     <c:forEach items="${shoppingCartItemList}" var="shoppingCartItem" varStatus="status">
      <input type="checkbox" name="shoppingCartItemIds" value="${shoppingCartItem.shoppingCartItemId}" checked="checked" style="display:none">
      <input type="checkbox" name="productQuantitys" value="${shoppingCartItem.productQuantity}" checked="checked" style="display:none">
      <input type="checkbox" name="sums" value="${shoppingCartItem.sum}" checked="checked" style="display:none">
      <input type="checkbox" name="productIds" value="${productList[status.count-1].productId}" checked="checked" style="display:none">
       <tr>
       <td>${productList[status.count-1].productName} </td>
       <td>${productList[status.count-1].salePrice}</td>
       <td>${shoppingCartItem.productQuantity}</td>
       <td>${shoppingCartItem.sum}</td>
       </tr>
    </c:forEach>
    </tbody>
    </table>
    <div>
    <div style="display:inlne;">总共:&yen;${totalPrice}
    <input type="hidden" name="totalPrice" value="${totalPrice}">
    </div>
    <button class="btn btn-primary" style="float:right" onclick="pay()">立即支付</button>
    </div>
   </form>
<script type="text/javascript">
function pay(){
	if(window.confirm("确定支付吗")){
		var formData=$('#form').serialize();
		var url=path+"/order/orderCreate.do";
	$('#pageContent').load(url,formData);
		
	 /*    $.ajax({
	    url:url,
	    type:'post',
	    data:formData,
	    success:function(){
	    },
	    error:function(){
	    }
	    }); */
	}
	}
    </script>
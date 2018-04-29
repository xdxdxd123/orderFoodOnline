<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <input type="hidden" id="shoppingCartId" value="${shoppingCartId}"/>
    
    <table class="table">
    <tr>
    <td>商品名称</td>
    <td>商品价格</td>
    <td>数量</td>
    <td>小结</td>
    </tr>
     <c:forEach items="${shoppingCartItemList}" var="shoppingCartItem">    
        <input type="hidden" value="${shoppingCartItem.}">
       <tr>
       <td></td>
       <td></td>
       <td>${shoppingCartItem.productQuantity}</td>
       <td></td>
       </tr>
    </c:forEach>
    </table>
    <div>
    <button class="btn btn-primary" style="float:right">立即支付</button>
    </div>
   

    <script type="text/javascript">
function visitShop(shopId){
	var url=path+"/product/getDetail.do?shopId="+shopId;
	$('#pageContent').load(url);
}

function getProduct(id){
	
}
    </script>
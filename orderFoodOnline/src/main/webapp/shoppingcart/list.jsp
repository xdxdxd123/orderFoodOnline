<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <input type="hidden" id="shoppingCartId" value="${shoppingCartId}"/>
    <table class="table table-responsive">
    <tbody>
    <tr>
    <td>商品名称</td>
    <td>商品价格</td>
    <td>数量</td>
    <td>小结</td>
     </tr> 
     <c:forEach items="${shoppingCartItemList}" var="shoppingCartItem" varStatus="status">
         <input type="hidden" value="${shoppingCartItem.shoppingCartItemId}">
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
    <div style="display:inlne;">总共:${totalPrice}</div>
    <button class="btn btn-primary" style="float:right" onclick="pay()">立即支付</button>
    </div>
<script type="text/javascript">
function pay(){
	
}
    </script>
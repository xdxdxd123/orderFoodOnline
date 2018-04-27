<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="col-md-12"></div>
    <c:forEach items="${shopList}" var="shop">  
        <div class="col-md-2" style="height:250px">  
        <input type="hidden" value="${shop.shopId}">
        <a href="#" onclick="visitShop('${shop.shopId}')"> <img src=""  
            width="170" height="170" style="display: inline-block;">  
        </a>  
        <p>  
            ${shop.shopname}
        </p>   
        <p>
           <span style="font-color:blue">公告:<span>${shop.notice} 
        </p>
        </div>  
    </c:forEach>  
    
    <script>
function visitShop(shopId){
	var url=path+"/product/getDetail.do?shopId="+shopId;
	$('#pageContent').load(url);
}
    </script>
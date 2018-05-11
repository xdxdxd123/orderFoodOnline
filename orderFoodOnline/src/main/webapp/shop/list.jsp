<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

    %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="col-md-12">
    <c:forEach items="${shopList}" var="shop">  
        <div class="col-md-2" style="height:250px;border:1px solid 	#E8E8E8;margin: 2px 2px 2px 2px">  
        <input type="hidden" value="${shop.shopId}">
        <div style="margin-top: 2px">
          <a href="#" onclick="visitShop('${shop.shopId}')"> 
        <img src="<%=basepath%>${shop.img}"  
            width="170" height="170" style="display: inline-block;">  
        </a>  
        </div>
        <p>  
            ${shop.shopname}
        </p>   
        <p>
           <span style="color:blue">公告:<span>${shop.notice} 
        </p>
        </div>  
    </c:forEach>  
    </div>
    <script>
function visitShop(shopId){
	var url=path+"/product/getDetail.do?shopId="+shopId;
	$('#pageContent').load(url);
}
    </script>
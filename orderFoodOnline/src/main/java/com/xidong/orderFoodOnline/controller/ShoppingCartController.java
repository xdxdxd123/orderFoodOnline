package com.xidong.orderFoodOnline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xidong.orderFoodOnline.model.ShoppingCart;
import com.xidong.orderFoodOnline.model.ShoppingCartItem;
import com.xidong.orderFoodOnline.service.IProductService;
import com.xidong.orderFoodOnline.service.IShopService;
import com.xidong.orderFoodOnline.service.IShoppingCartItemService;
import com.xidong.orderFoodOnline.service.IShoppingCartService;
import com.xidong.orderFoodOnline.service.IUserService;
import com.xidong.orderFoodOnline.util.JsonVo;

@Controller
@RequestMapping(value="/shoppingCart")
public class ShoppingCartController {
@Autowired
private IShoppingCartItemService shoppingCartItemService;
@Autowired
private IShoppingCartService shoppingCartService;
@Autowired
private IUserService userService;
@Autowired
private IShopService shopService;
@Autowired
private IProductService productService;

public void setShopService(IShopService shopService) {
	this.shopService = shopService;
}
public void setShoppingCartItemService(IShoppingCartItemService shoppingCartItemService) {
	this.shoppingCartItemService = shoppingCartItemService;
}

public void setShoppingCartService(IShoppingCartService shoppingCartService) {
	this.shoppingCartService = shoppingCartService;
}

public void setUserService(IUserService userService) {
	this.userService = userService;
}

	@RequestMapping(value="/operate")
 public @ResponseBody JsonVo operateShoppingCart(ShoppingCartItem shoppingCartItem,HttpServletRequest request,int flag ){
		JsonVo json=new JsonVo();
		HttpSession session= request.getSession();
		String userId=(String) session.getAttribute("userId");
		try {
			ShoppingCart shoppingCart=shoppingCartService.getByUserId(userId);
			String shoppingCartId=shoppingCart.getShoppingCartId();
			String productId=shoppingCartItem.getProductId();
			ShoppingCartItem shoppingCartItem2= shoppingCartItemService.getByIds(shoppingCartId, productId);
			//添加商品
			if(flag==1){
				if(shoppingCartItem2==null){
					shoppingCartItemService.save(shoppingCartItem2);
				}else{
					shoppingCartItem2.setProductQuantity(shoppingCartItem.getProductQuantity());
					shoppingCartItemService.update(shoppingCartItem2);
				}
			}else{   //删除商品
				if(shoppingCartItem2==null){
					shoppingCartItemService.save(shoppingCartItem2);
				}else{
					if(shoppingCartItem.getProductQuantity()==0){
						shoppingCartItemService.del(shoppingCartItem2.getShoppingCartItemId());
					}else {
						shoppingCartItem2.setProductQuantity(shoppingCartItem.getProductQuantity());
						shoppingCartItemService.update(shoppingCartItem2);
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}

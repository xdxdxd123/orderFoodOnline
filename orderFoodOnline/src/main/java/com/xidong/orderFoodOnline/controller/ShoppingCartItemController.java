package com.xidong.orderFoodOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xidong.orderFoodOnline.model.ShoppingCart;
import com.xidong.orderFoodOnline.service.IShoppingCartItemService;
import com.xidong.orderFoodOnline.service.IShoppingCartService;
import com.xidong.orderFoodOnline.service.IUserService;

@Controller
@RequestMapping(value="/shoppingCartItem")
public class ShoppingCartItemController {

	@Autowired
	private IShoppingCartItemService shoppingCartItemService;
	@Autowired
	private IUserService UserService;
	@Autowired
	private IShoppingCartService shoppingCartService;
	
	@RequestMapping(value="/getSum")
	public @ResponseBody int getSum(String userId){
		try {
			ShoppingCart shoppingCart=shoppingCartService.getByUserId(userId);
			return shoppingCartItemService.getSum(shoppingCart.getShoppingCartId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}

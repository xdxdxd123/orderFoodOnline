package com.xidong.orderFoodOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xidong.orderFoodOnline.service.IShoppingCartItemService;

@Controller
@RequestMapping(value="/shoppingCartItem")
public class ShoppingCartItemController {

	@Autowired
	IShoppingCartItemService shoppingCartItemService;
	
	@RequestMapping(value="/getSum")
	public @ResponseBody int getSum(){
		try {
			return shoppingCartItemService.getSum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}

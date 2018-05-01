package com.xidong.orderFoodOnline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/order")
public class OrderController {

	@RequestMapping(value="/orderCreate")
	public String  orderCreate(){
		return null;
	}
}

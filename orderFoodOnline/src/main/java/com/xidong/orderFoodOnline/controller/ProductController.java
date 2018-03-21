package com.xidong.orderFoodOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.service.IProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private IProductService productService;
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
 void  addProduct(Product product){
	  try {
		productService.addProduct(product);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	 void  modifyProduct(Product product){
		  try {
			productService.modifyProduct(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	@RequestMapping(value="/del",method=RequestMethod.POST)
	 void  delProduct(Product product){
		  try {
			productService.delProduct(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	@RequestMapping(value="/select",method=RequestMethod.POST)
	 void  selectProduct(String shopId){
		  try {
			productService.selectAllProduct(shopId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}

package com.xidong.orderFoodOnline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xidong.orderFoodOnline.dao.IShopDao;
import com.xidong.orderFoodOnline.model.Shop;
import com.xidong.orderFoodOnline.service.IShopService;

@Controller
@RequestMapping(value="/shop")
public class ShopController {
	@Autowired
 private  IShopService shopService;
public void setShopService(IShopService shopService) {
	this.shopService = shopService;
}
	/**
	 *添加商店
	 * @param shop
	 */
	@RequestMapping(value="/add" ,method=RequestMethod.POST)
	public  void addShop(Shop shop){
		try {
			shopService.addShop(shop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="del",method=RequestMethod.POST)
	public void delShop(Shop shop){
		try {
			shopService.delShop(shop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="selectAll",method=RequestMethod.POST)
	public List<Shop> selectAllShop(){
		try {
		return 	shopService.selectAllShop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="selectByShopUserId",method=RequestMethod.POST)
	public void selectShop(String userId){
		try {
			shopService.selectShopByUserId(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

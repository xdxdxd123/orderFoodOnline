package com.xidong.orderFoodOnline.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xidong.orderFoodOnline.model.Shop;
import com.xidong.orderFoodOnline.model.User;
import com.xidong.orderFoodOnline.service.IShopService;
import com.xidong.orderFoodOnline.service.IUserService;
import com.xidong.orderFoodOnline.util.JsonVo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/shop")
public class ShopController {
	@Autowired
 private  IShopService shopService;
	@Autowired
	private IUserService userService;
	
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
			//默认信息
			User user=userService.findUserById(shop.getUserid()); 
			shop.setNotice("欢迎光临");
			shop.setShopname(user.getUsername());
			shop.setImg("/resources/picture/shop_default.jpg");
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
	
	@RequestMapping(value="/getShops",method=RequestMethod.GET)
	public String  selectAllShop(Model model){
		try {
		List<Shop>  shops	=shopService.selectAllShop();
		model.addAttribute("shopList", shops);
		return 	"shop/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/selectShopByUserId")
	public @ResponseBody String  selectShop(String userId){
		try {
			JsonVo json=new JsonVo();
			json.setReturnJson(shopService.selectShopByUserId(userId).getShopId());
		return  JSONObject.fromObject(json).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 店铺图片
	 * @return
	 */
	public  String getImage(String filePath){
		return filePath;
	}
}

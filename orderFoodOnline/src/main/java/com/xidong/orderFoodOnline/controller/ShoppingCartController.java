package com.xidong.orderFoodOnline.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xidong.orderFoodOnline.model.Address;
import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.model.ShoppingCart;
import com.xidong.orderFoodOnline.model.ShoppingCartItem;
import com.xidong.orderFoodOnline.service.IAddressService;
import com.xidong.orderFoodOnline.service.IProductService;
import com.xidong.orderFoodOnline.service.IShopService;
import com.xidong.orderFoodOnline.service.IShoppingCartItemService;
import com.xidong.orderFoodOnline.service.IShoppingCartService;
import com.xidong.orderFoodOnline.service.IUserService;
import com.xidong.orderFoodOnline.util.JsonVo;
import com.xidong.orderFoodOnline.util.UUIDUtil;

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
@Autowired
private IAddressService addressService;

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
 public @ResponseBody JsonVo operateShoppingCart(ShoppingCartItem shoppingCartItem,HttpServletRequest request,int flag,String userId ){
		JsonVo json=new JsonVo();
		
		try {
			ShoppingCart shoppingCart=shoppingCartService.getByUserId(userId);
			String shoppingCartId=shoppingCart.getShoppingCartId();
			String productId=shoppingCartItem.getProductId();
			Product product=productService.selectProductById(productId);
			ShoppingCartItem shoppingCartItem2= shoppingCartItemService.getByIds(shoppingCartId, productId);
			//添加商品
			if(flag==1){
				if(shoppingCartItem2==null){
					shoppingCartItem.setShoppingCartItemId(UUIDUtil.getUUID());
					shoppingCartItem.setShoppingCartId(shoppingCartId);
					shoppingCartItem.setSum(product.getSalePrice().multiply(new BigDecimal(shoppingCartItem.getProductQuantity())));
					shoppingCartItemService.save(shoppingCartItem);
					
				}else{
					shoppingCartItem2.setProductQuantity(shoppingCartItem.getProductQuantity());
					shoppingCartItem2.setSum(product.getSalePrice().multiply(new BigDecimal(shoppingCartItem2.getProductQuantity())));
					shoppingCartItemService.update(shoppingCartItem2);
				}
			}else{   //删除商品
					if(shoppingCartItem.getProductQuantity()==0){
						if(shoppingCartItem2!=null){
							shoppingCartItemService.del(shoppingCartItem2.getShoppingCartItemId());
						}
					}else {
						shoppingCartItem2.setProductQuantity(shoppingCartItem.getProductQuantity());
						shoppingCartItem2.setSum(product.getSalePrice().multiply(new BigDecimal(shoppingCartItem2.getProductQuantity())));
						shoppingCartItemService.update(shoppingCartItem2);
					}
			}
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	
	@RequestMapping(value="goPay")
	public  String  goPay(HttpServletRequest request,Model model,String userId,String shopId){
		try {
			ShoppingCart shoppingCart =shoppingCartService.getByUserId(userId);
		List<ShoppingCartItem>	shoppingCartItemList=shoppingCartItemService.selectShoppingCartItem(shoppingCart.getShoppingCartId());
		List<Product> productList=new ArrayList<Product>();
		BigDecimal totalPrice=new BigDecimal(0.00);
			for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
				Product product = productService.selectProductById(shoppingCartItem.getProductId());
				BigDecimal sum = product.getSalePrice().multiply(new BigDecimal(shoppingCartItem.getProductQuantity()));
				totalPrice=totalPrice.add(sum);
				productList.add(product);
			}
		List<Address> addressList= addressService.selectAddressByUserId(userId);
		model.addAttribute("shoppingCartItemList", shoppingCartItemList);
		model.addAttribute("totalPrice", totalPrice.toString());
		model.addAttribute("productList",productList);
		model.addAttribute("shopId",shopId);
		model.addAttribute("userId",userId);
		model.addAttribute("addressList", addressList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/shoppingcart/list";
	}
}

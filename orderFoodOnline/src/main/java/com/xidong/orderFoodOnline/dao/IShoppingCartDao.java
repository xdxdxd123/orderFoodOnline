package com.xidong.orderFoodOnline.dao;

import com.xidong.orderFoodOnline.model.ShoppingCart;
import com.xidong.orderFoodOnline.model.ShoppingCartItem;

public interface IShoppingCartDao {
	//添加商品到购物车
	void addProduct(ShoppingCartItem shoppingCartItem) throws Exception;
	
    //删除购物车里的商品
	void delProduct(ShoppingCartItem shoppingCartItem) throws Exception;
	
	//初始化购物车
	void addShoppingCart(ShoppingCart  shoppingCart) throws Exception;
	
	ShoppingCart findByUserId(String userId) throws Exception;
}

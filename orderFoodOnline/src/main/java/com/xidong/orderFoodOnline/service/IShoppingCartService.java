package com.xidong.orderFoodOnline.service;

import java.util.List;
import com.xidong.orderFoodOnline.model.Product;

public interface IShoppingCartService {
	//添加商品到购物车
	void addProducts(List<Product> products) throws Exception;
    //删除购物车里的商品
	void delProducts(List<Product> products) throws Exception;
    //买家购物车的商品
	List<Product> selectProducts(String  shoppingCartId,String userId)throws Exception;
}

package com.xidong.orderFoodOnline.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.service.IShoppingCartService;

@Service(value="shoppingCartService")
public class ShoppingCartServiceImpl implements IShoppingCartService {

	@Override
	public void addProducts(List<Product> products) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delProducts(List<Product> products) throws Exception {

	}

	@Override
	public List<Product> selectProducts(String shoppingCartId, String userId) throws Exception {
		return null ;
	}

}

package com.xidong.orderFoodOnline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xidong.orderFoodOnline.dao.IShoppingCartDao;
import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.model.ShoppingCart;
import com.xidong.orderFoodOnline.service.IShoppingCartService;

@Service(value="shoppingCartService")
public class ShoppingCartServiceImpl implements IShoppingCartService {
	@Autowired
private IShoppingCartDao shoppingCartDao;
	
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

	@Override
	public ShoppingCart getByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return shoppingCartDao.findByUserId(userId);
	}

}

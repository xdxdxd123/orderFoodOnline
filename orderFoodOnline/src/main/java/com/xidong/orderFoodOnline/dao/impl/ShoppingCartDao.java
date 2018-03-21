package com.xidong.orderFoodOnline.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xidong.orderFoodOnline.dao.IShoppingCartDao;
import com.xidong.orderFoodOnline.model.Product;

@Repository(value="shoppingCatrDao")
class ShoppingCartDao implements IShoppingCartDao {
	@Autowired
private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void addProducts(List<Product> products) throws Exception {
		// TODO Auto-generated method stub
sessionFactory.getCurrentSession().save(products);
	}

	@Override
	public void delProducts(List<Product> products) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> selectProducts(String shoppingCartId, String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.xidong.orderFoodOnline.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xidong.orderFoodOnline.dao.IShoppingCartDao;
import com.xidong.orderFoodOnline.model.ShoppingCart;
import com.xidong.orderFoodOnline.model.ShoppingCartItem;

@Repository(value = "shoppingCatrDao")
class ShoppingCartDao implements IShoppingCartDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProduct(ShoppingCartItem shoppingCartItem) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delProduct(ShoppingCartItem shoppingCartItem) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 买家注册是增加购物车
	 */
	@Override
	public void addShoppingCart(ShoppingCart shoppingCart) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(shoppingCart);
	}

	/**
	 * 用户的购物车
	 */
	@Override
	public ShoppingCart findByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "select new ShoppingCart(shoppingCartId,userId) from ShoppingCart where userId=:userId";
		Query<ShoppingCart> query = session.createQuery(sql);
		query.setParameter("userId", userId);
		List<ShoppingCart> list=query.list();
		return list.get(0);
	}

}

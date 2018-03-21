package com.xidong.orderFoodOnline.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xidong.orderFoodOnline.dao.IShopDao;
import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.model.Shop;

@Repository(value = "shopDao")
public class ShopDaoImpl implements IShopDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addShop(Shop shop) throws Exception {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(shop);
	}

	@Override
	public void modifyShop(Shop shop) throws Exception {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(shop);
	}

	@Override
	public void delShop(Shop shop) throws Exception {
		// TODO Auto-generated method stub
		shop.setStatus("OPEN");
		sessionFactory.getCurrentSession().update(shop);
	}

	@Override
	public List<Shop> selectAllShop() throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "select * from  shop_";
		NativeQuery<Shop> query = session.createNativeQuery(sql);
		List<Shop> shops = query.list();
		return shops;
	}
	
	
	//卖家id查询店铺
	public Shop selectShopByUserId(String userId){
	Shop shop=	sessionFactory.getCurrentSession().get(Shop.class, userId);
		return shop;
	}

	@Override
	public Shop selectShopById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return	sessionFactory.getCurrentSession().get(Shop.class, userId);
	}

}

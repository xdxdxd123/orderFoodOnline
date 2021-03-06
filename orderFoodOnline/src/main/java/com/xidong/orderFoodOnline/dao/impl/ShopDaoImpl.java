package com.xidong.orderFoodOnline.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xidong.orderFoodOnline.dao.IShopDao;
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
		String sql = "from  Shop";
		Query<Shop> query = session.createQuery(sql);
		List<Shop> shops = query.list();
		return shops;
	}
	
	
	//卖家id查询店铺
	public Shop selectShopByUserId(String userId){
	Session session=sessionFactory.getCurrentSession();
	Query<Shop> query=session.createQuery("from Shop  where  userid=:userId");
	query.setParameter("userId", userId);
	List<Shop> shops= query.list();
		return shops.get(0);
	}

	@Override
	public Shop selectShopById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return	sessionFactory.getCurrentSession().get(Shop.class, userId);
	}

}

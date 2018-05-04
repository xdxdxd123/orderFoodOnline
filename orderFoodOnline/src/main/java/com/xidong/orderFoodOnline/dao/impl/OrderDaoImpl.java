package com.xidong.orderFoodOnline.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xidong.orderFoodOnline.dao.IOrderDao;
import com.xidong.orderFoodOnline.model.Order;

@Repository(value="orderDao")
public class OrderDaoImpl implements IOrderDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public List<Order> getOrdersByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		String sql="select new com.xidong.orderFoodOnline.model.Order(order.orderId,order.orderTotalPrice,order.shopOrderStatus,order.userId,order.shopId,order.buyersOrderStatus,order.status,order.createDate,order.orderCode) from  Order order  where  order.userId=:userId";
		Query query  =session.createQuery(sql);
		query.setParameter("userId", userId);
		return query.list();
	}

	@Override
	public List<Order> getOrdersByShopId(String shopId) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		String sql="select new Order(orderId, orderTotalPrice, shopOrderStatus, userId, shopId,"+
			 "buyersOrderStatus, status, createDate, orderCode) from Order  where  shopId=:shopId";
		Query query  =session.createQuery(sql);
		query.setParameter("shopId", shopId);
		return query.list();
	}

	@Override
	public void add(Order order) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.save(order);
	}

}

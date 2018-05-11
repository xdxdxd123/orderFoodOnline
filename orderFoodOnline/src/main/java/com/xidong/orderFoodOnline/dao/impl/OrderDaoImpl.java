package com.xidong.orderFoodOnline.dao.impl;

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
	public List<Order> getOrdersByUserId(Order order) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		String hql="select new com.xidong.orderFoodOnline.model.Order(order.orderId,order.orderTotalPrice,order.shopOrderStatus,order.userId,order.shopId,order.buyersOrderStatus,order.status,order.createDate,order.orderCode) from  Order order  where  order.userId=:userId ";
		Query query  =session.createQuery(hql);
		query.setParameter("userId", order.getUserId());
		query.setFirstResult((order.getPageNumber()-1)*order.getPageSize());
		query.setMaxResults(order.getPageSize());
		return query.list();
	}

	@Override
	public List<Order> getOrdersByShopId(Order order) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		String hql="select new com.xidong.orderFoodOnline.model.Order(order.orderId, order.orderTotalPrice, order.shopOrderStatus, order.userId, order.shopId,"+
			 "order.buyersOrderStatus, order.status, order.createDate, order.orderCode) from Order order where  order.shopId=:shopId";
		Query query  =session.createQuery(hql);
		query.setParameter("shopId", order.getShopId());
		query.setFirstResult((order.getPageNumber()-1)*order.getPageSize());
		query.setMaxResults(order.getPageSize());
		return query.list();
	}

	@Override
	public void add(Order order) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.save(order);
	}

	@Override
	public void update(Order order) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Order order_=session.load(Order.class, order.getOrderId());
		order_.setBuyersOrderStatus(order.getBuyersOrderStatus());
		order_.setShopOrderStatus(order.getShopOrderStatus());
		session.update(order_);
	}

	@Override
	public long getOrdertotal(Order order) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		String hql=null;
		if(order.getShopId()==null){
		 hql="select count(*) from  Order order  where  order.userId=:userId";
		}else {
		 hql="select count(*) from  Order order  where  order.shopId=:shopId";
		}
		Query<Long> query  =session.createQuery(hql);
		if(order.getShopId()==null){
			query.setParameter("userId", order.getUserId());
		}else {
			query.setParameter("shopId", order.getShopId());
		}
		return query.list().get(0);
	}

}

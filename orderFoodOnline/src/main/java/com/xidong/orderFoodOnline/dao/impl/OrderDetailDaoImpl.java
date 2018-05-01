package com.xidong.orderFoodOnline.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xidong.orderFoodOnline.dao.IOrderDetailDao;
import com.xidong.orderFoodOnline.model.Orderdetail;
import com.xidong.orderFoodOnline.util.UUIDUtil;

@Repository(value="orderDetailDao")
public class OrderDetailDaoImpl implements IOrderDetailDao {


	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public void add(Orderdetail orderDetail) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		orderDetail.setOrderDetailId(UUIDUtil.getUUID());
		session.save(orderDetail);
	}

	@Override
	public void modify(Orderdetail orderDetail) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.merge(orderDetail);
	}

	@Override
	public void del(Orderdetail orderDetailId) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Orderdetail orderDetail= session.get(Orderdetail.class, orderDetailId);
		session.delete(orderDetail);
	}

	@Override
	public  Orderdetail selectById(Orderdetail orderDetailId) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
	return 	session.get(Orderdetail.class, orderDetailId);
	}

	@Override
	public  List<Orderdetail> selectByOrderId(String orderId) throws Exception {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		String sql="select new Orderdetail(orderDetailId, productId,  productQuantity,  orderId, char isDeleted) from Order where  orderId=:orderId";
		Query query  =session.createQuery(sql);
		query.setParameter("orderId", orderId);
		return query.list();
	}

}

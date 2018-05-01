package com.xidong.orderFoodOnline.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xidong.orderFoodOnline.dao.IOrderDao;
import com.xidong.orderFoodOnline.model.Order;
import com.xidong.orderFoodOnline.model.Orderdetail;
import com.xidong.orderFoodOnline.service.IOrderService;

@Service(value="orderService")
@Transactional
public class OrderService implements IOrderService {
	@Autowired
private IOrderDao orderDao;
	
	
	@Override
	public List<Order> getOrdersByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.getOrdersByUserId(userId);
	}

	@Override
	public List<Order> getOrdersByShopId(String shopId) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.getOrdersByShopId(shopId);
	}

	@Override
	public void add(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.add(order);
	}

	@Override
	public void modify(Orderdetail orderDetail) throws Exception {
		// TODO Auto-generated method stub
		
	}
}

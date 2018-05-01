package com.xidong.orderFoodOnline.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xidong.orderFoodOnline.dao.IOrderDetailDao;
import com.xidong.orderFoodOnline.model.Orderdetail;
import com.xidong.orderFoodOnline.service.IOrderDetailService;

@Service(value="orderDetailService")
@Transactional
public class OrderDetailService implements IOrderDetailService {
	@Autowired
private IOrderDetailDao orderDetailDao ;
	
	@Override
	public void add(Orderdetail orderDetail) throws Exception {
		// TODO Auto-generated method stub
		orderDetailDao.add(orderDetail);
	}

	@Override
	public void modify(Orderdetail orderDetail) throws Exception {
		// TODO Auto-generated method stub
		orderDetailDao.modify(orderDetail);
	}

	@Override
	public void del(Orderdetail orderDetailId) throws Exception {
		// TODO Auto-generated method stub
		orderDetailDao.del(orderDetailId);
	}

	@Override
	public Orderdetail selectById(Orderdetail orderDetailId) throws Exception {
		// TODO Auto-generated method stub
		return orderDetailDao.selectById(orderDetailId);
	}

	@Override
	public List<Orderdetail> selectByOrderId(String orderId) throws Exception {
		// TODO Auto-generated method stub
		return orderDetailDao.selectByOrderId(orderId);
	}

}

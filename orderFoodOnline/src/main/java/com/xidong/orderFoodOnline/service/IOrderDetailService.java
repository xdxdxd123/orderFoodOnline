package com.xidong.orderFoodOnline.service;

import java.util.List;

import com.xidong.orderFoodOnline.model.Orderdetail;

public interface IOrderDetailService {
	  void add(Orderdetail  orderDetail) throws Exception;
	  
	  void modify(Orderdetail  orderDetail) throws Exception;
	  
	  void del(Orderdetail  orderDetailId) throws Exception;
	  
	  Orderdetail selectById(Orderdetail  orderDetailId) throws Exception;
	  
	  List<Orderdetail> selectByOrderId(String orderId)throws Exception;
}

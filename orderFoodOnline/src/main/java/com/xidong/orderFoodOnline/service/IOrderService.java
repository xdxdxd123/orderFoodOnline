package com.xidong.orderFoodOnline.service;

import java.util.List;

import com.xidong.orderFoodOnline.model.Order;
import com.xidong.orderFoodOnline.model.Orderdetail;

public interface IOrderService {
	//买家订单
 List<Order> getOrdersByUserId(Order order) throws Exception;
 
    //卖家订单
 List<Order> getOrdersByShopId(Order order) throws Exception;
 
 void add(Order order) throws Exception;
 
 void modify(Orderdetail orderDetail) throws Exception;
 
 void update(Order order) throws Exception;
 
 long getOrderTotal(Order order) throws Exception;
}

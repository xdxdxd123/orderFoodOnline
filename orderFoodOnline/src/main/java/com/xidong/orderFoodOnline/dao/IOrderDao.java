package com.xidong.orderFoodOnline.dao;

import java.util.List;

import com.xidong.orderFoodOnline.model.Order;

public interface IOrderDao {
	//买家订单
 List<Order> getOrdersByUserId(Order order) throws Exception;
 
    //卖家订单
 List<Order> getOrdersByShopId(Order order) throws Exception;
 
 long getOrdertotal(Order order);
 
 void add(Order order) throws Exception;
 
 void update(Order order) throws Exception;
}

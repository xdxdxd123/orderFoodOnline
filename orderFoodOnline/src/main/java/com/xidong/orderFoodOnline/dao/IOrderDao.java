package com.xidong.orderFoodOnline.dao;

import java.util.List;

import com.xidong.orderFoodOnline.model.Order;

public interface IOrderDao {
	//买家订单
 List<Order> getOrdersByUserId(String userId) throws Exception;
 
    //卖家订单
 List<Order> getOrdersByShopId(String shopId) throws Exception;
 
 void add(Order order) throws Exception;
}

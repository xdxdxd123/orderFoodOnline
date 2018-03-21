package com.xidong.orderFoodOnline.service;

import java.util.List;

import com.xidong.orderFoodOnline.model.Shop;

public interface IShopService {
	void addShop(Shop shop) throws Exception;

	void modifyShop(Shop shop) throws Exception;

	void delShop(Shop shop) throws Exception;

	List<Shop>  selectAllShop() throws Exception;
	
	Shop  selectShopById(String id) throws Exception;
	
	Shop selectShopByUserId(String userId) throws Exception;
}

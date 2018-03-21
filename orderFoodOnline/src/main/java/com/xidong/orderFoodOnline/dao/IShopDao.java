package com.xidong.orderFoodOnline.dao;

import java.util.List;

import com.xidong.orderFoodOnline.model.Shop;

public interface IShopDao {
	void addShop(Shop shop) throws Exception;

	void modifyShop(Shop shop) throws Exception;

	void delShop(Shop shop) throws Exception;

	List<Shop> selectAllShop() throws Exception;
	
	Shop selectShopById(String id) throws Exception;
	
	//用户id查询店铺
	Shop selectShopByUserId(String userId) throws Exception;
}

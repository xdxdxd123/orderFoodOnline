package com.xidong.orderFoodOnline.dao;

import java.util.List;

import com.xidong.orderFoodOnline.model.Product;

public interface IProductDao {
	void addProduct(Product product) throws Exception;

	void modifyProduct(Product product) throws Exception;

	void delProduct(Product product) throws Exception;

	List<Product> selectAllProduct(String ShopId) throws Exception;
}

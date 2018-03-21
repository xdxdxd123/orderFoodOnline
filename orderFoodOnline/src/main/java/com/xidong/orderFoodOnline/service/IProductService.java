package com.xidong.orderFoodOnline.service;

import java.util.List;

import com.xidong.orderFoodOnline.model.Product;

public interface IProductService {
	void addProduct(Product product) throws Exception;

	void modifyProduct(Product product) throws Exception;

	void delProduct(Product product) throws Exception;

	List<Product> selectAllProduct(String shopId) throws Exception;
	
}

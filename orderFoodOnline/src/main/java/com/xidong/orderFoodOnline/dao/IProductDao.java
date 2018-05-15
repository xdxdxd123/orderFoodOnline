package com.xidong.orderFoodOnline.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.xidong.orderFoodOnline.model.Product;

public interface IProductDao {
	void addProduct(Product product) throws Exception;

	void modifyProduct(Product product) throws Exception;

	void delProduct(Product product) throws Exception;

	List<Product> selectProducts(Product product) throws Exception;
	
	void addProduct(Product product,MultipartFile picture) throws Exception;
	
	Product selectProductById(String productId) throws Exception;
	
	long getCoutAll(Product product) throws Exception;
}

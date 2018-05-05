package com.xidong.orderFoodOnline.service;

import java.util.List;

import com.xidong.orderFoodOnline.model.ProductType;

public interface IProductTypeService {
	
	  void add(ProductType productType) throws Exception; 
	  
	  void modify(ProductType productType) throws Exception;
	  
	  void delete(ProductType productType) throws Exception;
	  
	  List<ProductType> gettAll(ProductType productType) throws Exception;
}

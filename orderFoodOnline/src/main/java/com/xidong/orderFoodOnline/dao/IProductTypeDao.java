package com.xidong.orderFoodOnline.dao;

import java.util.List;

import com.xidong.orderFoodOnline.model.ProductType;

public interface IProductTypeDao {
	
  void add(ProductType productType) throws Exception; 
  
  void modify(ProductType productType) throws Exception;
  
  void delete(ProductType productType) throws Exception;
  
  List<ProductType> selectAll(ProductType productType) throws Exception;
  
  ProductType selectProductTypeById(String productTypeId)     throws Exception;
  
  long getCountAll(ProductType productType) throws Exception;

}

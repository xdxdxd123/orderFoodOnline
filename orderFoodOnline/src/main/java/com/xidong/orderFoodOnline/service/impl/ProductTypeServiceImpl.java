package com.xidong.orderFoodOnline.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xidong.orderFoodOnline.dao.IProductTypeDao;
import com.xidong.orderFoodOnline.model.ProductType;
import com.xidong.orderFoodOnline.service.IProductTypeService;

@Service(value="productTypeService")
@Transactional
public class ProductTypeServiceImpl implements IProductTypeService {
	@Autowired
private IProductTypeDao productTypeDao; 
	@Override
	public void add(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		productTypeDao.add(productType);
	}

	@Override
	public void modify(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		productTypeDao.modify(productType);
	}

	@Override
	public void delete(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		productTypeDao.delete(productType);
	}

	@Override
	public List<ProductType> getAll(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		
		return  productTypeDao.selectAll(productType);
	}

	@Override
	public ProductType getProductTypeById(String productTypeId) throws Exception {
		
		return productTypeDao.selectProductTypeById(productTypeId);
	}

	@Override
	public long getCountAll(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		return productTypeDao.getCountAll(productType);
	}

}

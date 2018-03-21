package com.xidong.orderFoodOnline.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xidong.orderFoodOnline.dao.IProductDao;
import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.service.IProductService;

@Service(value="productService")
@Transactional
public class ProductServiceImpl implements IProductService {
	@Autowired
private IProductDao productDao;
	
	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
productDao.addProduct(product);
	}

	@Override
	public void modifyProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.modifyProduct(product);
	}

	@Override
	public void delProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.delProduct(product);
	}

	@Override
	public List<Product> selectAllProduct(String  shopId) throws Exception {
		// TODO Auto-generated method stub
return  productDao.selectAllProduct(shopId);
	}

}

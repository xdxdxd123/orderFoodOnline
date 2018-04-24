package com.xidong.orderFoodOnline.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xidong.orderFoodOnline.dao.IProductDao;
import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.service.IProductService;
import com.xidong.orderFoodOnline.util.FileUtil;
import com.xidong.orderFoodOnline.util.UUIDUtil;

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
		;
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

	@Override
	public void addProduct(Product product, MultipartFile picture) throws Exception {
		// TODO Auto-generated method stub
       String filePath=   FileUtil.fileSave(picture);
       if(filePath!=null) {
    	   product.setImage(filePath);
       }
		  productDao.addProduct(product);
	}

	@Override
	public Product selectProductById(String productId) throws Exception {
		// TODO Auto-generated method stub
		return productDao.selectProductById(productId);
	}

	@Override
	public void modifyProduct(Product product, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		 Product  productOriginal= productDao.selectProductById(product.getProductId());
		 if(productOriginal.getImage()!=null) {
			 new  File(productOriginal.getImage()).deleteOnExit();
		 }
		  String filePath=   FileUtil.fileSave(file);
	       if(filePath!=null) {
	    	   product.setImage(filePath);
	       }
			  productDao.modifyProduct(product);
	}

}

package com.xidong.orderFoodOnline.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xidong.orderFoodOnline.dao.IProductDao;
import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.util.UUIDUtil;

@Repository(value = "productDao")
public class ProductDaoImpl implements IProductDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		product.setProductId(UUIDUtil.getUUID());
		session.save(product);
	}

	@Override
	public void modifyProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(product);
	}

	@Override
	public void delProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		product.setStatus("DEL");
		session.update(product);
	}

	@Override
	public List<Product> selectAllProduct(String shopId) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "select * from product_  where shopid_=?";
		NativeQuery<Product> query = session.createNativeQuery(sql);
		query.setParameter(1, shopId);
		List<Product> products = query.list();
		return products;
	}

}

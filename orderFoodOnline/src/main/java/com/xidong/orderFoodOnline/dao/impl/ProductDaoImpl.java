package com.xidong.orderFoodOnline.dao.impl;


import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
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
		session.merge(product);
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
		String sql = "select new Product( productId,  productName, shopId,  productTypeId,provenance," + 
				"price,  salePrice,  discount,  image, status, stock) from Product where shopId=?";
		Query<Product> query = session.createQuery(sql);
		query.setParameter(0, shopId);
		List<Product> products = query.list();
		return products;
	}

	@Override
	public void addProduct(Product product, MultipartFile picture) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
	}

	@Override
	public Product selectProductById(String productId) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(Product.class, productId);
	}

}

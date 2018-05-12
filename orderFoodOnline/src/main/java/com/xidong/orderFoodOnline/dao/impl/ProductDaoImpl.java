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
		Product  product_=session.get(Product.class, product.getProductId());
		session.delete(product_);
	}

	@Override
	public List<Product> selectProducts(Product product) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		StringBuilder sql = new  StringBuilder("select new Product( productId,  productName, shopId,  productTypeId,provenance," + 
				"price,  salePrice,  discount,  image, status, stock) from Product where 1=1");
		String  shopId= product.getShopId();
		if(shopId!=null&&!"".equals(shopId)){
			sql.append(" and shopId=:shopId");
		}
		String productTypeId =product.getProductTypeId();
		if(productTypeId!=null&&!"".equals(productTypeId)){
			sql.append(" and productTypeId=:productTypeId");
		}
		int stock=product.getStock();
		
		/*if(){
			sql.append(" and stock=:stock");
		}*/
		Query<Product> query = session.createQuery(sql.toString());		
		
		if(!"".equals(product.getShopId())){
		query.setParameter("shopId",product.getShopId() );
		}
		if(productTypeId!=null&&!"".equals(productTypeId)){
			query.setParameter("productTypeId",productTypeId);
		}
	/*	if(!"".equals(product.getStock())){
			query.setParameter("stock",product.getStock());
			}*/
		List<Product> products=query.list();
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

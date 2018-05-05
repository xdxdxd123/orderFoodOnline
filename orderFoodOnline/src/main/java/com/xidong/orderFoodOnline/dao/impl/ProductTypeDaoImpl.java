package com.xidong.orderFoodOnline.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xidong.orderFoodOnline.dao.IProductTypeDao;
import com.xidong.orderFoodOnline.model.ProductType;

@Repository(value="productTypeDaoImpl")
public class ProductTypeDaoImpl implements IProductTypeDao {
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public void add(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.getCurrentSession();
		session.save(productType);
	}

	@Override
	public void modify(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.getCurrentSession();
		session.update(productType);
	}

	@Override
	public void delete(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.getCurrentSession();
		session.delete(productType);
	}

	@Override
	public List<ProductType> selectAll(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.getCurrentSession();
	    String hql="from ProductType wherer shopId=:shopId";
		Query  query=session.createQuery(hql);
		return query.list();
	}

}

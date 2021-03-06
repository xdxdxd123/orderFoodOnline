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
	    String hql="select new ProductType(productTypeId,productTypeName,shopId,status) from ProductType where shopId=:shopId";
	    if(productType.getProductTypeName()!=null&&!"".equals(productType.getProductTypeName())){
	    	hql="from ProductType where shopId=:shopId and productTypeName=:productTypeName";
	    }
		Query  query=session.createQuery(hql);
		query.setParameter("shopId", productType.getShopId());
		if(productType.getProductTypeName()!=null&&!"".equals(productType.getProductTypeName())){
			query.setParameter("productTypeName", productType.getProductTypeName());	
		}
		if(productType.getPageNumber()>0&&productType.getPageSize()>0){
			query.setFirstResult((productType.getPageNumber()-1)*productType.getPageSize());
			query.setMaxResults(productType.getPageSize());
		}
		
		return query.list();
	}

	@Override
	public ProductType selectProductTypeById(String productTypeId) throws Exception {
		Session  session=sessionFactory.getCurrentSession();
		return session.get(ProductType.class, productTypeId);
		 
	}

	@Override
	public long getCountAll(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.getCurrentSession();
	    String hql=" from ProductType where shopId=:shopId";
	    if(productType.getProductTypeName()!=null&&!"".equals(productType.getProductTypeName())){
	    	hql="from ProductType where shopId=:shopId and productTypeName=:productTypeName";
	    }
		Query<Long>  query=session.createQuery(hql);
		query.setParameter("shopId", productType.getShopId());
		if(productType.getProductTypeName()!=null&&!"".equals(productType.getProductTypeName())){
			query.setParameter("productTypeName", productType.getProductTypeName());	
		}
		
	if(query.list()!=null){
		return query.list().size();
	}else return 0;
	}
	
	
	

}

package com.xidong.orderFoodOnline.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xidong.orderFoodOnline.dao.IShoppingCartItemDao;
import com.xidong.orderFoodOnline.model.ShoppingCartItem;
import com.xidong.orderFoodOnline.util.UUIDUtil;

@Repository(value="shoppingCartDao")
public class ShoppingCartItemDaoImpl implements IShoppingCartItemDao {
	
	@Autowired
private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查询购物车的所有购物项
	 */
	@Override
	public List<ShoppingCartItem> selectShoppingCartItem(String shoppingCartId) throws Exception {
		// TODO Auto-generated method stub
		Session session=	sessionFactory.getCurrentSession();
		String sql="select new ShoppingCartItem(sci.shoppingCartItemId, sci.shoppingCartId, sci.productId,sci.productQuantity,sci.sum) from ShoppingCartItem sci where sci.shoppingCartId=:shoppingCartId";
		Query<ShoppingCartItem> query =session.createQuery(sql);
		query.setParameter("shoppingCartId",shoppingCartId);
		return query.list();
	}

	/**
	 * 删除购物车项
	 */
	@Override
	public void del(String shoppingCartItemId) throws Exception {
		// TODO Auto-generated method stub
		Session session=	sessionFactory.getCurrentSession();
		ShoppingCartItem shoppingCartItem= session.load(ShoppingCartItem.class, shoppingCartItemId);
		session.delete(shoppingCartItem);
	}

	/**
	 * 新增购物车项
	 */
	@Override
	public void save(ShoppingCartItem shoppingCartItem) throws Exception {
		// TODO Auto-generated method stub
		Session session=	sessionFactory.getCurrentSession();
		session.save(shoppingCartItem);
	}

	/**
	 * 更新购物车项
	 */
	@Override
	public void update(ShoppingCartItem shoppingCartItem) throws Exception {
		// TODO Auto-generated method stub
		Session session=	sessionFactory.getCurrentSession();
		session.merge(shoppingCartItem);
	}

	@Override
	public ShoppingCartItem findByIds(String shoppingCartId, String productId) throws Exception {
		// TODO Auto-generated method stub
		Session session=	sessionFactory.getCurrentSession();
		String sql="select new ShoppingCartItem(sci.shoppingCartItemId, sci.shoppingCartId, sci.productId, sci.productQuantity, sci.sum) from ShoppingCartItem  sci where  sci.shoppingCartId=:shoppingCartId and   sci.productId=:productId" ;
		Query<ShoppingCartItem>  query=session.createQuery(sql);
		query.setParameter("shoppingCartId",shoppingCartId);
		query.setParameter("productId",productId);
		if(query.list().size()==0)
		{
			return null;
		}else {
			return query.list().get(0);
		}
		
	}

	@Override
	public int getSum(String shoppingCartId ) throws Exception {
		// TODO Auto-generated method stub
		Session session=	sessionFactory.getCurrentSession();
		String sql="select sum(productQuantity) from ShoppingCartItem where shoppingCartId=:shoppingCartId";
		Query<Long>  query=session.createQuery(sql);
		query.setParameter("shoppingCartId", shoppingCartId);
	    List<Long>  list=  query.list();
	    if(list.get(0)!=null){
	    	return (int)(list.get(0).longValue());
	    }else {
	    	return 0;
	    }
		
	}

}

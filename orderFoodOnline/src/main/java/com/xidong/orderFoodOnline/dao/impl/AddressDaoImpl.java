package com.xidong.orderFoodOnline.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xidong.orderFoodOnline.dao.IAddressDao;
import com.xidong.orderFoodOnline.model.Address;

@Repository(value = "addressDao")
public class AddressDaoImpl implements IAddressDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addAddress(Address Address) throws Exception {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(Address);
	}

	@Override
	public void modifyAddress(Address Address) throws Exception {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(Address);
	}

	@Override
	public void delAddress(Address Address) throws Exception {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(Address);
	}

	@Override
	public List<Address> selectAllAddress() throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "from  Address";
		Query<Address> query = session.createQuery(sql);
		List<Address> Addresss = query.list();
		return Addresss;
	}
	
	
	//卖家id查询店铺
	public Address selectAddressByUserId(String userId){
	Session session=sessionFactory.getCurrentSession();
	Query<Address> query=session.createQuery("from Address  where  userid=?");
	query.setParameter(0, userId);
	List<Address> Addresss= query.list();
		return Addresss.get(0);
	}

	@Override
	public Address selectAddressById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return	sessionFactory.getCurrentSession().get(Address.class, userId);
	}

}

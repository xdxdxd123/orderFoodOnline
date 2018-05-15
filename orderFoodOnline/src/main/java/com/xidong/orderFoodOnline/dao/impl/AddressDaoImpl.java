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
import com.xidong.orderFoodOnline.util.UUIDUtil;

@Repository(value = "addressDao")
public class AddressDaoImpl implements IAddressDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addAddress(Address address) throws Exception {
		// TODO Auto-generated method stub
		address.setAddressId(UUIDUtil.getUUID());
		sessionFactory.getCurrentSession().save(address);
	}

	@Override
	public void modifyAddress(Address address) throws Exception {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(address);
	}

	@Override
	public void delAddress(Address address) throws Exception {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(address);
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
	
	
	/**
	 * 买家的所有收货地址
	 * @param userId
	 * @return
	 */
	public List< Address> selectAddressByUserId(String userId){
	Session session=sessionFactory.getCurrentSession();
	Query<Address> query=session.createQuery("from Address  where  userId=:userId");
	query.setParameter("userId", userId);
	List<Address> addresss= query.list();
		return addresss;
	}

	@Override
	/**
	 * 买家单个收货地址
	 */
	public Address selectAddressById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return	sessionFactory.getCurrentSession().get(Address.class, userId);
	}

	@Override
	public void updateDefaultAddress(String userId) throws Exception {
		// TODO Auto-generated method stub
		
	Session session=	sessionFactory.getCurrentSession();
	String hql="update Address set defaultAddr=:defaultAddr where userId=:userId";
	Query query= session.createQuery(hql);
	query.setParameter("defaultAddr", "-1");
	query.setParameter("userId", userId);
	query.executeUpdate();
	}

}

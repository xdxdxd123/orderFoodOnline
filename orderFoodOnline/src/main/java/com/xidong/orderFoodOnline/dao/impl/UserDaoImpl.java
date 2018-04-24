package com.xidong.orderFoodOnline.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.xidong.orderFoodOnline.dao.IUserDao;
import com.xidong.orderFoodOnline.model.User;
import com.xidong.orderFoodOnline.util.UUIDUtil;

@Repository(value="userDao")
public class UserDaoImpl implements IUserDao {
	@Resource(name="sessionFactory")
private SessionFactory  sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub
	Session  session=	sessionFactory.getCurrentSession();
	session.save(user);
	}
	
	@Override
	public boolean findUser(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*) from user_ where username_=? and password_=? ";
		NativeQuery<Integer> query = session.createNativeQuery(sql);
		query.setParameter(1, username);
		query.setParameter(2, password);
		List<Integer> list = query.list();
		int flag = list.get(0);
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean checkUsernameExist(String username) throws Exception {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*) from user_ where username_=?";
		NativeQuery<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter(1, username);
		List<BigInteger> list = query.list();
		Long flag = list.get(0).longValue();
		if (flag == 1L) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public User checkIdentity(String username ,String password ,String userType) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "from User user where user.username=? and user.password=? and user.usertype=?";
		Query<User> query = session.createQuery(sql);
		query.setParameter(0, username);
		query.setParameter(1, password);
		query.setParameter(2, userType);
		List<User> list = query.list();
		if(list.size()==1) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public User getUserById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(User.class, userId);
	}
}

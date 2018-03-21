package com.xidong.orderFoodOnline.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.xidong.orderFoodOnline.dao.IUserDao;
import com.xidong.orderFoodOnline.model.User;
import com.xidong.orderFoodOnline.service.IUserService;


@Service(value="userService")
@Transactional
public class UserServiceImpl implements IUserService {
	@Resource(name="userDao")
private  IUserDao  userDao;
	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub
userDao.addUser(user);
	}
	@Override
	public boolean login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		return	  userDao.findUser(username, password);
	}
	@Override
	public boolean checkUsernameExist(String username) throws Exception {
		// TODO Auto-generated method stub
		return userDao.checkUsernameExist(username);
	}

}

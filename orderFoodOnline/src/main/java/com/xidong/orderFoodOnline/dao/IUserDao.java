package com.xidong.orderFoodOnline.dao;

import com.xidong.orderFoodOnline.model.User;

public interface IUserDao {
	void addUser(User user) throws Exception;

	boolean findUser(String username, String password) throws Exception;

	boolean checkUsernameExist(String username) throws Exception;
	
	User  checkIdentity(String username ,String password ,String userType) throws  Exception;
	
	User  getUserById(String userId) throws Exception;
}

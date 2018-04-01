package com.xidong.orderFoodOnline.service;

import com.xidong.orderFoodOnline.model.User;

public interface IUserService {
	void addUser(User user) throws Exception;

	boolean login(String username, String password) throws Exception;

	boolean checkUsernameExist(String username) throws Exception;
	
	User  checkIdentity(String username ,String password ,String userType ) throws Exception;
}

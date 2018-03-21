package com.xidong.orderFoodOnline.dao;

import com.xidong.orderFoodOnline.model.User;

public interface IUserDao {
	void addUser(User user) throws Exception;

	boolean findUser(String username, String password) throws Exception;

	boolean checkUsernameExist(String username) throws Exception;
}

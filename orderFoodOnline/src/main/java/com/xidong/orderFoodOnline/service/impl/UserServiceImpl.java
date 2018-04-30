package com.xidong.orderFoodOnline.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.xidong.orderFoodOnline.dao.IShopDao;
import com.xidong.orderFoodOnline.dao.IShoppingCartDao;
import com.xidong.orderFoodOnline.dao.IUserDao;
import com.xidong.orderFoodOnline.model.Shop;
import com.xidong.orderFoodOnline.model.ShoppingCart;
import com.xidong.orderFoodOnline.model.User;
import com.xidong.orderFoodOnline.service.IUserService;
import com.xidong.orderFoodOnline.util.UUIDUtil;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements IUserService {
	@Resource(name = "userDao")
	private IUserDao userDao;
	@Resource(name="shoppingCatrDao")
    private IShoppingCartDao shoppingCartDao;
	@Resource(name="shopDao")
	private IShopDao shopDao;
	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		user.setUserid(UUIDUtil.getUUID());
		userDao.addUser(user);
		//买家初始化购物车
		if("买家".equals(user.getUsertype())){
		ShoppingCart  shoppingCart=	new ShoppingCart();
		shoppingCart.setShoppingCartId(UUIDUtil.getUUID());
		shoppingCart.setUserId(user.getUserid());
			shoppingCartDao.addShoppingCart(shoppingCart);
		}else{   //卖家初始化店铺
		Shop  shop=	new Shop();
		shop.setUserid(user.getUserid());
		shop.setShopId(UUIDUtil.getUUID());
		shopDao.addShop(shop);
		}	
	}

	@Override
	public boolean login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		return userDao.findUser(username, password);
	}

	@Override
	public boolean checkUsernameExist(String username) throws Exception {
		// TODO Auto-generated method stub
		return userDao.checkUsernameExist(username);
	}

	@Override
	public User checkIdentity(String username, String password, String userType) throws Exception {
		// TODO Auto-generated method stub

		User user = userDao.checkIdentity(username, password, userType);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public User findUserById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

}

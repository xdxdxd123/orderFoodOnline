package com.xidong.orderFoodOnline.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xidong.orderFoodOnline.dao.IShoppingCartItemDao;
import com.xidong.orderFoodOnline.model.ShoppingCartItem;
import com.xidong.orderFoodOnline.service.IShoppingCartItemService;

@Transactional
@Service(value="shoppingCartItemService")
public class ShoppingCartItemServiceImpl implements IShoppingCartItemService {
	@Autowired
private IShoppingCartItemDao shoppingCartItemDao;
	
	@Override
	public List<ShoppingCartItem> selectShoppingCartItem(String shoppingCartId) throws Exception {
		// TODO Auto-generated method stub
		return shoppingCartItemDao.selectShoppingCartItem(shoppingCartId);
	}

	@Override
	public void del(String shoppingCartItemId) throws Exception {
		// TODO Auto-generated method stub
		shoppingCartItemDao.del(shoppingCartItemId);
	}

	@Override
	public void save(ShoppingCartItem shoppingCartItem) throws Exception {
		// TODO Auto-generated method stub
    shoppingCartItemDao.save(shoppingCartItem);
	}

	@Override
	public void update(ShoppingCartItem shoppingCartItem) throws Exception {
		// TODO Auto-generated method stub
	shoppingCartItemDao.update(shoppingCartItem);
	}

	@Override
	public ShoppingCartItem getByIds(String shoppingCartId, String productId) throws Exception {
		// TODO Auto-generated method stub
		return shoppingCartItemDao.findByIds(shoppingCartId, productId);
	}

	@Override
	public int getSum(String shoppingCartId) throws Exception {
		return shoppingCartItemDao.getSum(shoppingCartId);
	}

}

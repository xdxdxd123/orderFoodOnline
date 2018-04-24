package com.xidong.orderFoodOnline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xidong.orderFoodOnline.dao.IShopDao;
import com.xidong.orderFoodOnline.model.Shop;
import com.xidong.orderFoodOnline.service.IShopService;

@Service(value = "shopService")
@Transactional
public class ShopServiceImpl implements IShopService {
	@Autowired
	private IShopDao shopDao;

	@Override
	public void addShop(Shop shop) throws Exception {
		// TODO Auto-generated method stub
		shopDao.addShop(shop);
	}

	@Override
	public void modifyShop(Shop shop) throws Exception {
		// TODO Auto-generated method stub
		shopDao.modifyShop(shop);
		;
	}

	@Override
	public void delShop(Shop shop) throws Exception {
		// TODO Auto-generated method  stub
		shopDao.delShop(shop);
	}

	@Override
	public List<Shop> selectAllShop() throws Exception {
		// TODO Auto-generated method stub
		List<Shop>   shops =shopDao.selectAllShop();
		return shops;
	}

	@Override
	public Shop selectShopById(String id) throws Exception {
		// TODO Auto-generated method stub
	return shopDao.selectShopById(id);
	}

	
	
	@Override
	public Shop selectShopByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return shopDao.selectShopByUserId(userId);
	}


}

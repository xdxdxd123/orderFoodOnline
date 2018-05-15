package com.xidong.orderFoodOnline.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xidong.orderFoodOnline.dao.IAddressDao;
import com.xidong.orderFoodOnline.model.Address;
import com.xidong.orderFoodOnline.service.IAddressService;

@Service(value = "addressService")
@Transactional
public class AddressServiceImpl implements IAddressService {
	@Autowired
	private IAddressDao addressDao;

	@Override
	public void addAddress(Address Address) throws Exception {
		// TODO Auto-generated method stub
		addressDao.addAddress(Address);
	}

	@Override
	public void modifyAddress(Address Address) throws Exception {
		// TODO Auto-generated method stub
		addressDao.modifyAddress(Address);
		;
	}

	@Override
	public void delAddress(Address Address) throws Exception {
		// TODO Auto-generated method  stub
		addressDao.delAddress(Address);
	}

	@Override
	public List<Address> selectAllAddress() throws Exception {
		// TODO Auto-generated method stub
		List<Address>   addresss =addressDao.selectAllAddress();
		return addresss;
	}

	@Override
	public Address selectAddressById(String id) throws Exception {
		// TODO Auto-generated method stub
	return addressDao.selectAddressById(id);
	}

	
	
	@Override
	public List<Address> selectAddressByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return addressDao.selectAddressByUserId(userId);
	}

	@Override
	public void updateDefaultAddress(String userId) throws Exception {
		// TODO Auto-generated method stub
		addressDao.updateDefaultAddress(userId);
	}


}

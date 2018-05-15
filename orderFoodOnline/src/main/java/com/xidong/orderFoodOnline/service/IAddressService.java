package com.xidong.orderFoodOnline.service;

import java.util.List;

import com.xidong.orderFoodOnline.model.Address;

public interface IAddressService {
	void addAddress(Address Address) throws Exception;

	void modifyAddress(Address Address) throws Exception;

	void delAddress(Address Address) throws Exception;

	List<Address>  selectAllAddress() throws Exception;
	
	Address  selectAddressById(String id) throws Exception;
	
	List<Address> selectAddressByUserId(String userId) throws Exception;
	
	void updateDefaultAddress(String userId) throws Exception;
}

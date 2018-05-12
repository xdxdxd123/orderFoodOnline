package com.xidong.orderFoodOnline.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xidong.orderFoodOnline.model.Address;
import com.xidong.orderFoodOnline.model.User;
import com.xidong.orderFoodOnline.service.IAddressService;
import com.xidong.orderFoodOnline.service.IUserService;
import com.xidong.orderFoodOnline.util.JsonVo;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/address")
public class AddressController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAddressService addressService;
	
	/**
	 *添加地址
	 * @param address
	 */
	@RequestMapping(value="/add" ,method=RequestMethod.POST)
	public  void addaddress(Address address){
		try {
			//默认信息
			User user=userService.findUserById(address.getUserId()); 
			addressService.addAddress(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="del",method=RequestMethod.POST)
	public void deladdress(Address address){
		try {
			addressService.delAddress(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getAddresss",method=RequestMethod.GET)
	public String  selectAlladdress(Model model){
		try {
		List<Address>  addresss	=addressService.selectAllAddress();
		model.addAttribute("addressList", addresss);
		return 	"address/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/selectAddressByUserId")
	public @ResponseBody String  selectAddress(String addressId){
		try {
			JsonVo json=new JsonVo();
			json.setReturnJson(addressService.selectAddressByUserId(addressId));
		return  JSONObject.fromObject(json).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

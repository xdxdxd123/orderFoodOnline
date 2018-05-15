package com.xidong.orderFoodOnline.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xidong.orderFoodOnline.model.Address;
import com.xidong.orderFoodOnline.service.IAddressService;
import com.xidong.orderFoodOnline.util.JsonVo;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/address")
public class AddressController {
	
	@Autowired
	private IAddressService addressService;
	
	
	/**
	 * 添加地址页面
	 * @param shopId
	 * @param model
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/addPage")
	public  String addProductTypePage(String userId,Model model,@RequestParam(required=false) String addressId) {
	    model.addAttribute("userId",userId);
	    if(addressId!=null) {
	    	 model.addAttribute("addressId", addressId);
	    }
		return "address/add";
	}
	
	@RequestMapping(value="/index")
	public String getProducttypeIndex(String userId ,Model model ){
		model.addAttribute("userId", userId);
		return "address/index";
	}
	
	/**
	 *添加地址
	 * @param address
	 */
	@RequestMapping(value="/add" ,method=RequestMethod.POST)
	public  @ResponseBody String addaddress(Address address){
		JsonVo json=new JsonVo();
		json.setSuccess(true);
		try {
			if("1".equals(address.getDefaultAddr())){
				addressService.updateDefaultAddress(address.getUserId());
			}
			addressService.addAddress(address);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
		}
		return JSONObject.fromObject(json).toString();
	}
	
	@RequestMapping(value="del",method=RequestMethod.GET)
	public @ResponseBody  String delAddress(Address address){
		JsonVo json=new JsonVo();
		json.setSuccess(true);
		try {
			addressService.delAddress(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
		}
		return JSONObject.fromObject(json).toString();
	}
	
	@RequestMapping(value="modify",method=RequestMethod.POST)
	public @ResponseBody  String modifyAddress(Address address,int flag){
		JsonVo json=new JsonVo();
		json.setSuccess(true);
		try {
			if(flag==1){
			Address address_=	addressService.selectAddressById(address.getAddressId());
			address_.setDefaultAddr("1");
			addressService.updateDefaultAddress(address.getUserId());
			addressService.modifyAddress(address_);
			}else{
				if("1".equals(address.getDefaultAddr())){
					addressService.updateDefaultAddress(address.getUserId());
				}
				addressService.modifyAddress(address);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
		}
		return JSONObject.fromObject(json).toString();
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
	
	@RequestMapping(value="/getOneAddresss",method=RequestMethod.GET)
	public @ResponseBody  String  selectOneaddress(Model model,String addressId){
		Address  addresss=null;
		try {
		 addresss	=addressService.selectAddressById(addressId);
		model.addAttribute("addressList", addresss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONObject.fromObject(addresss).toString();
	}
	
	
	//买家所有地址
	@RequestMapping(value="/list")
	public @ResponseBody String  selectAddress(String userId){
		Map<String,Object>  map=new HashMap<String ,Object>();
		try {
			List<Address> address= addressService.selectAddressByUserId(userId);
			map.put("rows",address );
			map.put("total", address.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString();
	}
}

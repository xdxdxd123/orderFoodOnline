package com.xidong.orderFoodOnline.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xidong.orderFoodOnline.form.OrderForm;
import com.xidong.orderFoodOnline.model.Order;
import com.xidong.orderFoodOnline.model.Orderdetail;
import com.xidong.orderFoodOnline.model.Shop;
import com.xidong.orderFoodOnline.model.User;
import com.xidong.orderFoodOnline.service.IOrderDetailService;
import com.xidong.orderFoodOnline.service.IOrderService;
import com.xidong.orderFoodOnline.service.IProductService;
import com.xidong.orderFoodOnline.service.IShopService;
import com.xidong.orderFoodOnline.service.IShoppingCartItemService;
import com.xidong.orderFoodOnline.service.IUserService;
import com.xidong.orderFoodOnline.util.UUIDUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/order")
public class OrderController {
@Autowired
private IOrderDetailService orderDetailService;
@Autowired
private IOrderService orderService;
@Autowired
private IShoppingCartItemService shoppingCartItemService;
@Autowired
private IUserService userService;
@Autowired
private IShopService shopService;



	/**
	 * 创建订单
	 * @param orderForm
	 * @return
	 */
	@RequestMapping(value="/orderCreate")
	public  String  orderCreate(OrderForm orderForm ){
		Order order  =new Order();
		Date date=new Date();
		order.setOrderCode(getCode(orderForm.getUserId()));
		order.setCreateDate(date);
		order.setOrderTotalPrice(orderForm.getTotalPrice());
		order.setBuyersOrderStatus("1");
		order.setShopOrderStatus("1");
		String orderId= UUIDUtil.getUUID();
		order.setOrderId(orderId);
		order.setShopId(orderForm.getShopId());
		order.setUserId(orderForm.getUserId());
		int  length=orderForm.getShoppingCartItemIds().length;
		for(int index=0;index<length;index++ ){
		Orderdetail orderDetail=new Orderdetail();
		orderDetail.setOrderId(orderId);
		orderDetail.setProductQuantity(Short.parseShort(orderForm.getProductQuantitys()[index]));
		orderDetail.setProductId(orderForm.getProductQuantitys()[index]);
		orderDetail.setSum(orderForm.getSums()[index]);
		try {
			orderDetailService.add(orderDetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		try {
			orderService.add(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int index=0;index<length;index++){
			try {
				shoppingCartItemService.del(orderForm.getShoppingCartItemIds()[index]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "order/index";
	}
	
	
	/**
	 * 订单列表
	 * @param product
	 * @return
	 */
	/**
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectOrders(Order order) {
		try {
		Map<String, Object> map = new HashMap<String, Object>();
			List list = null;
			JSONArray array=null;
			String userId = order.getUserId();
		    User user=userService.findUserById(userId);
			if ("买家".equals(user.getUsertype())) {
				list = orderService.getOrdersByUserId(userId);
				 array = JSONArray.fromObject(list);
				int length=array.size();
			for(int index=0;index<length;index++ ){
			JSONObject  object	=array.getJSONObject(index);
			String shopId=(String) object.get("shopId");
			Shop shop=shopService.selectShopById(shopId);
			object.accumulate("shopName",shop.getShopname());
			object.accumulate("userType", 1);
			
			String buyersOrderStatus=(String) object.get("buyersOrderStatus");
			object.accumulate("buyersOrderStatusValue", buyersOrderStatus);
			switch (buyersOrderStatus){
		case	"1":object.replace("buyersOrderStatus","待卖家接单");break;
		case	"2":object.replace("buyersOrderStatus","已接单");break;
	    case    "3":object.replace("buyersOrderStatus","待收货");break;
	    case    "4":object.replace("buyersOrderStatus","已完成");break;
	    case    "5":object.replace("buyersOrderStatus","已取消");break;
			}
			}
			map.put("userType", 1);
			} else if("卖家".equals(user.getUsertype())){
				list = orderService.getOrdersByShopId(order.getShopId());
			    array = JSONArray.fromObject(list);
				int length=array.size();
			for(int index=0;index<length;index++ ){
			JSONObject  object	=array.getJSONObject(index);
			String userId_=(String) object.get("userId");
			User user_=userService.findUserById(userId_);
			object.accumulate("buyer",user_.getUsername());
			object.accumulate("userType",2);
			String shopOrderStatus=(String) object.get("shopOrderStatus");
			object.accumulate("shopOrderStatusValue", shopOrderStatus);
			switch (shopOrderStatus){
		case	"1":
			object.replace("shopOrderStatus","待接单");
		System.out.println(object);
		break;
		case    "2":object.replace("shopOrderStatus","待发货");break;
	    case    "3":object.replace("shopOrderStatus","已发货");break;
	    case    "4":object.replace("shopOrderStatus","已完成");break;
	    case    "5":object.replace("shopOrderStatus","已取消");break;
			}
			}
			
			}
			map.put("rows", array);
			map.put("total", list.size());
			JSONObject json = JSONObject.fromObject(map);
			return json.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//订单列表
	@RequestMapping(value="/listPage")
	public  String  orderListPage(Order order){
	return "order/index";
	}
	
	private String  getCode (String userId){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return  DigestUtils.md5Hex(userId+sdf.format(new Date()));
	}
	
	//更新订单状态
	/**
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/update")
	public @ResponseBody String updateOrder(Order order){
		try {
			orderService.update(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

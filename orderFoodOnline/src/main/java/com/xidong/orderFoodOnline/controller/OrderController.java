package com.xidong.orderFoodOnline.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xidong.orderFoodOnline.form.OrderForm;
import com.xidong.orderFoodOnline.model.Order;
import com.xidong.orderFoodOnline.model.Orderdetail;
import com.xidong.orderFoodOnline.service.IOrderDetailService;
import com.xidong.orderFoodOnline.service.IOrderService;
import com.xidong.orderFoodOnline.service.IShoppingCartItemService;
import com.xidong.orderFoodOnline.util.UUIDUtil;

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
	
	
	@RequestMapping(value="/orderCreate")
	public  String  orderCreate(OrderForm orderForm ){
		Order order  =new Order();
		order.setCreateDate(new Date());
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
	@RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectOrders(Order order) {
		try {
			List list = null;
			if (order.getShopId() != null) {
				list = orderService.getOrdersByShopId(order.getShopId());
			} else {
				list = orderService.getOrdersByUserId(order.getUserId());
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", list);
			map.put("total", list.size());
			JSONObject json = JSONObject.fromObject(map);
			return json.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

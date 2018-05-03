package com.xidong.orderFoodOnline.service;

import java.util.List;

import com.xidong.orderFoodOnline.model.ShoppingCartItem;

public interface IShoppingCartItemService {
	  //查询购物车项
	  List <ShoppingCartItem> selectShoppingCartItem(String shoppingCartId) throws Exception;
	 
	  //删除购物车项
	  void  del(String  shoppingCartItemId) throws Exception;
	  
	  //增加购物车项
	  void save(ShoppingCartItem shoppingCartItem)  throws Exception;
	  
	  //更改购物车项
	  void update(ShoppingCartItem shoppingCartItem) throws Exception;
	  
	  /**
	   * 得到购物车项
	   * @param shoppingCartId
	   * @param productId
	   * @return
	   * @throws Exception
	   */
	  ShoppingCartItem  getByIds(String shoppingCartId,String productId ) throws Exception;
	  
	  int getSum(String shoppingCartId) throws Exception;
}

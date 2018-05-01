package com.xidong.orderFoodOnline.dao;

import java.util.List;

import com.xidong.orderFoodOnline.model.ShoppingCartItem;

public interface IShoppingCartItemDao {
  //查询购物车项
  List <ShoppingCartItem> selectShoppingCartItem(String shoppingCartId) throws Exception;
 
  //删除购物车项
  void  del(String  shoppingCartItemId) throws Exception;
  
  //增加购物车项
  void save(ShoppingCartItem shoppingCartItem)  throws Exception;
  
  //更改购物车项
  void update(ShoppingCartItem shoppingCartItem) throws Exception;
  
  //购物车里的购物项
  ShoppingCartItem findByIds(String shoppingCartId,String productId) throws Exception;
  
  int getSum() throws Exception;
}

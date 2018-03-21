package com.xidong.orderFoodOnline.model;
// default package
// Generated 2018-2-24 19:09:30 by Hibernate Tools 3.5.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Shoppingcart generated by hbm2java
 */

@Entity
@Table(name="shoppingcart_")
public class ShoppingCart implements java.io.Serializable {

	@Id
	@Column(name="shopping_cart_id_")
	private String shoppingCartId;
	
	@Column(name="user_id_")
	private String userId;
	
	@Column(name="shop_id_")
	private String shopId;

	public ShoppingCart() {
	}

	public String getShoppingCartId() {
		return this.shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}

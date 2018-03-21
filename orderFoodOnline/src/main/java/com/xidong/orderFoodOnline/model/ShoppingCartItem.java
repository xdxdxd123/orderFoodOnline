package com.xidong.orderFoodOnline.model;
// default package
// Generated 2018-2-24 19:09:30 by Hibernate Tools 3.5.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shoppingcartitem_")
public class ShoppingCartItem implements java.io.Serializable {
	@Id
	@Column(name="shopping_cart_item_id_")
	private String shoppingCartItemId;
	
	@Column(name="shopping_cart_id_")
	private String shoppingCartId;
	
	@Column(name="product_id_")
	private String productId;
	
	@Column(name="product_quantity_")
	private short productQuantity;

	public ShoppingCartItem() {
	}

	public String getShoppingCartItemId() {
		return this.shoppingCartItemId;
	}

	public void setShoppingCartItemId(String shoppingCartItemId) {
		this.shoppingCartItemId = shoppingCartItemId;
	}

	public String getShoppingCartId() {
		return this.shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public short getProductQuantity() {
		return this.productQuantity;
	}

	public void setProductQuantity(short productQuantity) {
		this.productQuantity = productQuantity;
	}

}

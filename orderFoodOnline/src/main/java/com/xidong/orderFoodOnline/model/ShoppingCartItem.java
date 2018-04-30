package com.xidong.orderFoodOnline.model;

import java.math.BigDecimal;

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
	
	//小结
	@Column(name="sum_")
    private BigDecimal sum;
	
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

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public ShoppingCartItem(String shoppingCartItemId, String shoppingCartId, String productId, short productQuantity,
			BigDecimal sum) {
		this.shoppingCartItemId = shoppingCartItemId;
		this.shoppingCartId = shoppingCartId;
		this.productId = productId;
		this.productQuantity = productQuantity;
		this.sum = sum;
	}


	
	

}

package com.xidong.orderFoodOnline.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shoppingcart_")
public class ShoppingCart implements java.io.Serializable {

	
	
	@Id
	@Column(name="shopping_cart_id_")
	private String shoppingCartId;
	
	@Column(name="user_id_")
	private String userId;

	public ShoppingCart() {
	}

    
	public ShoppingCart(String shoppingCartId, String userId) {
		super();
		this.shoppingCartId = shoppingCartId;
		this.userId = userId;
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

}

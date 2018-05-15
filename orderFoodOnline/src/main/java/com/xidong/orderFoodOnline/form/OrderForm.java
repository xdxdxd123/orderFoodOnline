package com.xidong.orderFoodOnline.form;

import java.math.BigDecimal;

public class OrderForm {
	private String[] shoppingCartItemIds;
	private String productIds[];
	private String productQuantitys[];
	private BigDecimal[] sums;
	private BigDecimal totalPrice;
    private String userId;
    private String ShopId;
    private String addressId;
	public String[] getShoppingCartItemIds() {
		return shoppingCartItemIds;
	}
	public void setShoppingCartItemIds(String[] shoppingCartItemIds) {
		this.shoppingCartItemIds = shoppingCartItemIds;
	}
	public String[] getProductIds() {
		return productIds;
	}
	public void setProductIds(String[] productIds) {
		this.productIds = productIds;
	}
	public String[] getProductQuantitys() {
		return productQuantitys;
	}
	public void setProductQuantitys(String[] productQuantitys) {
		this.productQuantitys = productQuantitys;
	}
	public BigDecimal[] getSums() {
		return sums;
	}
	public void setSums(BigDecimal[] sums) {
		this.sums = sums;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShopId() {
		return ShopId;
	}
	public void setShopId(String shopId) {
		ShopId = shopId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	

}

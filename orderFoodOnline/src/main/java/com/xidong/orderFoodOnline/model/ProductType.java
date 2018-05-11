package com.xidong.orderFoodOnline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producttype_")
public class ProductType implements java.io.Serializable {
    @Id
	@Column(name="product_type_id_")
	private String productTypeId;
	
	@Column(name="product_type_name_")
	private String productTypeName;
	
	@Column(name="shop_id_")
	private String shopId;
	
	@Column(name="status_")
	private String status;

	public ProductType() {
	}

	public String getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductTypeName() {
		return this.productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

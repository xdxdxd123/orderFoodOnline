package com.xidong.orderFoodOnline.model;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_")
public class Product implements java.io.Serializable {
	@Id
	@Column(name="product_id_")
	private String productId;
	
	@Column(name="product_name_")
	private String productName;
	
	@Column(name="shop_id_")
	private String shopId;
	
	@Column(name="product_type_id_")
	private String productTypeId;
	
	@Column(name="provenance_")
	private String provenance;
	
	@Column(name="price_")
	private BigDecimal price;
	
	@Column(name="sale_price_")
	private BigDecimal salePrice;
	
	@Column(name="discount_")
	private long discount;
	
	@Column(name="image_")
	private String image;
	
	@Column(name="status_")
	private String status;

	public Product() {
	}
	
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProvenance() {
		return this.provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public long getDiscount() {
		return this.discount;
	}

	public void setDiscount(long discount) {
		this.discount = discount;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

package com.xidong.orderFoodOnline.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_")
public class Order implements java.io.Serializable {
	@Id
	@Column(name = "order_id_")
	private String orderId;
	@Column(name = "order_total_price_")
	private BigDecimal orderTotalPrice;
	@Column(name = "shop_order_status_")
	private String shopOrderStatus;
	@Column(name = "user_id_")
	private String userId;
	@Column(name = "shop_id_")
	private String shopId;
	@Column(name = "buyers_order_status_")
	private String buyersOrderStatus;
	@Column(name = "status_")
	private String status;
	@Column(name="create_date")
	private Date createDate;

	public Order() {
	}

	public Order(String orderId, BigDecimal orderTotalPrice, String shopOrderStatus, String userId, String shopId,
			String buyersOrderStatus, String status) {
		this.orderId = orderId;
		this.orderTotalPrice = orderTotalPrice;
		this.shopOrderStatus = shopOrderStatus;
		this.userId = userId;
		this.shopId = shopId;
		this.buyersOrderStatus = buyersOrderStatus;
		this.status = status;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getOrderTotalPrice() {
		return this.orderTotalPrice;
	}

	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getShopOrderStatus() {
		return this.shopOrderStatus;
	}

	public void setShopOrderStatus(String shopOrderStatus) {
		this.shopOrderStatus = shopOrderStatus;
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

	public String getBuyersOrderStatus() {
		return this.buyersOrderStatus;
	}

	public void setBuyersOrderStatus(String buyersOrderStatus) {
		this.buyersOrderStatus = buyersOrderStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
    public Date getCreateDate() {
		return createDate;
	}
    
}

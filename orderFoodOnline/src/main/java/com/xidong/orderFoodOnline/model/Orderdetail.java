package com.xidong.orderFoodOnline.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderdetail_")
public class Orderdetail implements java.io.Serializable {

	@Id
	@Column(name="order_detail_id")
	private String orderDetailId;
	@Column(name="product_id_")
	private String productId;
	@Column(name="product_quantity_")
	private short productQuantity;
	@Column(name="order_id_")
	private String orderId;
	@Column(name="is_deleted_")
	private char isDeleted;
	@Column(name="sum_")
	private BigDecimal sum;
    
	public Orderdetail() {
	}

	
	
	public Orderdetail(String orderDetailId, String productId, short productQuantity, String orderId, char isDeleted,
			BigDecimal sum) {
		super();
		this.orderDetailId = orderDetailId;
		this.productId = productId;
		this.productQuantity = productQuantity;
		this.orderId = orderId;
		this.isDeleted = isDeleted;
		this.sum = sum;
	}



	public Orderdetail(String orderDetailId, String productId, short productQuantity, String orderId, char isDeleted) {
		this.orderDetailId = orderDetailId;
		this.productId = productId;
		this.productQuantity = productQuantity;
		this.orderId = orderId;
		this.isDeleted = isDeleted;
	}

	public String getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
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

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public char getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(char isDeleted) {
		this.isDeleted = isDeleted;
	}
     
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
	public BigDecimal getSum() {
		return sum;
	}
}

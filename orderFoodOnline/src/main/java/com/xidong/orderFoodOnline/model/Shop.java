package com.xidong.orderFoodOnline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xidong.orderFoodOnline.form.Page;

@Entity
@Table(name = "shop_")
public class Shop extends Page implements java.io.Serializable {
	@Id
	@Column(name = "shop_id_")
	private String shopId;
	@Column(name = "shopname_")
	private String shopname;
	@Column(name = "userid_")
	private String userid;
	@Column(name = "status_")
	private String status;
	@Column(name = "notice_")
	private String notice;
	@Column(name="img_")
	private String img;
	public Shop() {
	}

	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopname() {
		return this.shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getImg() {
		return img;
	}
}

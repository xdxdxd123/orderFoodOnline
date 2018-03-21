package com.xidong.orderFoodOnline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address_")
public class Address implements java.io.Serializable {
    @Id
    @Column(name="address_id_")
	private String addressId;
    @Column(name="user_id_")
	private String userId;
@Column(name="province_")
	private String province;
@Column(name="contact_")
	private String contact;
@Column(name="city_")
	private String city;
@Column(name="detail_address_")
	private String detailAddress;

	public Address() {
	}

	public Address(String addressId, String userId, String province, String contact, String city,
			String detailAddress) {
		this.addressId = addressId;
		this.userId = userId;
		this.province = province;
		this.contact = contact;
		this.city = city;
		this.detailAddress = detailAddress;
	}

	public String getAddressId() {
		return this.addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDetailAddress() {
		return this.detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

}

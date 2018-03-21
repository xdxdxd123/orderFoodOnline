package com.xidong.orderFoodOnline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user_")
public class User implements java.io.Serializable {
	@Id 
	@Column(name = "userid_")
	private String userid;
	
	@Column(name = "username_")
	private String username;
	
	@Column(name = "password_")
	private String password;
	
	@Column(name = "e_mail_")
	private String email;
	
	@Column(name = "cell_phone_number_")
	private String cellPhoneNumber;
	
	@Column(name = "usertype_")
	private String usertype;
	
	@Column(name = "status_")
	private String status;

	public User() {
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellPhoneNumber() {
		return this.cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

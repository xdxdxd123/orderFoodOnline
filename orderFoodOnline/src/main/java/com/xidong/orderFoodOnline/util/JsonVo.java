package com.xidong.orderFoodOnline.util;

public class JsonVo {
	private boolean isSuccess;
	private Object returnJson;
	private String message;
	private String url;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setReturnJson(Object returnJson) {
		this.returnJson = returnJson;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

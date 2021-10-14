package com.credit.domain;

public class ResultVO {
	private boolean success;
	
	private String msg;
	
	private Object res;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getRes() {
		return res;
	}

	public void setRes(Object res) {
		this.res = res;
	}
	
	
}

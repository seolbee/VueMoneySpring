package com.credit.domain;

public class RegisterDTO {
	
	private String id;
	
	private String name;
	
	private String password;
	
	private String seqno;
	
	private String acctoken;
	
	private String reftoken;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getAcctoken() {
		return acctoken;
	}

	public void setAcctoken(String acctoken) {
		this.acctoken = acctoken;
	}

	public String getReftoken() {
		return reftoken;
	}

	public void setReftoken(String reftoken) {
		this.reftoken = reftoken;
	}
	
	
}

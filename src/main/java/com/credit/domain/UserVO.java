package com.credit.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserVO {
	
	@Id
	private ObjectId _id;
	
	private String name;
	
	private String userid;
	
	private String password;
	
	private String acctoken;
	
	private String reftoken;
	
	private String seqno;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	
}

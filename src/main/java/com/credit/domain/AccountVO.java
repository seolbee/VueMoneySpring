package com.credit.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="accounts")
public class AccountVO {
	
	@Id
	private ObjectId _id;
	
	private String accno;
	
	private String code;
	
	private String name;
	
	private String uid;
	
	private String finTechNum;
	
	private int money;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId objectId) {
		this._id = objectId;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFinTechNum() {
		return finTechNum;
	}

	public void setFinTechNum(String finTechNum) {
		this.finTechNum = finTechNum;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}

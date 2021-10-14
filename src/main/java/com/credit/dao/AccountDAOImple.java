package com.credit.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.credit.API.FinTechAPI;
import com.credit.domain.AccountVO;
import com.credit.domain.HistoryVO;

@Repository
public class AccountDAOImple implements AccountDAO{
	
	private final String CollectionName = "accounts";
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<AccountVO> getList(String uid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("uid").is(uid));
		List<AccountVO> list = mongoTemplate.find(query, AccountVO.class, CollectionName);
		return list;
	}

	@Override
	public void insertAccount(AccountVO vo) {
		vo.set_id(ObjectId.get());
		mongoTemplate.insert(vo, CollectionName);
	}
	
	@Override
	public boolean getAccount(String accno) {
		Query query = new Query();
		query.addCriteria(Criteria.where("accno").is(accno));
		AccountVO findAccount = mongoTemplate.findOne(query, AccountVO.class);
		if(findAccount != null) return true;
		else return false;
	}
	
}

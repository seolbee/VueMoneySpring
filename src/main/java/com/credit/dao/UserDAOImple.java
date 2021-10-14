package com.credit.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.credit.domain.LoginDTO;
import com.credit.domain.UserVO;

@Repository
public class UserDAOImple implements UserDAO{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	private final String CollectionName = "users";

	@Override
	public void insertUser(UserVO vo) {
		vo.set_id(ObjectId.get());
		vo.get_id();
		mongoTemplate.insert(vo, CollectionName);
	}

	@Override
	public UserVO loginUser(LoginDTO vo) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userid").is(vo.getUserid()));
		query.addCriteria(Criteria.where("password").is(vo.getPassword()));
		UserVO user = mongoTemplate.findOne(query, UserVO.class);
		return user;
	}

	@Override
	public boolean findIdUser(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userid").is(id));
		UserVO findUser = mongoTemplate.findOne(query, UserVO.class);
		if(findUser != null) return false;
		else return true;
	}

	@Override
	public boolean findPasswordUser(String password) {
		Query query = new Query();
		query.addCriteria(Criteria.where("password").is(password));
		UserVO findUser = mongoTemplate.findOne(query, UserVO.class);
		if(findUser != null) return false;
		else return true;
	}
	
}

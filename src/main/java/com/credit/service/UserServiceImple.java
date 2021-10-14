package com.credit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.dao.UserDAO;
import com.credit.domain.LoginDTO;
import com.credit.domain.UserVO;

@Service
public class UserServiceImple implements UserService{
	
	@Autowired
	UserDAO userDAO;

	@Override
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);		
	}

	@Override
	public UserVO loginUser(LoginDTO vo) {
		return userDAO.loginUser(vo);
	}

	@Override
	public boolean findIdUser(String id) {
		return userDAO.findIdUser(id);
	}

	@Override
	public boolean findPasswordUser(String password) {
		return userDAO.findPasswordUser(password);
	}
	
}

package com.credit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.dao.AccountDAO;
import com.credit.domain.AccountVO;

@Service
public class AccountServiceImple implements AccountService{
	
	@Autowired
	AccountDAO accountDAO;
	
	@Override
	public List<AccountVO> getList(String uid) {
		return accountDAO.getList(uid);
	}
	
	@Override
	public void insertAccount(AccountVO vo) {
		accountDAO.insertAccount(vo);		
	}
	
	@Override
	public boolean findAccount(String accno) {
		return accountDAO.getAccount(accno);
	}
}

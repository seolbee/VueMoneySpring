package com.credit.service;

import java.util.List;

import com.credit.domain.AccountVO;

public interface AccountService {
	
	public List<AccountVO> getList(String uid);
	public void insertAccount(AccountVO vo);
	public boolean findAccount(String accno);
}

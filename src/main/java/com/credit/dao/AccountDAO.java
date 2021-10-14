package com.credit.dao;

import java.util.List;

import com.credit.domain.AccountVO;
import com.credit.domain.HistoryVO;

public interface AccountDAO {
	public List<AccountVO> getList(String uid);
	
	public void insertAccount(AccountVO vo);
	
	public boolean getAccount(String accno);
	
}

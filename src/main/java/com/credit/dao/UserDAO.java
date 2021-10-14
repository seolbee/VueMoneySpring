package com.credit.dao;

import com.credit.domain.LoginDTO;
import com.credit.domain.RegisterDTO;
import com.credit.domain.UserVO;

public interface UserDAO {
	
	public void insertUser(UserVO vo);
	
	public UserVO loginUser(LoginDTO vo);
	
	public boolean findIdUser(String id);
	
	public boolean findPasswordUser(String password);
}

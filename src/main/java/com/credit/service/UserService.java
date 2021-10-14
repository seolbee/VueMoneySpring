package com.credit.service;

import com.credit.domain.LoginDTO;
import com.credit.domain.UserVO;

public interface UserService {
	public void insertUser(UserVO vo);
	public UserVO loginUser(LoginDTO vo);
	public boolean findIdUser(String id);
	public boolean findPasswordUser(String password);
}

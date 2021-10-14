package com.credit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.domain.LoginDTO;
import com.credit.domain.ResultVO;
import com.credit.domain.UserVO;
import com.credit.service.UserService;

@Controller
@RequestMapping("/auth/")
public class AuthController {
	
	@Autowired
	UserService UserService;
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public @ResponseBody Object register(@RequestBody UserVO registerDTO) {
		ResultVO vo = new ResultVO();
		
		if(!UserService.findIdUser(registerDTO.getUserid())) {
			vo.setSuccess(false);
			vo.setMsg("이미 있는 유저 입니다.");
			return vo;
		}
		
		UserService.insertUser(registerDTO);
		vo.setSuccess(true);
		vo.setMsg("회원가입 성공");
		return vo;
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public @ResponseBody Object login(HttpSession session, @RequestBody LoginDTO loginDto) {
		ResultVO vo = new ResultVO();
		if(UserService.findIdUser(loginDto.getUserid())) {
			vo.setSuccess(false);
			vo.setMsg("아이디가 틀렸습니다.");
			return vo;
		}
		if(UserService.findPasswordUser(loginDto.getPassword())){
			vo.setSuccess(false);
			vo.setMsg("비밀번호가 틀렸습니다.");
			return vo;
		}
		
		UserVO user = UserService.loginUser(loginDto);
		if(user == null) {
			vo.setSuccess(false);
			vo.setMsg("아이디나 비밀번호가 틀렸습니다. ");
			return vo;
		}
		session.setAttribute("user", user);
		vo.setSuccess(true);
		vo.setMsg("로그인 완료");
		vo.setRes(user);
		return vo;
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public @ResponseBody Object logout(HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("user");
		if(user != null) {
			session.removeAttribute("user");
		}
		return null;
	}
}

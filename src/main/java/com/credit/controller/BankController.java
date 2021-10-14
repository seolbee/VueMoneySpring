package com.credit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.API.FinTechAPI;
import com.credit.domain.AccountDTO;
import com.credit.domain.AccountVO;
import com.credit.domain.HistoryVO;
import com.credit.domain.ResultVO;
import com.credit.domain.UserVO;
import com.credit.service.AccountService;

@Controller
@RequestMapping("/bank/")
public class BankController {
	
	@Autowired
	FinTechAPI api;
	
	@Autowired
	AccountService AccountService;
	
	@RequestMapping(value="lists", method=RequestMethod.GET)
	public @ResponseBody Object lists(HttpSession session){
		ResultVO res = new ResultVO();
		UserVO user = (UserVO) session.getAttribute("user");
		List<AccountVO> list = AccountService.getList(user.get_id().toString());
		if(list.size() == 0) {
			res.setSuccess(false);
			res.setMsg("계좌가 없습니다. 계좌를 등록하세요 ");
			return res;
		}
		
		list.forEach(vo -> {
			vo.setMoney(api.getBalance(user.getAcctoken(), vo.getFinTechNum()));
		});
		
		res.setSuccess(true);
		res.setRes(list);
		return res;
		
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public @ResponseBody Object register(HttpSession session, @RequestBody AccountDTO accountDTO) {
		UserVO user = (UserVO) session.getAttribute("user");
		ResultVO vo = new ResultVO();
		if(AccountService.findAccount(accountDTO.getAccno())) {
			vo.setSuccess(false);
			vo.setMsg("이미 등록한 계좌입니다.");
			return vo;
		}
		String finTechNum = (String) api.getFinTechNum(user.getAcctoken(), user.getSeqno(), accountDTO.getAccno());
		if(finTechNum == "") {
			vo.setSuccess(false);
			vo.setMsg("계좌번호가 틀렸습니다.");
			return vo;
		}
		
		AccountVO account = new AccountVO();
		account.setAccno(accountDTO.getAccno());
		account.setCode(accountDTO.getCode());
		account.setName(accountDTO.getName());
		account.setUid(user.get_id().toString());
		account.setFinTechNum(finTechNum);
		AccountService.insertAccount(account);
		
		vo.setSuccess(true);
		vo.setMsg("계좌 등록 완료");
		return vo;
	}
	
	@RequestMapping(value="history/{finTechNum}", method=RequestMethod.GET)
	public @ResponseBody Object history(HttpSession session, @PathVariable String finTechNum) {
		UserVO user = (UserVO) session.getAttribute("user");
		ResultVO res = new ResultVO();
		List<HistoryVO> list = api.getHistory(user.getAcctoken(), finTechNum);
		if(list == null) {
			res.setSuccess(false);
			return res;
		}
		res.setSuccess(true);
		res.setRes(list);
		
		return res;
	}
}

package com.credit.API;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.credit.domain.HistoryVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Repository
public class FinTechAPI {
	private final String code = "M202112953U";
	
	private String getUseCode() {
		Integer num = (int) Math.floor(Math.random() * 100000000);
		String ranNum = num.toString() + "000000000";
		ranNum = ranNum.substring(0, 9);
		return this.code + ranNum;
	}
	
	public String getFinTechNum(String token, String seqno, String accno){
		String url = "https://testapi.openbanking.or.kr/v2.0/account/list?user_seq_no=" + seqno + "&include_cancel_yn=Y&sort_order=D";
		OkHttpClient client = new OkHttpClient();
		try {
			Request.Builder builder = new Request.Builder().url(url).get();
			builder.addHeader("Authorization", "Bearer " + token);
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			if(!response.isSuccessful()) {
				throw new Error();
			}
			
			ResponseBody body = response.body(); 
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = (Map<String, Object>) mapper.readValue(body.string(), Map.class);
			List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("res_list");
			String result = "";
			accno = accno.substring(0, accno.length() -3);
			for(Map<String, Object> temp : list) {
				String accno2 = (String) temp.get("account_num_masked");
				accno2 = accno2.substring(0, accno2.length()-3);
				if(accno.equals(accno2)) {
					result = (String) temp.get("fintech_use_num");
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public int getBalance(String token, String fintech_use_num) {
		String usecode = this.getUseCode();
		String dtime = this.getDtime();
		String url = "https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num?" + "bank_tran_id=" + usecode + "&fintech_use_num=" + fintech_use_num + "&tran_dtime=" + dtime;
		OkHttpClient client = new OkHttpClient();
		try {
			Request.Builder builder = new Request.Builder().url(url).get();
			builder.addHeader("Authorization", "Bearer " +token);
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			if(!response.isSuccessful()) {
				throw new Error();
			}
			ResponseBody body = response.body();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = (Map<String, Object>) mapper.readValue(body.string(), Map.class);
			
			return Integer.parseInt((String) map.get("balance_amt"));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
//		return 0;
	}
	
	private String getDtime() {
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		String date = String.valueOf(cal.get(Calendar.DATE));
		String hour = String.valueOf(Calendar.HOUR);
		String minute = String.valueOf(Calendar.MINUTE);
		String second = String.valueOf(Calendar.SECOND);
		return year + this.format(month) + this.format(date) + this.format(hour) + this.format(minute) + this.format(second);
	}
	
	private String format(String value) {
		String val = "00" + value;
		return val.substring(val.length() - 2, val.length());
	}
	
	public List<HistoryVO> getHistory(String token, String finTechNum){
		String dtime = this.getDtime();
		String usecode = this.getUseCode();
		String fromDate = "20160712";
		String toDate = "20210815";
		String url = "https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?" + "bank_tran_id=" + usecode + "&fintech_use_num="+finTechNum+"&inquiry_type=A&inquiry_base=D&from_date="+fromDate+"&to_date="+toDate+"&sort_order=D&tran_dtime="+dtime;
		OkHttpClient client = new OkHttpClient();
		try {
			Request.Builder builder = new Request.Builder().url(url).get();
			builder.addHeader("Authorization", "Bearer " + token);
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			if(!response.isSuccessful()) {
				throw new Exception();
			}
			ResponseBody body = response.body();
			ObjectMapper mapper = new ObjectMapper();
			List<HistoryVO> list;
			Map<String, Object> res = (Map<String, Object>) mapper.readValue(body.string(), Map.class);
			List<Map<String, String>> res_list = (List<Map<String,String>>) res.get("res_list");
			Stream<Map<String, String>> stream_res = res_list.stream();
			list = (List<HistoryVO>) stream_res.filter(f-> !f.get("print_content").equals("오픈상여금")).map(m->new HistoryVO(m.get("tran_date"), m.get("tran_time"), m.get("inout_type"), m.get("print_content"), m.get("tran_amt"), m.get("after_balance_amt"), m.get("branch_name"))).collect(Collectors.toList());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

package com.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.credit.API.FinTechAPI;

@ComponentScan(basePackages={"com.credit.controller", "com.credit.dao", "com.credit.service", "com.credit.API"})
@SpringBootApplication
public class CreditApplication {
	public static FinTechAPI api = new FinTechAPI();

	public static void main(String[] args) {
//		try {
//			System.out.println(api.getHistory("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzczNjYyIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MzYxODMxNzMsImp0aSI6IjgwN2ZlZGRlLTA1NDAtNDllZC1hZTlhLTA2YzM3YmYwMjJiZiJ9.zzZ6Bh0FOHkw0cMVNzpkBFIM7gwNHe6m5aT02J0Cq8A", "120211295388932265694663"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		SpringApplication.run(CreditApplication.class, args);
		
	}

}

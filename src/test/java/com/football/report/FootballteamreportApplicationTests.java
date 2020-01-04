package com.football.report;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.apache.http.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.football.report.pojo.StandingResponse;
import com.football.report.service.FootballService;



@SpringBootTest
class FootballteamreportApplicationTests {
	
	@Autowired
	private FootballService footballService;
	
	@Test
	void contextLoads() {
	}
	
	
	
	@Test
	public void testSuccess() throws ParseException, IOException {
		StandingResponse obj = footballService.getStandingsData("England", "Championship", "Swansea");
		assertNotNull(obj);
		System.out.println(obj.getCountryName());
		assertEquals("England", obj.getCountryName());
	}
	
	@Test
	public void testFailure() {
		try {
			StandingResponse obj = footballService.getStandingsData("India", "Premier League", "Leicester");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			assertThat(e.getMessage().contains("Error while fetching"));
		}
	}

}

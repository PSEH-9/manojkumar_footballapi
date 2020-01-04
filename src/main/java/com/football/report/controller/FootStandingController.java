package com.football.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.football.report.pojo.ResponseData;
import com.football.report.service.FootballService;

public class FootStandingController {
	
	@Autowired
	private FootballService footballService;
	
	@RequestMapping(value  = "/findstandings/{contryname}/{leaguename}/{teamname}", method = RequestMethod.GET)
	public ResponseEntity<ResponseData> getReport(
			@PathVariable(value = "leaguename",required = true) String countryName ,
			@PathVariable(value = "leaguename",required = true) String leagueName, 
			@PathVariable(value = "teamname",required = true) String teamName) {
		
		ResponseData data = footballService.getStandingsData(countryName, leagueName, teamName);
		return new ResponseEntity<ResponseData>(data,HttpStatus.OK);
		
	}
}

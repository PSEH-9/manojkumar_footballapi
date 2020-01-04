package com.football.report.service;

import com.football.report.pojo.StandingResponse;

public interface FootballService {
	public StandingResponse getStandingsData(String countryName, String leagueName, String teamName);
}

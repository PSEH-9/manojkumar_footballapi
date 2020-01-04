package com.football.report.service;

import com.football.report.pojo.ResponseData;

public interface FootballService {
	public ResponseData getStandingsData(String countryName, String leagueName, String teamName);
}

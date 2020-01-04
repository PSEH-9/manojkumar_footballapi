package com.football.report.service;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.football.report.pojo.Country;
import com.football.report.pojo.League;
import com.football.report.pojo.StandingResponse;
import com.football.report.pojo.Standing;
import com.football.report.pojo.Team;


@Component
public class FootballServiceImple implements FootballService{
	private static final String base_url="https://apiv2.apifootball.com/?action=";
	private static final String apiKey = "&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
	DefaultHttpClient httpClient;
	ObjectMapper mapper;
	
	
	{	mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
		httpClient = new DefaultHttpClient();
	}
	
	
	@Override
	public StandingResponse getStandingsData(String countryName, String leagueName, String teamName) {
		StandingResponse respData = new StandingResponse();
		
		Country c = getCountry(countryName);
		if(c != null) {
			respData.setCountryName(c.getCountry_name());
			respData.setCountryId(c.getCountry_id());
			
			League league = getLeagues(leagueName, c.getCountry_id());
			if(league != null) {
				respData.setLeagueName(league.getLeague_name());
				respData.setLeagueId(league.getLeague_id());
				
				Team team = getTeam(teamName, league.getLeague_id());
				if(team != null) {
					respData.setTeamId(team.getTeam_key());
					respData.setTeamName(team.getTeam_name());
				}
				
				Standing standing = getStanding(teamName, league.getLeague_id());
				respData.setOverAllPosition(standing.getOverall_league_position());
			}
			
			
			
		}
		
		return respData;
	}
	
	private Standing getStanding(String teamName, String league_id) {
		try {
			HttpGet getRequest = new HttpGet(buildUrl("get_standings&league_id=" + league_id));
			String result = getResponsefromApi(getRequest);
			List<Standing> standingList = mapper.readValue(result, new TypeReference<List<Standing>>(){});
			return standingList.stream().filter(ts -> league_id.equalsIgnoreCase(ts.getLeague_id())).findAny().get();
		}catch(Exception e){
			throw new RuntimeException("something went wrong while fethching standing " +e);
		}
	}

	private Team getTeam(String teamName, String leagueId) {
		try {
			HttpGet getRequest = new HttpGet(buildUrl("get_teams&league_id=" + leagueId));
			String result = getResponsefromApi(getRequest);
			List<Team> teamList = mapper.readValue(result, new TypeReference<List<Team>>(){});
			return teamList.stream().filter(team -> teamName.equalsIgnoreCase(team.getTeam_name())).findAny().get();
		}catch(Exception e){
			throw new RuntimeException("something went wrong while fethching teams " +e);
		}
	}

	private League getLeagues(String leagueName, String countryId) {
		try {
			HttpGet getRequest = new HttpGet(buildUrl("get_leagues&country_id=" + countryId));
			String result = getResponsefromApi(getRequest);
			
			List<League> leagueList = mapper.readValue(result, new TypeReference<List<League>>(){});
			return leagueList.stream().filter(league -> leagueName.equalsIgnoreCase(league.getLeague_name())&& countryId.equalsIgnoreCase(league.getCountry_id())).findAny().get();
		
		}catch(Exception e){
			throw new RuntimeException("something went wrong while fethching leagues " +e);
		}
	}

	private static String buildUrl(String params) {
		return base_url + params + apiKey;
	}
	
	private Country getCountry(String CountryName) {
		try {
			HttpGet getRequest = new HttpGet(buildUrl("get_countries"));
			String result = getResponsefromApi(getRequest);
			List<Country> teamResList = mapper.readValue(result, new TypeReference<List<Country>>(){});
			return teamResList.stream().filter(c->c.getCountry_name().equalsIgnoreCase(CountryName)).findAny().get();
		}catch(Exception e){
			throw new RuntimeException("something went wrong while fethching country " +e);
		}
	}
	
	private String getResponsefromApi(HttpGet request) {
		try {
			HttpResponse response = httpClient.execute(request);
			// verify the valid error code first
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}
			HttpEntity e = response.getEntity();
			String responseText = EntityUtils.toString(e);
			System.out.println("responseText -\n" + responseText);
			return responseText;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
}
	/*public static void main(String[] args) {
		ResponseData res = new FootballServiceImple().getStandingsData("England", "Championship", "Swansea");
		System.out.println(res.toString());
	}*/

}

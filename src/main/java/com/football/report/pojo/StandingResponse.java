package com.football.report.pojo;

public class StandingResponse {
	
	private String countryId;
	private String countryName;
	private String leagueId;
	private String leagueName;
	private String teamId;
	private String teamName;
	private String overAllPosition;
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getOverAllPosition() {
		return overAllPosition;
	}
	public void setOverAllPosition(String overAllPosition) {
		this.overAllPosition = overAllPosition;
	}
	@Override
	public String toString() {
		return "StandingResponse [countryId=" + countryId + ", countryName=" + countryName + ", leagueId=" + leagueId
				+ ", leagueName=" + leagueName + ", teamId=" + teamId + ", teamName=" + teamName + ", overAllPosition="
				+ overAllPosition + "]";
	}
	
	
	
	
}

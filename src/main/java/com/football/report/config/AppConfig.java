package com.football.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.football.report.service.FootballServiceImple;

@Configuration
public class AppConfig {
	@Bean
	FootballServiceImple footballService() {
		return new FootballServiceImple();
	}
}

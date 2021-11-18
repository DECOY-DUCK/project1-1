package com.ssafy.happyhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import com.ssafy.happyhouse.config.AdminKeyConfig;
import com.ssafy.happyhouse.config.AuthKeyConfig;
import com.ssafy.happyhouse.config.CorsConfig;

@SpringBootApplication
@PropertySource("classpath:/application.properties")
@EnableConfigurationProperties(value = {AuthKeyConfig.class, AdminKeyConfig.class, CorsConfig.class})
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

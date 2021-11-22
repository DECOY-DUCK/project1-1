package com.ssafy.happyhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import com.ssafy.happyhouse.config.AdminKeyProperties;
import com.ssafy.happyhouse.config.AuthKeyProperties;
import com.ssafy.happyhouse.config.CorsProperties;
import com.ssafy.happyhouse.config.MultipartProperties;

@SpringBootApplication
@PropertySource("classpath:/application.properties")
@EnableConfigurationProperties(value = {AuthKeyProperties.class, AdminKeyProperties.class, CorsProperties.class, MultipartProperties.class})
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

package com.ssafy.happyhouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.auth")
public class AuthKeyProperties {
	private String jwtSecret;
	private int jwtExpiresSec;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public int getJwtExpiresSec() {
		return jwtExpiresSec;
	}

	public void setJwtExpiresSec(int jwtExpiresSec) {
		this.jwtExpiresSec = jwtExpiresSec;
	}
}

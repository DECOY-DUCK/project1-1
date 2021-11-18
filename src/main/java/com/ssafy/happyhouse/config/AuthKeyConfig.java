package com.ssafy.happyhouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/* 
 * 토큰 생성시 관련한 property들 
 * adminkey랑 동일한 방식으로 사용하면 됩니다.
 * 더 추가할 거 있으면 이런 방식으로 작성해주세요! 
 */
@ConfigurationProperties(prefix = "custom.auth")
public class AuthKeyConfig {
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

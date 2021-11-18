package com.ssafy.happyhouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.cors")
public class CorsConfig {
	private String devOrigin;
	// private String origin;

	public String getDevOrigin() {
		return devOrigin;
	}

	public void setDevOrigin(String devOrigin) {
		this.devOrigin = devOrigin;
	}

//	public String getOrigin() {
//		return origin;
//	}
//
//	public void setOrigin(String origin) {
//		this.origin = origin;
//	}
}

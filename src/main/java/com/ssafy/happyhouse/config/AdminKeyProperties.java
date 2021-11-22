package com.ssafy.happyhouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.admin")
public class AdminKeyProperties {
	private String hostEmail;
	private String hostEmailPwd;

	public String getHostEmail() {
		return hostEmail;
	}

	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}

	public String getHostEmailPwd() {
		return hostEmailPwd;
	}

	public void setHostEmailPwd(String hostEmailPwd) {
		this.hostEmailPwd = hostEmailPwd;
	}
}

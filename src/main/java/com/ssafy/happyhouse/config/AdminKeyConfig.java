package com.ssafy.happyhouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/* 
 * 비밀번호 찾기할 때 이메일 보낼 호스트 이메일과 비밀번호 
 * (application.properties에 custom.admin.hostemail=aaa@aaa.com 이런식으로 작성하고
 *    사용하는 곳에서 
 * 		@Autowired 
 *      private AuthKeyConfig authKeyConfig; 
 *    한 다음에 메서드에서 쓸 때
 *      authKeyConfig.getHostEmail() 
 *    이렇게 쓰면 됩니당 
 *    확인 후 멘트 지워주세요~~!!
 * 	)
 */
@ConfigurationProperties(prefix = "custom.admin")
public class AdminKeyConfig {
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

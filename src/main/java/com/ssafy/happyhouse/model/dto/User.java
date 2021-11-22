package com.ssafy.happyhouse.model.dto;

public class User {
	private int no;
	private String email;
	private String password;
	private String name;
	private String authCode;
	private String joinDate;
	private String deleteDate;

	public User() {}
	 
	public User(int no, String email, String password, String name, String authCode, String joinDate,
			String deleteDate) {
		this.no = no;
		this.email = email;
		this.password = password;
		this.name = name;
		this.authCode = authCode;
		this.joinDate = joinDate;
		this.deleteDate = deleteDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Override
	public String toString() {
		return "User [no=" + no + ", email=" + email + ", password=" + password + ", name=" + name + ", authCode="
				+ authCode + ", joinDate=" + joinDate + ", deleteDate=" + deleteDate + "]";
	}

}

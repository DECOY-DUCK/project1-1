package com.ssafy.happyhouse.model.dto;

import java.util.List;

public class HouseReview {

	private int no;
	private int aptNo;
	private int authorNo;
	private String authorName;
	private String content;
	private String createdAt;
	private String modifiedAt;
	private List<Integer> likeUsers;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getAuthorNo() {
		return authorNo;
	}

	public void setAuthorNo(int authorNo) {
		this.authorNo = authorNo;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public int getAptNo() {
		return aptNo;
	}

	public void setAptNo(int aptNo) {
		this.aptNo = aptNo;
	}

	public List<Integer> getLikeUsers() {
		return likeUsers;
	}

	public void setLikeUsers(List<Integer> likeUsers) {
		this.likeUsers = likeUsers;
	}

}

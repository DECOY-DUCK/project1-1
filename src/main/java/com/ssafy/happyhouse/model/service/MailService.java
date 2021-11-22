package com.ssafy.happyhouse.model.service;

public interface MailService {

	void sendEmail(String to, String subject, String body);

}
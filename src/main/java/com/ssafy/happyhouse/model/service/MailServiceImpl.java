package com.ssafy.happyhouse.model.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Override
	public void sendEmail(String to, String subject, String body) {
		
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);
		try {
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		sender.send(msg);
	}
}

package com.sec.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailService {
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	private JavaMailSender javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMessage(String email) {
		SimpleMailMessage message = null;
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(email);
			message.setSubject("Sikeres regisztráció!");
			message.setText("Kedves "+ email +"! \n \n Köszönjük, hogy regisztráltál az oldalunkra!");
			javaMailSender.send(message);
			
		}catch(Exception ex) {
			log.error("Hiba az email küldésekor az alábbi címre: "+ email + "!" + ex);
		}
	}
	
}

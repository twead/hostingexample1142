package com.sec.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sec.entity.User;

@Service
public class EmailServiceImpl {
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	@Value("${url}")
	private String Url;
	
	private JavaMailSender javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMessage(String email, String activation, String fullName) {
		SimpleMailMessage message = null;
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(email);
			message.setSubject("Sikeres regisztráció!");
			message.setText("Kedves "+ fullName +"! \n \nKöszönjük, hogy regisztráltál az oldalunkra!\n\n"
					+ "Kérlek kattints a linkre profilod aktiválásához: " + Url + "activation/" + activation);
			javaMailSender.send(message);
			
		}catch(Exception ex) {
			log.error("Hiba az email küldésekor az alábbi címre: "+ email + "!" + ex);
		}
	}
	
	@Async
	public void sendEmail(String to, String subject, String msg) {
		
		MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setText("<html><body>"+msg+"</body></html>", true);
            helper.setFrom(MESSAGE_FROM);
            helper.setTo(to);
            helper.setSubject(subject);

        } catch (MessagingException e) {
            System.out.println("HIBA"+e);
            e.printStackTrace();
        }
        javaMailSender.send(message);

	}
	
}

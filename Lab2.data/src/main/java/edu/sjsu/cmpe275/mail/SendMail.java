package edu.sjsu.cmpe275.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import edu.sjsu.cmpe275.user.UserProfile;
import org.springframework.core.io.ClassPathResource;

import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;



public class SendMail {
	@Autowired
    private JavaMailSender javaMailSender;

	public MailMessage welcomeMail(UserProfile user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("mbarsainyan21@gmail.com");
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Welcome to the Greenhouse!");
		
		mailMessage.setText("abc");
		return mailMessage;
	}
public void send() {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo("mbarsainyan21@gmail.com");
            helper.setReplyTo("mbarsainyan21@gmail.com");
            helper.setFrom("mbarsainyan21@gmail.com");
            helper.setSubject("Lorem ipsum");
            helper.setText("Lorem ipsum dolor sit amet [...]");
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {}
        javaMailSender.send(mail);
        //return helper;
    }
}

package com.boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils 
{
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String to,String subject,String body) 
	{
		boolean isSent=false;
		try 
		{
			MimeMessage createMimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper =  new MimeMessageHelper(createMimeMessage);
			
			helper.setSubject(subject);
			helper.setText(body,true);
			
			//Pending
			helper.setTo(to);
			//helper.addAttachment(to, null);
			
			mailSender.send(createMimeMessage);
			
			isSent=true;
			
		} 
		catch (Exception e) 
		{
			System.out.println("Exception is occured in sendEmail method  "+e);	
			}
		
		return true;
	}
	
	

}

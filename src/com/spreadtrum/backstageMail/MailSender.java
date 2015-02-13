package com.spreadtrum.backstageMail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	String emailHost = "mail.spreadtrum.com";

	public static void sendMail(String htmlBody,String to,String cc,String subject) throws AddressException,
			MessagingException {

		MailSender javaEmail = new MailSender();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage(htmlBody,to,cc,subject);
		javaEmail.sendEmail();
	}

	public static void main(String args[]) throws AddressException,
			MessagingException {

		MailSender javaEmail = new MailSender();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage("123123","qilong.yin","senxue.jing","你好");
		javaEmail.sendEmail();
	}

	public void setMailServerProperties() {

		emailProperties = new Properties();
		emailProperties.put("mail.smtp.host", emailHost);
		//emailProperties.put("mail.smtp.port", "25");
		emailProperties.put("mail.smtp.auth", "true");
	}

	public void createEmailMessage(String htmlBody,String to,String cc,String subject) throws AddressException,
			MessagingException {
		// 收件人
		//String to = "senxue.jing";
		//String cc = "qilong.yin";
		String[] toEmails = to.split(";");
		String[] ccEmails = cc.split(";");
		
		String emailSubject = subject;

		String emailBody = htmlBody;

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);
		// To
		//System.out.println(to);
		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmails[i]));
					//new InternetAddress(toEmails[i] + "/Spreadtrum@SPREADTRUM"));
		}
		// CC
		//System.out.println("cc"+cc);
		
		if (!cc.equals("")) {
			//System.out.println("cc"+ccEmails);
			for (int i = 0; i < ccEmails.length; i++) {
				emailMessage.addRecipient(Message.RecipientType.CC,
						new InternetAddress(ccEmails[i] ));
			}
		}
		emailMessage.setSubject(emailSubject);
		emailMessage.setFrom(new InternetAddress ("tjsdelab@spreadtrum.com"));
		emailMessage.setContent(emailBody, "text/html; charset=utf-8");
	}

	public void sendEmail() throws AddressException, MessagingException {
		// 发件人
		String fromUser = "qilong.yin";
		String fromUserEmailPassword = "12qwASzx12";
		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
	}

}
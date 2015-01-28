package com.spreadtrum.backmanage;

import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.mail.MailSender;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


public class MailSendAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String contents;
    private String To;
    private String CC;
    private String subject;

	public Boolean mail() throws AddressException,
	MessagingException, Exception {
	MailSender javaEmail = new MailSender();
	javaEmail.setMailServerProperties();
	contents = java.net.URLDecoder.decode(contents,"UTF-8");
	subject =  java.net.URLDecoder.decode(subject,"UTF-8");
	
	javaEmail.createEmailMessage(contents,To,CC,subject);
	
	//System.out.println(contents);
	javaEmail.sendEmail();
	return false;
	}
	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTo() {
		return To;
	}

	public void setTo(String to) {
		To = to;
	}

	public String getCC() {
		return CC;
	}

	public void setCC(String cC) {
		CC = cC;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
//	
//	public class MailSender {
//
//		Properties emailProperties;
//		Session mailSession;
//		MimeMessage emailMessage;
//		String emailHost = "mail.spreadtrum.com";
//
//		public void sendMail(String htmlBody,String to,String cc,String subject) throws AddressException,
//				MessagingException {
//
//			MailSender javaEmail = new MailSender();
//
//			javaEmail.setMailServerProperties();
//			javaEmail.createEmailMessage(htmlBody,to,cc,subject);
//			javaEmail.sendEmail();
//		}
//
//		public String mail(String args[]) throws AddressException,
//				MessagingException {
//
//			MailSender javaEmail = new MailSender();
//
//			javaEmail.setMailServerProperties();
//			javaEmail.createEmailMessage("123123","qilong.yin","senxue.jing","你好");
//			javaEmail.sendEmail();
//			return SUCCESS;
//		}
//
//		public void setMailServerProperties() {
//
//			emailProperties = new Properties();
//			emailProperties.put("mail.smtp.host", emailHost);
//			//emailProperties.put("mail.smtp.port", "25");
//			emailProperties.put("mail.smtp.auth", "true");
//		}
//
//		public void createEmailMessage(String htmlBody,String to,String cc,String subject) throws AddressException,
//				MessagingException {
//			// 收件人
//			//String to = "senxue.jing";
//			//String cc = "qilong.yin";
//			String[] toEmails = to.split(";");
//			String[] ccEmails = cc.split(";");
//			
//			String emailSubject = subject;
//
//			String emailBody = htmlBody;
//
//			mailSession = Session.getDefaultInstance(emailProperties, null);
//			emailMessage = new MimeMessage(mailSession);
//			// To
//			for (int i = 0; i < toEmails.length; i++) {
//				emailMessage.addRecipient(Message.RecipientType.TO,
//						new InternetAddress(toEmails[i] + "/Spreadtrum@SPREADTRUM"));
//			}
//			// CC
//			for (int i = 0; i < ccEmails.length; i++) {
//				emailMessage.addRecipient(Message.RecipientType.CC,
//						new InternetAddress(ccEmails[i] + "/Spreadtrum@SPREADTRUM"));
//			}
//			emailMessage.setSubject(emailSubject);
//			emailMessage.setFrom(new InternetAddress ("qilong.yin"));
//			emailMessage.setContent(emailBody, "text/html; charset=utf-8");
//		}
//
//		public void sendEmail() throws AddressException, MessagingException {
//			// 发件人
//			String fromUser = "qilong.yin";
//			String fromUserEmailPassword = "12qwASzx12";
//			Transport transport = mailSession.getTransport("smtp");
//
//			transport.connect(emailHost, fromUser, fromUserEmailPassword);
//			transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
//			transport.close();
//		}
//
//	}
//	
//	
//	




}

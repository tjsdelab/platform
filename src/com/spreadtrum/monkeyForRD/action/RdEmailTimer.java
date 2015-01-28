package com.spreadtrum.monkeyForRD.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.spreadtrum.monkeytest.dao.TestFormDAO;
import com.spreadtrum.monkeytest.dao.impl.TestFormDAOImpl;

public class RdEmailTimer {
	private static String PreTestForm;
	private static TestFormDAO testFormDAO =new TestFormDAOImpl();

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	String emailHost = "mail.spreadtrum.com";

	public void sendMail(String subject,String htmlBody,String to,String cc) throws AddressException,MessagingException {

		RdEmailTimer javaEmail = new RdEmailTimer();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage(subject,htmlBody,to,cc);
		javaEmail.sendEmail();
	}

	public static void main(String[] args)  {
		
		//PreTestForm = testFormDAO.getLastTestFormByProject("t8861a");
		Timer timer = new Timer();				
		timer.schedule(new RdTask(), 1000, 10000);
	}



	public void setMailServerProperties() {

		emailProperties = new Properties();
		emailProperties.put("mail.smtp.host", emailHost);
		//emailProperties.put("mail.smtp.port", "25");
		emailProperties.put("mail.smtp.auth", "true");
	}

	public void createEmailMessage(String subject,String htmlBody,String to,String cc) throws AddressException,
			MessagingException {
		// 收件人
		//String to = "qilong.yin";
		//String cc = "qilong.yin";
		String[] toEmails = to.split(";");
		String[] ccEmails = cc.split(";");
		
		String emailSubject = subject;

		String emailBody = htmlBody;

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);
		// To
		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmails[i] + "/Spreadtrum@SPREADTRUM"));
		}
		// CC
		for (int i = 0; i < ccEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.CC,
					new InternetAddress(ccEmails[i] + "/Spreadtrum@SPREADTRUM"));
		}
		emailMessage.setSubject(emailSubject);
		emailMessage.setFrom(new InternetAddress ("tjsdelab"));
		emailMessage.setContent(emailBody, "text/html; charset=utf-8");
	}

	public void sendEmail() throws AddressException, MessagingException {
		// 发件人
		String fromUser = "tjsdelab";
		String fromUserEmailPassword = "12abAB";
		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
	}

	public String getPreTestForm() {
		return PreTestForm;
	}

	public void setPreTestForm(String preTestForm) {
		PreTestForm = preTestForm;
	}

	public TestFormDAO getTestFormDAO() {
		return testFormDAO;
	}

	public void setTestFormDAO(TestFormDAO testFormDAO) {
		this.testFormDAO = testFormDAO;
	}

}


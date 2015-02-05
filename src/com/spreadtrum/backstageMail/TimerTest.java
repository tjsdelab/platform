package com.spreadtrum.backstageMail;

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

public class TimerTest {
	private static String PreTestForm;
	private static TestFormDAO testFormDAO =new TestFormDAOImpl();

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	String emailHost = "mail.spreadtrum.com";

	public void sendMail(String htmlBody) throws AddressException,MessagingException {

		TimerTest javaEmail = new TimerTest();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage(htmlBody);
		javaEmail.sendEmail();
	}

	public static void main(String[] args)  {
		PreTestForm = testFormDAO.getLastTestFormByProject("t8861a");
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 1000, 10000);
	}



	public void setMailServerProperties() {

		emailProperties = new Properties();
		emailProperties.put("mail.smtp.host", emailHost);
		//emailProperties.put("mail.smtp.port", "25");
		emailProperties.put("mail.smtp.auth", "true");
	}

	public void createEmailMessage(String htmlBody) throws AddressException,
			MessagingException {
		// 收件人
		String to = "qilong.yin";
		String cc = "qilong.yin";
		String[] toEmails = to.split(";");
		String[] ccEmails = cc.split(";");
		
		String emailSubject = "数据平台邮件提醒1";

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
		emailMessage.setFrom(new InternetAddress ("qilong.yin"));
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


package com.spreadtrum.backstageMail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailExample {

	public static void main(String args[]) throws Exception {

		String host = "exmail.spreadtrum.com"; // 发件人使用发邮件的电子信箱服务器
		String from = "qilong.yin@spreadtrum.com"; // 发邮件的出发地（发件人的信箱）
		String to = "qilong.yin@spreadtrum.com"; // 发邮件的目的地（收件人信箱）
		String password = "12qwASzx12";

		// Get system properties
		Properties props = System.getProperties();

		// Setup mail server
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port","25"); 
		props.put("mail.transport.protocol","smtp"); // 设置协议

		// Get session
		props.put("mail.smtp.auth", "false"); // 这样才能通过验证

		MyAuthenticator myauth = new MyAuthenticator("qilong.yin@spreadtrum.com",password);
		Session session = Session.getDefaultInstance(props,myauth);
		
		// session.setDebug(true);

		// Define message
		MimeMessage message = new MimeMessage(session);

		// Set the from address
		message.setFrom(new InternetAddress(from));

		// Set the to address
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		// Set the subject
		message.setSubject("测试程序！");

		// Set the content
		message.setText("这是用java写的发送电子邮件的测试程序！");

		message.saveChanges();

		Transport.send(message);

	}
}

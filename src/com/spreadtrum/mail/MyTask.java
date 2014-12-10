package com.spreadtrum.mail;

import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.spreadtrum.monkeytest.dao.TestFormDAO;
import com.spreadtrum.monkeytest.dao.impl.TestFormDAOImpl;

class MyTask extends TimerTask {  
	private TimerTest test = new TimerTest();
	private static TestFormDAO testFormDAO =new TestFormDAOImpl();
	private String preFormName;	 
	
    @Override  
    public void run() { 
    	System.out.println(test.getPreTestForm());
    	preFormName = test.getPreTestForm();
    	try {
    		String currentFormName=testFormDAO.getLastTestFormByProject("t8861a");
    		if(currentFormName.equals(preFormName)){
    			System.out.println("currentFormName:"+currentFormName);
    		} else {
			    test.sendMail("new currentFormName:" + currentFormName);
			    test.setPreTestForm(currentFormName);
			    System.out.println("PreTestForm:"+test.getPreTestForm());
    		}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  
    }

	public String getPreFormName() {
		return preFormName;
	}

	public void setPreFormName(String preFormName) {
		this.preFormName = preFormName;
	}

	public static TestFormDAO getTestFormDAO() {
		return testFormDAO;
	}

	public static void setTestFormDAO(TestFormDAO testFormDAO) {
		MyTask.testFormDAO = testFormDAO;
	}  
}

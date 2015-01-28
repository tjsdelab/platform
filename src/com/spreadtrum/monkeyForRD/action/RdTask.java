package com.spreadtrum.monkeyForRD.action;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.spreadtrum.monkeytest.dao.TestFormDAO;
import com.spreadtrum.monkeytest.dao.impl.TestFormDAOImpl;
class RdTask extends TimerTask {
 
	private RdEmailTimer rdEmail = new RdEmailTimer();
	private List<MonkeyForRDPerformance> emailMembers = new ArrayList<MonkeyForRDPerformance>() ;
	private MonkeyForRDHomeAction rdHomeAction = new MonkeyForRDHomeAction();
	
    @Override  
    public void run() { 

    	try {
    		
    		emailMembers = rdHomeAction.emailToNotDoInAMonth();
    		if (null != emailMembers){
    		    for (int i=0; i<emailMembers.size(); i++){
    		    	System.out.println(emailMembers.get(i).getGroupLeader());
    		    	rdEmail.sendMail(emailMembers.get(i).getName(),"你小子有日子没跑monkey了啊，已经告诉了你的老大，注意点！！！","qilong.yin","qilong.yin");
    		    }
    		}
    		
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
    }
  
}

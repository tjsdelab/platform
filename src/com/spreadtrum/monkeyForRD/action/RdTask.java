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
    		    	System.out.println(emailMembers.get(i).getName() +"30:"+ emailMembers.get(i).getGroupLeader());
    		    	rdEmail.sendMail("数据平台邮件提醒","Dear "+emailMembers.get(i).getName() + ": 您已经有一个月未进行monkey自测，请确认，谢谢",emailMembers.get(i).getEmailAddr(),emailMembers.get(i).getGroupLeader()+";"+"qilong.yin@spreadtrum.com");
    		    	//rdEmail.sendMail("数据平台邮件提醒",emailMembers.get(i).getEmailAddr() + "您已经有一个月未进行monkey自测，请确认，谢谢","qilong.yin@spreadtrum.com",emailMembers.get(i).getGroupLeader()+";"+emailMembers.get(i).getDepartmentLeader());
    		    }
    		}
    		emailMembers = rdHomeAction.emailToNotDoIn10Days();
    		if (null != emailMembers){
    		    for (int i=0; i<emailMembers.size(); i++){
    		    	System.out.println(emailMembers.get(i).getName() +"10:"+ emailMembers.get(i).getGroupLeader());
    		    	rdEmail.sendMail("数据平台邮件提醒","Dear "+emailMembers.get(i).getName() + ": 您已经有10天未进行monkey自测，请确认，谢谢",emailMembers.get(i).getEmailAddr(),emailMembers.get(i).getGroupLeader()+";"+"qilong.yin@spreadtrum.com");
    		    }
    		}
    		emailMembers = rdHomeAction.emailToNotDoIn1Days();
    		if (null != emailMembers){
    		    for (int i=0; i<emailMembers.size(); i++){
    		    	System.out.println(emailMembers.get(i).getName());
    		    	rdEmail.sendMail("数据平台邮件提醒","Dear "+emailMembers.get(i).getName() + ": 您昨天未进行monkey自测，请确认，谢谢",emailMembers.get(i).getEmailAddr(),"qilong.yin@spreadtrum.com");
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

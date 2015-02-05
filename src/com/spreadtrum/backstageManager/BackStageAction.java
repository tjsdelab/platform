package com.spreadtrum.backstageManager;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BackStageAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	private QueryUser queryUser = new QueryUser();
	
	public String loginVerify() {
		System.out.println("username:"+userName);
		System.out.println("password:"+passWord);
		if(null == userName || null == passWord){
			return "loginFail";
		} else {
			String checkResult = queryUser.checkUser(userName);
			System.out.println("checkResult:"+checkResult);
			if (null == checkResult || !passWord.equals(checkResult)){
				System.out.println("failed");
				return "loginFail";
			} else {
				System.out.println("success");
				return SUCCESS;
			}			
		}
	}
	
	public String deleteUser() {
		return SUCCESS;
	}
	
	public String addUser() {
		return SUCCESS;
	}
	
	public String userEdit() {

		return SUCCESS;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


}

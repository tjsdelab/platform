package com.spreadtrum.userManager;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private List<UserBean> allUser = new ArrayList<UserBean>();
	private QueryUser queryUser = new QueryUser();
	private DeleteUser deleteUser = new DeleteUser();
	private AddUser addUser = new AddUser();
	private EditUser editUser = new EditUser();
	private String userName;
	private String passWord;
	private String type;
	private int id;
	public String query() {
		
		allUser = queryUser.getAllUser();
		return SUCCESS;
	}
	
	public String deleteUser() {
		deleteUser.deleteUser(id);
		return SUCCESS;
	}
	
	public String addUser() {
		UserBean user = new UserBean();
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setType(type);
		addUser.addUser(user);
		return SUCCESS;
	}
	
	public String userEdit() {
		System.out.println("*********"+id);
		editUser.updateUser(id,userName,passWord,type);

		return SUCCESS;
	}
	
	public List<UserBean> getAllUser() {
		return allUser;
	}
	public void setAllUser(List<UserBean> allUser) {
		this.allUser = allUser;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public DeleteUser getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(DeleteUser deleteUser) {
		this.deleteUser = deleteUser;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AddUser getAddUser() {
		return addUser;
	}

	public void setAddUser(AddUser addUser) {
		this.addUser = addUser;
	}

	public EditUser getEditUser() {
		return editUser;
	}

	public void setEditUser(EditUser editUser) {
		this.editUser = editUser;
	}

}

package cn.edu.fjnu.shop.action;

import cn.edu.fjnu.shop.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport{

	private int adminID;
	private String orignPassword;
	private String newPassword;
	private String commitNewPassword;
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getOrignPassword() {
		return orignPassword;
	}
	public void setOrignPassword(String orignPassword) {
		this.orignPassword = orignPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getCommitNewPassword() {
		return commitNewPassword;
	}
	public void setCommitNewPassword(String commitNewPassword) {
		this.commitNewPassword = commitNewPassword;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	public String changePassword() throws Exception {
		// TODO Auto-generated method stub
		//if()
		String userID= ActionContext.getContext().getSession().get("userID").toString();
		adminID=Integer.parseInt(userID);
		//System.out.println(adminID);
		UserService userService=new UserService();
		if(userService.isValidate(adminID, orignPassword)){
			if(newPassword.equals(commitNewPassword)){
				userService.changePassword(adminID, newPassword);
				return "success";
			}
				
		}else{
			return "input";
		}
		return "input";
	}
	
	public String exitSystem() throws Exception {
		// TODO Auto-generated method stub
		//if()
		ActionContext.getContext().getSession().remove("userID");
		return "login";
	}
	
}

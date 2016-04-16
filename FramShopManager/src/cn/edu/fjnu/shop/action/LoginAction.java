/**
 * 
 */
package cn.edu.fjnu.shop.action;

import java.util.Map;

import cn.edu.fjnu.shop.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GaoFei
 *
 */
public class LoginAction extends ActionSupport{

	private int userID;
	private String password;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	public String login() throws Exception {
		// TODO Auto-generated method stub
		/**登录验证模块*/
		
		UserService userService=new UserService();
		
		
		if(userService.isValidate(userID, password)){
			/**用户名和密码验证成功,登录至主界面*/
			Map<String, Object> sessions = ActionContext.getContext().getSession();
			sessions.put("userID", userID);
			return SUCCESS;
		}else{
			
			return INPUT;
		}
	//	return SUCCESS;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
		System.out.println(userID);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		System.out.println(password);
	}
	
	
}

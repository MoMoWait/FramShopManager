/**
 * 
 */
package cn.edu.fjnu.shop.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.edu.fjnu.shop.domain.User;
import cn.edu.fjnu.shop.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GaoFei
 * 
 */
public class UserManagerAction extends ActionSupport {

	/** 用户总数 */
	private int userNumber;
	/** 每页显示的用户数 */
	private int pageCount = 10;
	/** 用户列表 */
	private List<User> users = new ArrayList<User>();
	/** 总页数 */
	private int pages;
	/** 当前页数 */
	private int currentPage = 1;
	/** 跳转页数 */
	private int goPage = 1;
	/** 是否是跳转至指定的页数 */
	private String goSpecial = "false";
	/** 是否删除指定ID的用户 */
	private String deleteUser = "false";
	/** 删除(修改)用户的ID */
	private int changeID;
	private User user;
	/** 搜索类型 */
	private String searchType="帐号";
	/** 搜索值 */
	private String searchValue;
	/** 是查看还是搜索 */
	private String type = "look";

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public String userManager() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("userManager");
		/** 读取数据库中的内容 */
		UserService userService = new UserService();
		/** 判断是否是删除 */
		if ("true".equals(deleteUser)) {
			userService.deleteUser(changeID);
		}
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("type", type);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchValue", searchValue);
		if("search".equals(type)&&searchValue!=null&&!searchValue.equals(""))
			return "search";
		
		userNumber = userService.getUserCount();

		if (userNumber % pageCount > 0) {
			pages = userNumber / pageCount + 1;
		} else {
			pages = userNumber / pageCount;
		}
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {

			users = userService.getUsers(currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;

			users = userService.getUsers(goPage, pageCount);

		}

		// users=userService.findAllUser();
		// this.users=
		return "success";
	}

	public String changeUser() throws Exception {

		System.out.println("changeUser操作类型:"+type);
		UserService userService = new UserService();
		user = userService.findUser(changeID);
		return "success";
	}

	public String commitChange() throws Exception {

		System.out.println("commitChange操作类型:"+type);
		UserService userService = new UserService();
		userService.ChangeUser(user.getID(), user);
		return "success";
	}
	
	public String commitAdd() throws Exception {

		//System.out.println("commitChange操作类型:"+type);

		UserService userService=new UserService();
		userService.addUser(user);
		userNumber = userService.getUserCount();

		if (userNumber % pageCount > 0) {
			pages = userNumber / pageCount + 1;
		} else {
			pages = userNumber / pageCount;
		}
		currentPage=pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {

			users = userService.getUsers(currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;

			users = userService.getUsers(goPage, pageCount);

		}
		return "success";
	
	}
	
	public String searchUser() throws Exception {

		UserService userService = new UserService();
		userNumber=userService.getSearchCount(searchType, searchValue);
		if (userNumber % pageCount > 0) {
			pages = userNumber / pageCount + 1;
		} else {
			pages = userNumber / pageCount;
		}
		
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {
			
			users = userService.getSearchUsers(searchType, searchValue, currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;
			
			users = userService.getSearchUsers(searchType,searchValue,goPage, pageCount);

		}
		return "success";
	}

	public String addUser() throws Exception{
		
		return "success";
	}
	
	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getGoPage() {
		return goPage;
	}

	public void setGoPage(int goPage) {
		this.goPage = goPage;
	}

	public String getGoSpecial() {
		return goSpecial;
	}

	public void setGoSpecial(String goSpecial) {
		this.goSpecial = goSpecial;
	}

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public int getChangeID() {
		return changeID;
	}

	public void setChangeID(int changeID) {
		this.changeID = changeID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
		
	}

	public String getSearchValue() {
		
		return searchValue;
		//if(searchValue)
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

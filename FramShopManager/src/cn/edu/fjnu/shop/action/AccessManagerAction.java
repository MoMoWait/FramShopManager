/**
 * 
 */
package cn.edu.fjnu.shop.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.edu.fjnu.shop.domain.Access;
import cn.edu.fjnu.shop.service.AccessService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GaoFei
 * 
 */
public class AccessManagerAction extends ActionSupport {

	/** 用户总数 */
	private int accessNumber;
	/** 每页显示的用户数 */
	private int pageCount = 10;
	/** 用户列表 */
	private List<Access> accesss = new ArrayList<Access>();
	/** 总页数 */
	private int pages;
	/** 当前页数 */
	private int currentPage = 1;
	/** 跳转页数 */
	private int goPage = 1;
	/** 是否是跳转至指定的页数 */
	private String goSpecial = "false";
	/** 是否删除指定ID的用户 */
	private String deleteAccess = "false";
	/** 删除(修改)用户的ID */
	private int changeID;
	private Access access;
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

	public String accessManager() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("accessManager");
		/** 读取数据库中的内容 */
		AccessService accessService = new AccessService();
		/** 判断是否是删除 */
		if ("true".equals(deleteAccess)) {
			accessService.deleteAccess(changeID);
		}
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("type", type);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchValue", searchValue);
		if("search".equals(type)&&searchValue!=null&&!searchValue.equals(""))
			return "search";
		
		accessNumber = accessService.getAccessCount();

		if (accessNumber % pageCount > 0) {
			pages = accessNumber / pageCount + 1;
		} else {
			pages = accessNumber / pageCount;
		}
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {

			accesss = accessService.getAccesss(currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;

			accesss = accessService.getAccesss(goPage, pageCount);

		}

		// accesss=accessService.findAllAccess();
		// this.accesss=
		return "success";
	}

	public String changeAccess() throws Exception {

		System.out.println("changeAccess操作类型:"+type);
		AccessService accessService = new AccessService();
		access = accessService.findAccess(changeID);
		return "success";
	}
	
	public String searchAccess() throws Exception {

		AccessService accessService = new AccessService();
		accessNumber=accessService.getSearchCount(searchType, searchValue);
		if (accessNumber % pageCount > 0) {
			pages = accessNumber / pageCount + 1;
		} else {
			pages = accessNumber / pageCount;
		}
		
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {
			
			accesss = accessService.getSearchAccesss(searchType, searchValue, currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;
			
			accesss = accessService.getSearchAccesss(searchType,searchValue,goPage, pageCount);

		}
		return "success";
	}

	public String addAccess() throws Exception{
		
		return "success";
	}
	
	public int getAccessNumber() {
		return accessNumber;
	}

	public void setAccessNumber(int accessNumber) {
		this.accessNumber = accessNumber;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<Access> getAccesss() {
		return accesss;
	}

	public void setAccesss(List<Access> accesss) {
		this.accesss = accesss;
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

	public String getDeleteAccess() {
		return deleteAccess;
	}

	public void setDeleteAccess(String deleteAccess) {
		this.deleteAccess = deleteAccess;
	}

	public int getChangeID() {
		return changeID;
	}

	public void setChangeID(int changeID) {
		this.changeID = changeID;
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
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

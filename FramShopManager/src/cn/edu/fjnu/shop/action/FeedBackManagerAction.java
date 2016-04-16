/**
 * 
 */
package cn.edu.fjnu.shop.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.edu.fjnu.shop.domain.FeedBack;
import cn.edu.fjnu.shop.service.FeedBackService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GaoFei
 * 
 */
public class FeedBackManagerAction extends ActionSupport {

	/** 用户总数 */
	private int feedbackNumber;
	/** 每页显示的用户数 */
	private int pageCount = 10;
	/** 用户列表 */
	private List<FeedBack> feedbacks = new ArrayList<FeedBack>();
	/** 总页数 */
	private int pages;
	/** 当前页数 */
	private int currentPage = 1;
	/** 跳转页数 */
	private int goPage = 1;
	/** 是否是跳转至指定的页数 */
	private String goSpecial = "false";
	/** 是否删除指定ID的用户 */
	private String deleteFeedBack = "false";
	/** 删除(修改)用户的ID */
	private int changeID;
	private FeedBack feedback;
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

	public String feedbackManager() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("feedbackManager");
		/** 读取数据库中的内容 */
		FeedBackService feedbackService = new FeedBackService();
		/** 判断是否是删除 */
		if ("true".equals(deleteFeedBack)) {
			feedbackService.deleteFeedBack(changeID);
		}
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("type", type);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchValue", searchValue);
		if("search".equals(type)&&searchValue!=null&&!searchValue.equals(""))
			return "search";
		
		feedbackNumber = feedbackService.getFeedBackCount();
		
		if (feedbackNumber % pageCount > 0) {
			pages = feedbackNumber / pageCount + 1;
		} else {
			pages = feedbackNumber / pageCount;
		}
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {

			feedbacks = feedbackService.getFeedBacks(currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;

			feedbacks = feedbackService.getFeedBacks(goPage, pageCount);

		}

		// feedbacks=feedbackService.findAllFeedBack();
		// this.feedbacks=
		return "success";
	}

	public String changeFeedBack() throws Exception {

		System.out.println("changeFeedBack操作类型:"+type);
		FeedBackService feedbackService = new FeedBackService();
		feedback = feedbackService.findFeedBack(changeID);
		return "success";
	}
	
	public String searchFeedBack() throws Exception {

		FeedBackService feedbackService = new FeedBackService();
		feedbackNumber=feedbackService.getSearchCount(searchType, searchValue);
		if (feedbackNumber % pageCount > 0) {
			pages = feedbackNumber / pageCount + 1;
		} else {
			pages = feedbackNumber / pageCount;
		}
		
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {
			
			feedbacks = feedbackService.getSearchFeedBacks(searchType, searchValue, currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;
			
			feedbacks = feedbackService.getSearchFeedBacks(searchType,searchValue,goPage, pageCount);

		}
		return "success";
	}

	public String addFeedBack() throws Exception{
		
		return "success";
	}
	
	public int getFeedBackNumber() {
		return feedbackNumber;
	}

	public void setFeedBackNumber(int feedbackNumber) {
		this.feedbackNumber = feedbackNumber;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<FeedBack> getFeedBacks() {
		return feedbacks;
	}

	public void setFeedBacks(List<FeedBack> feedbacks) {
		this.feedbacks = feedbacks;
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

	public String getDeleteFeedBack() {
		return deleteFeedBack;
	}

	public void setDeleteFeedBack(String deleteFeedBack) {
		this.deleteFeedBack = deleteFeedBack;
	}

	public int getChangeID() {
		return changeID;
	}

	public void setChangeID(int changeID) {
		this.changeID = changeID;
	}

	public FeedBack getFeedBack() {
		return feedback;
	}

	public void setFeedBack(FeedBack feedback) {
		this.feedback = feedback;
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

	public int getFeedbackNumber() {
		return feedbackNumber;
	}

	public void setFeedbackNumber(int feedbackNumber) {
		this.feedbackNumber = feedbackNumber;
	}

	public List<FeedBack> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<FeedBack> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public FeedBack getFeedback() {
		return feedback;
	}

	public void setFeedback(FeedBack feedback) {
		this.feedback = feedback;
	}

	
}

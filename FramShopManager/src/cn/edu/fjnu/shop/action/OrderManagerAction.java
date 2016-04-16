package cn.edu.fjnu.shop.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.edu.fjnu.shop.domain.Order;
import cn.edu.fjnu.shop.service.OrderService;
import cn.edu.fjnu.shop.service.OrderService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GaoFei
 * 
 */
public class OrderManagerAction extends ActionSupport {

	
	/** 用户总数 */
	private int orderNumber;
	/** 每页显示的用户数 */
	private int pageCount = 8;
	/** 用户列表 */
	private List<Order> orders = new ArrayList<Order>();
	/** 总页数 */
	private int pages;
	/** 当前页数 */
	private int currentPage = 1;
	/** 跳转页数 */
	private int goPage = 1;
	/** 是否是跳转至指定的页数 */
	private String goSpecial = "false";
	/** 是否删除指定ID的用户 */
	private String deleteOrder = "false";
	/** 删除(修改)用户的ID */
	private String changeID;
	private Order order;
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

	public String orderManager() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("orderManager");
		/** 读取数据库中的内容 */
		OrderService orderService = new OrderService();
		/** 判断是否是删除 */
		if ("true".equals(deleteOrder)) {
			orderService.deleteOrder(changeID);
		}
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("type", type);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchValue", searchValue);
		if("search".equals(type)&&searchValue!=null&&!searchValue.equals(""))
			return "search";
		
		orderNumber = orderService.getOrderCount();

		if (orderNumber % pageCount > 0) {
			pages = orderNumber / pageCount + 1;
		} else {
			pages = orderNumber / pageCount;
		}
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {

			orders = orderService.getOrders(currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;

			orders = orderService.getOrders(goPage, pageCount);

		}

		// orders=orderService.findAllOrder();
		// this.orders=
		return "success";
	}

	public String changeOrder() throws Exception {

		//System.out.println("changeOrder操作类型:"+type);
		OrderService orderService = new OrderService();
		order = orderService.findOrder(changeID);
		return "success";
	}

	public String commitChange() throws Exception {

		//System.out.println("commitChange操作类型:"+type);
		OrderService orderService = new OrderService();
	//	System.out.println(changeID);
		
		orderService.ChangeOrder(order.getOrderID(), order);
		return "success";
	}
	
	public String searchOrder() throws Exception {

		OrderService orderService = new OrderService();
		orderNumber=orderService.getSearchCount(searchType, searchValue);
		if (orderNumber % pageCount > 0) {
			pages = orderNumber / pageCount + 1;
		} else {
			pages = orderNumber / pageCount;
		}
		
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {
			
			orders = orderService.getSearchOrders(searchType, searchValue, currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;
			
			orders = orderService.getSearchOrders(searchType,searchValue,goPage, pageCount);

		}
		return "success";
	}

	public String addOrder() throws Exception{
		
		return "success";
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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

	public String getDeleteOrder() {
		return deleteOrder;
	}

	public void setDeleteOrder(String deleteOrder) {
		this.deleteOrder = deleteOrder;
	}

	public String getChangeID() {
		return changeID;
	}

	public void setChangeID(String changeID) {
		this.changeID = changeID;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

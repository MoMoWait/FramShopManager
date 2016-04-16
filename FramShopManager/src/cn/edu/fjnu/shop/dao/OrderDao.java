/**
 * 
 */
package cn.edu.fjnu.shop.dao;

import java.util.List;

import cn.edu.fjnu.shop.domain.Order;

/**
 * @author GaoFei
 * 
 */
public interface OrderDao {

	
	public int deleteOrder(String orderID);
	public void ChangeOrder(String orderID,Order order);
	public Order findOrder(String orderID);
	public int getOrderCount();
	public List<Order> getOrders(int newPage,int pageNumber);
	public int getSearchCount(String type,String searchName);
	public List<Order> getSearchOrders(String type,String searchName,int newPage,int pageNumber);
	
}

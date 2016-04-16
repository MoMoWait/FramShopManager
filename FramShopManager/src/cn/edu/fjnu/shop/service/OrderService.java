package cn.edu.fjnu.shop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.fjnu.shop.dao.OrderDao;
import cn.edu.fjnu.shop.domain.Order;
import cn.edu.fjnu.shop.utils.DBUtils;

public class OrderService implements OrderDao {
	
	@Override
	public int deleteOrder(String orderID) {
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="delete FROM myorder where orderID=?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setString(1, orderID);
				int rows= preparedStatement.executeUpdate();
				sql="DELETE FROM orderdetail WHERE orderID=?";
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, orderID);
				rows= preparedStatement.executeUpdate();
				preparedStatement.close();
				//List<Order> orders=new ArrayList<Order>();
				//int rows=pr
				DBUtils.closeConn();
				return rows;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
//		return null;
		return -1;
	}

	@Override
	public void ChangeOrder(String orderID, Order order) {
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		System.out.println(orderID);
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="update myorder set orderTime=?,expireTime=?,orderState=?,orderMoney=? where orderID=?";
				
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setString(1, order.getOrderTime());
				preparedStatement.setString(2, order.getExpireTime());
				preparedStatement.setString(3, order.getOrderState());
				preparedStatement.setFloat(4, order.getOrderMoney());
				preparedStatement.setString(5, orderID);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				DBUtils.closeConn();
				
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
			}
			
		}
		//System.out.println(orderID);
		//System.out.println("connection is null");
		//return false;
	}

	@Override
	public Order findOrder(String orderID) {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT orderID,myorder.userID,orderTime,expireTime,orderState,orderMoney,name,"
						+ "phoneNumber,address FROM myorder,user WHERE myorder.userID=user.ID and orderID=? ORDER BY orderTime DESC";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setString(1, orderID);
				ResultSet resultSet= preparedStatement.executeQuery();
				Order order=new Order();
				if(resultSet.first()){
					//Order order=new Order();
					order.setOrderID(resultSet.getString(1));
					order.setUserID(resultSet.getInt(2));
					order.setOrderTime(resultSet.getString(3));
					order.setExpireTime(resultSet.getString(4));
					order.setOrderState(resultSet.getString(5));
					order.setOrderMoney(resultSet.getFloat(6));
					order.setName(resultSet.getString(7));
					order.setPhoneNumber(resultSet.getString(8));
					order.setAddress(resultSet.getString(9));
					//order.setpr
				}
				sql="SELECT productNumber,productName,productPrice,productBagPrice,unit "
						+ "from orderdetail,commonproduct where orderID=?"
						+ " AND orderdetail.productID=commonproduct.productID";
				PreparedStatement detailPreparedStatement=connection.prepareStatement(sql);
				detailPreparedStatement.setString(1, orderID);
				ResultSet rSet=detailPreparedStatement.executeQuery();
				StringBuffer sBuffer=new StringBuffer();
				while(rSet.next()){
					sBuffer.append(rSet.getString(2)).append("(")
					.append(rSet.getString(5).equals("斤")?rSet.getString(3)+"元/斤":rSet.getString(4)+"元/袋").append(rSet.getString(1))
					.append(");");
					
				}
				
			//	closeConn();
				detailPreparedStatement.close();
				rSet.close();
				
				order.setProducts(sBuffer.toString());
				//return sBuffer.toString();
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return order;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		return null;
	
	}
	
	
	/**
	 * 获订单总数
	 */
	@Override
	public int getOrderCount() {
		// TODO Auto-generated method stub
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT count(*) FROM myorder,user where myorder.userID=user.ID";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
			//	preparedStatement.setInt(1, x);
				ResultSet resultSet= preparedStatement.executeQuery();
				int num=0;
				if(resultSet.first()){
					//DBUtils.closeConn();
					num=resultSet.getInt(1);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return num;
				
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	
	@Override
	public List<Order> getOrders(int newPage, int pageNumber) {
		if(newPage == 0)
			return null;
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT orderID,myorder.userID,orderTime,expireTime,orderState,orderMoney,name,"
						+ "phoneNumber,address,city FROM myorder,user WHERE myorder.userID=user.ID ORDER BY orderTime DESC"
						+ " limit ?,?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1,(newPage-1)*pageNumber);
				preparedStatement.setInt(2,pageNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Order> orders=new ArrayList<Order>();
				while(resultSet.next()){
					Order order=new Order();
					order.setOrderID(resultSet.getString(1));
					order.setUserID(resultSet.getInt(2));
					order.setOrderTime(resultSet.getString(3));
					order.setExpireTime(resultSet.getString(4));
					order.setOrderState(resultSet.getString(5));
					order.setOrderMoney(resultSet.getFloat(6));
					order.setName(resultSet.getString(7));
					order.setPhoneNumber(resultSet.getString(8));
					order.setAddress(resultSet.getString(9));
					order.setCity(resultSet.getString(10));
					sql="SELECT productNumber,productName,productPrice,productBagPrice,unit "
							+ "from orderdetail,commonproduct where orderID=?"
							+ " AND orderdetail.productID=commonproduct.productID";
					PreparedStatement detailPreparedStatement=connection.prepareStatement(sql);
					detailPreparedStatement.setString(1, order.getOrderID());
					ResultSet rSet=detailPreparedStatement.executeQuery();
					StringBuffer sBuffer=new StringBuffer();
					while(rSet.next()){
						sBuffer.append(rSet.getString(2)).append("(")
						.append(rSet.getString(5).equals("斤")?rSet.getString(3)+"元/斤":rSet.getString(4)+"元/袋").append("*").append(rSet.getString(1))
						.append("); ");
					}
					detailPreparedStatement.close();
					rSet.close();
					order.setProducts(sBuffer.toString());
					orders.add(order);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return orders;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		return null;
	}
		
	@Override
	public int getSearchCount(String type, String searchName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//OrderService orderService=new OrderService();
				int count=0;
				String sql=null;
				PreparedStatement preparedStatement;
				if("姓名".equals(type)){
					sql="SELECT count(*) FROM myorder,user where myorder.userID=user.ID and user.name like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else if("下单时间".equals(type)){
					sql="SELECT count(*) FROM myorder where myorder.orderTime like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else if("期望时间".equals(type)){
					sql="SELECT count(*) FROM myorder where myorder.expireTime like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/** type=订单状态*/
					sql="SELECT count(*) FROM myorder where myorder.orderState like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM order limit ?,?";
				
				//preparedStatement.setString(2, searchName);
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.next()){
					count=resultSet.getInt(1);
				}
				
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return count;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		
		return 0;
	}

	@Override
	public List<Order> getSearchOrders(String type, String searchName,
			int newPage, int pageNumber) {

		if(newPage==0)
			return null;
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//OrderService orderService=new OrderService();
				String sql=null;
				PreparedStatement preparedStatement;
				if("姓名".equals(type)){
					sql="SELECT orderID,myorder.userID,orderTime,expireTime,orderState,orderMoney,name,"
							+ "phoneNumber,address,city FROM myorder,user WHERE myorder.userID=user.ID "
							+ "and user.name like ? ORDER BY orderTime DESC"
							+ " limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
					preparedStatement.setInt(2,(newPage-1)*pageNumber);
					preparedStatement.setInt(3,pageNumber);
				}else if("下单时间".equals(type)){
					sql="SELECT orderID,myorder.userID,orderTime,expireTime,orderState,orderMoney,name,"
							+ "phoneNumber,address,city FROM myorder,user WHERE myorder.userID=user.ID "
							+ "and myorder.orderTime like ? ORDER BY orderTime DESC"
							+ " limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
					preparedStatement.setInt(2,(newPage-1)*pageNumber);
					preparedStatement.setInt(3,pageNumber);
					//preparedStatement= connection.prepareStatement(sql);
					
				}else if("期望时间".equals(type)){
					sql="SELECT orderID,myorder.userID,orderTime,expireTime,orderState,orderMoney,name,"
							+ "phoneNumber,address,city FROM myorder,user WHERE myorder.userID=user.ID "
							+ "and myorder.expireTime like ? ORDER BY orderTime DESC"
							+ " limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
					preparedStatement.setInt(2,(newPage-1)*pageNumber);
					preparedStatement.setInt(3,pageNumber);
				}else{
					sql="SELECT orderID,myorder.userID,orderTime,expireTime,orderState,orderMoney,name,"
							+ "phoneNumber,address,city FROM myorder,user WHERE myorder.userID=user.ID "
							+ "and myorder.orderState like ? ORDER BY orderTime DESC"
							+ " limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
					preparedStatement.setInt(2,(newPage-1)*pageNumber);
					preparedStatement.setInt(3,pageNumber);
				}
				//String sql="SELECT * FROM order limit ?,?";
				
		/*		preparedStatement.setInt(2,(newPage-1)*pageNumber);
				preparedStatement.setInt(3,pageNumber);*/
				//preparedStatement.setString(2, searchName);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Order> orders=new ArrayList<Order>();
				while(resultSet.next()){
					Order order=new Order();
					order.setOrderID(resultSet.getString(1));
					order.setUserID(resultSet.getInt(2));
					order.setOrderTime(resultSet.getString(3));
					order.setExpireTime(resultSet.getString(4));
					order.setOrderState(resultSet.getString(5));
					order.setOrderMoney(resultSet.getFloat(6));
					order.setName(resultSet.getString(7));
					order.setPhoneNumber(resultSet.getString(8));
					order.setAddress(resultSet.getString(9));
					order.setCity(resultSet.getString(10));
					sql="SELECT productNumber,productName,productPrice,productBagPrice,unit "
							+ "from orderdetail,commonproduct where orderID=?"
							+ " AND orderdetail.productID=commonproduct.productID";
					PreparedStatement detailPreparedStatement=connection.prepareStatement(sql);
					detailPreparedStatement.setString(1, order.getOrderID());
					ResultSet rSet=detailPreparedStatement.executeQuery();
					StringBuffer sBuffer=new StringBuffer();
					while(rSet.next()){
						sBuffer.append(rSet.getString(2)).append("(")
						.append(rSet.getString(5).equals("斤")?rSet.getString(3)+"元/斤":rSet.getString(4)+"元/袋").append("*").append(rSet.getString(1))
						.append(");").append(" ");
					}
					
					detailPreparedStatement.close();
					rSet.close();
					order.setProducts(sBuffer.toString());
					orders.add(order);
				}
				
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return orders;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		
		return null;
	
	}
	
	
	
	
}

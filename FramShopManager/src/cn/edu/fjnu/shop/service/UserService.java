package cn.edu.fjnu.shop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.tag.el.sql.UpdateTag;

import cn.edu.fjnu.shop.config.Config;
import cn.edu.fjnu.shop.dao.UserDao;
import cn.edu.fjnu.shop.domain.User;
import cn.edu.fjnu.shop.utils.DBUtils;

public class UserService implements UserDao {

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		/** 验证用户名和密码是否正确 */
		Connection connection = DBUtils.getConn();
		if (connection != null) {
			try {

				String sql = "insert into user(name,sex,address,headPhoto,account,phoneNumber,password) values(?,?,?,?,?,?,?)";
				
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);
				preparedStatement.setString(1,user.getName());
				preparedStatement.setString(2,user.getSex());
				preparedStatement.setString(3,user.getAddress());
				preparedStatement.setString(4,Config.serverIP+"/ProductShopService/UserHeadPhoto/1.jpg");
				preparedStatement.setFloat(5,user.getAccount());
				preparedStatement.setString(6,user.getPhoneNumber());
				preparedStatement.setString(7,"123456");
				int rows = preparedStatement.executeUpdate();
				// List<User> users=new ArrayList<User>();
				// int rows=pr
				preparedStatement.close();
				DBUtils.closeConn();
				return rows;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();

			}

		}
		// return null;
		return -1;
	}

	@Override
	public int deleteUser(int userID) {
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="delete FROM user where ID=?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, userID);
				int rows= preparedStatement.executeUpdate();
				//List<User> users=new ArrayList<User>();
				//int rows=pr
				preparedStatement.close();
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
	public void ChangeUser(int userID, User user) {
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="update user set name=?,sex=?,address=?,account=?,phoneNumber=?,password=? WHERE ID=?";
				
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getSex());
				preparedStatement.setString(3, user.getAddress());
				preparedStatement.setFloat(4, user.getAccount());
				preparedStatement.setString(5, user.getPhoneNumber());
				preparedStatement.setString(6, user.getPassword());
				preparedStatement.setInt(7, userID);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				DBUtils.closeConn();
				
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
			}
			
		}
		//return false;
	}

	@Override
	public User findUser(int userID) {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT * FROM user where ID=?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, userID);
				ResultSet resultSet= preparedStatement.executeQuery();
				User user=new User();
				if(resultSet.first()){
					user.setID(resultSet.getInt(1));
					user.setName(resultSet.getString(2));
					user.setSex(resultSet.getString(3));
					user.setAddress(resultSet.getString(4));
					user.setHeadPhoto(resultSet.getString(5));
					user.setAccount(resultSet.getFloat(6));
					user.setPhoneNumber(resultSet.getString(7));
					user.setPassword(resultSet.getString(8));
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return user;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		return null;
	
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT * FROM user";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<User> users=new ArrayList<User>();
				while(resultSet.next()){
					User user=new User();
					user.setID(resultSet.getInt(1));
					user.setName(resultSet.getString(2));
					user.setSex(resultSet.getString(3));
					user.setAddress(resultSet.getString(4));
					user.setHeadPhoto(resultSet.getString(5));
					user.setAccount(resultSet.getFloat(6));
					user.setPhoneNumber(resultSet.getString(7));
					user.setPassword(resultSet.getString(8));
					users.add(user);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return users;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		return null;
	}

	
	@Override
	public boolean isValidate(int userID, String password) {
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT * FROM admin WHERE adminID=? AND password=?";
				
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				
				preparedStatement.setInt(1, userID);
				
				preparedStatement.setString(2, password);
				
				ResultSet resultSet= preparedStatement.executeQuery();
				
				if(resultSet.first()){
					preparedStatement.close();
					resultSet.close();
					DBUtils.closeConn();
					return true;
				}else{
					preparedStatement.close();
					resultSet.close();
					DBUtils.closeConn();
					return false;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
			}
			
		}
		return false;
	}


	/**
	 * 获取总用户数
	 */
	@Override
	public int getUserCount() {
		// TODO Auto-generated method stub
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT count(*) FROM user";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.first()){
					//DBUtils.closeConn();
					int num=resultSet.getInt(1);
					preparedStatement.close();
					resultSet.close();
					DBUtils.closeConn();
					return num;
				}
			
				
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	
	@Override
	public List<User> getUsers(int newPage, int pageNumber) {
		// TODO Auto-generated method stub
		
		if(newPage == 0)
			return null;
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT * FROM user limit ?,?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1,(newPage-1)*pageNumber);
				preparedStatement.setInt(2,pageNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<User> users=new ArrayList<User>();
				while(resultSet.next()){
					User user=new User();
					user.setID(resultSet.getInt(1));
					user.setName(resultSet.getString(2));
					user.setSex(resultSet.getString(3));
					user.setAddress(resultSet.getString(4));
					user.setHeadPhoto(resultSet.getString(5));
					user.setAccount(resultSet.getFloat(6));
					user.setPhoneNumber(resultSet.getString(7));
					user.setPassword(resultSet.getString(8));
					users.add(user);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return users;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		return null;
	}
	
	
	@Override
	public List<User> getUsers(String type, String searchName) {
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//UserService userService=new UserService();
				String sql=null;
				PreparedStatement preparedStatement;
				if("帐号".equals(type)){
					sql="select * from user where ID=?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, searchName);
				}else if("姓名".equals(type)){
					sql="select * from user where name like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else if("地址".equals(type)){
					sql="select * from user where address like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/** type=phoneNumber*/
					sql="select * from user where phoneNumber like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM user limit ?,?";
				
				//preparedStatement.setString(2, searchName);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<User> users=new ArrayList<User>();
				while(resultSet.next()){
					User user=new User();
					user.setID(resultSet.getInt(1));
					user.setName(resultSet.getString(2));
					user.setSex(resultSet.getString(3));
					user.setAddress(resultSet.getString(4));
					user.setHeadPhoto(resultSet.getString(5));
					user.setAccount(resultSet.getFloat(6));
					user.setPhoneNumber(resultSet.getString(7));
					user.setPassword(resultSet.getString(8));
					users.add(user);
				}
				
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return users;
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
				//UserService userService=new UserService();
				int count=0;
				String sql=null;
				PreparedStatement preparedStatement;
				if("帐号".equals(type)){
					sql="select count(*) from user where ID=?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, searchName);
				}else if("姓名".equals(type)){
					sql="select count(*) from user where name like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else if("地址".equals(type)){
					sql="select count(*) from user where address like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/** type=phoneNumber*/
					sql="select count(*) from user where phoneNumber like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM user limit ?,?";
				
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
	public List<User> getSearchUsers(String type, String searchName,
			int newPage, int pageNumber) {

		// TODO Auto-generated method stub
		if(newPage==0)
			return null;
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//UserService userService=new UserService();
				String sql=null;
				PreparedStatement preparedStatement;
				if("帐号".equals(type)){
					sql="select * from user where ID=? limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, searchName);
				}else if("姓名".equals(type)){
					sql="select * from user where name like ? limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else if("地址".equals(type)){
					sql="select * from user where address like ? limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/** type=phoneNumber*/
					sql="select * from user where phoneNumber like ? limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM user limit ?,?";
				
				preparedStatement.setInt(2,(newPage-1)*pageNumber);
				preparedStatement.setInt(3,pageNumber);
				//preparedStatement.setString(2, searchName);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<User> users=new ArrayList<User>();
				while(resultSet.next()){
					User user=new User();
					user.setID(resultSet.getInt(1));
					user.setName(resultSet.getString(2));
					user.setSex(resultSet.getString(3));
					user.setAddress(resultSet.getString(4));
					user.setHeadPhoto(resultSet.getString(5));
					user.setAccount(resultSet.getFloat(6));
					user.setPhoneNumber(resultSet.getString(7));
					user.setPassword(resultSet.getString(8));
					users.add(user);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return users;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		
		return null;
	
	}
	
	
	@Override
	public void changePassword(int adminID, String newPassword) {
		// TODO Auto-generated method stub


		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//UserService userService=new UserService();
				String sql="update admin set password=? where adminID=?";
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, newPassword);
				preparedStatement.setInt(2, adminID);
				preparedStatement.executeUpdate();
				//String sql="SELECT * FROM user limit ?,?";
				preparedStatement.close();
				DBUtils.closeConn();
			
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		
	//	return null;
	
	
	}
}

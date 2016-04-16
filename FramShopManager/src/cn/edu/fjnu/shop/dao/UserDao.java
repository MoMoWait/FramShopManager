/**
 * 
 */
package cn.edu.fjnu.shop.dao;

import java.util.List;

import cn.edu.fjnu.shop.domain.User;

/**
 * @author GaoFei
 * 
 */
public interface UserDao {

	public int addUser(User user);
	public int deleteUser(int userID);
	public void ChangeUser(int userID,User user);
	public User findUser(int userID);
	public List<User> findAllUser();
	public boolean isValidate(int userID,String password);
	public int getUserCount();
	public List<User> getUsers(int newPage,int pageNumber);
	public List<User> getUsers(String type,String searchName);
	public int getSearchCount(String type,String searchName);
	public List<User> getSearchUsers(String type,String searchName,int newPage,int pageNumber);
	public void changePassword(int adminID,String newPassword);
}

/**
 * 
 */
package cn.edu.fjnu.shop.dao;

import java.util.List;

import cn.edu.fjnu.shop.domain.Access;

/**
 * @author GaoFei
 * 
 */
public interface AccessDao {

	
	public int deleteAccess(int accessID);
	public Access findAccess(int accessID);
	public int getAccessCount();
	public List<Access> getAccesss(int newPage,int pageNumber);
	public int getSearchCount(String type,String searchName);
	public List<Access> getSearchAccesss(String type,String searchName,int newPage,int pageNumber);
	
}

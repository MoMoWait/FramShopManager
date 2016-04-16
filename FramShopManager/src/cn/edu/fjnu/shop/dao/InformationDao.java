/**
 * 
 */
package cn.edu.fjnu.shop.dao;

import java.util.List;

import cn.edu.fjnu.shop.domain.Information;

/**
 * @author GaoFei
 * 
 */
public interface InformationDao {

	
	public int deleteInformation(int id);
	public Information findInformation(int id);
	public int getInformationCount();
	public List<Information> getInformations(int newPage,int pageNumber);
	public int getSearchCount(String type,String searchName);
	public List<Information> getSearchInformations(String type,String searchName,int newPage,int pageNumber);
	
}

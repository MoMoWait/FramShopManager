/**
 * 
 */
package cn.edu.fjnu.shop.dao;

import java.util.List;

import cn.edu.fjnu.shop.domain.FeedBack;

/**
 * @author GaoFei
 * 
 */
public interface FeedBackDao {

	
	public int deleteFeedBack(int id);
	public FeedBack findFeedBack(int id);
	public int getFeedBackCount();
	public List<FeedBack> getFeedBacks(int newPage,int pageNumber);
	public int getSearchCount(String type,String searchName);
	public List<FeedBack> getSearchFeedBacks(String type,String searchName,int newPage,int pageNumber);
	
}

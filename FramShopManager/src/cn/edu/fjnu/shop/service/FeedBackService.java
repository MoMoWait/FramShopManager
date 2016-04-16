package cn.edu.fjnu.shop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import cn.edu.fjnu.shop.dao.FeedBackDao;
import cn.edu.fjnu.shop.domain.FeedBack;
import cn.edu.fjnu.shop.utils.DBUtils;

public class FeedBackService implements FeedBackDao {
	
	@Override
	public int deleteFeedBack(int id) {
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="delete FROM feedback where id=?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				int rows= preparedStatement.executeUpdate();
				//List<FeedBack> feedbacks=new ArrayList<FeedBack>();
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
	public FeedBack findFeedBack(int feedbackID) {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT feedback.*,name FROM feedback,user  where id=? and feedback.userID=user.ID";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, feedbackID);
				ResultSet resultSet= preparedStatement.executeQuery();
				FeedBack feedback=new FeedBack();
				if(resultSet.first()){
					//FeedBack feedback=new FeedBack();
					feedback.setId(resultSet.getInt(1));
					feedback.setUserID(resultSet.getInt(2));
					feedback.setContent(resultSet.getString(3));
					feedback.setWay(resultSet.getString(4));
					feedback.setTime(resultSet.getString(5));
					feedback.setIp(resultSet.getString(6));
					feedback.setName(resultSet.getString(7));
					//feedback.setpr
				}
				
				
				//return sBuffer.toString();
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return feedback;
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
	public int getFeedBackCount() {
		// TODO Auto-generated method stub
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT count(*) FROM feedback,user where feedback.userID=user.ID";
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
	public List<FeedBack> getFeedBacks(int newPage, int pageNumber) {
		// TODO Auto-generated method stub
		if(newPage == 0)
			return null;
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT feedback.*,name FROM feedback,user WHERE feedback.userID=user.ID order by id desc limit ?,?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1,(newPage-1)*pageNumber);
				preparedStatement.setInt(2,pageNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<FeedBack> feedbacks=new ArrayList<FeedBack>();
				while(resultSet.next()){
					FeedBack feedback=new FeedBack();
					feedback.setId(resultSet.getInt(1));
					feedback.setUserID(resultSet.getInt(2));
					feedback.setContent(resultSet.getString(3));
					feedback.setWay(resultSet.getString(4));
					feedback.setTime(resultSet.getString(5));
					feedback.setIp(resultSet.getString(6));
					feedback.setName(resultSet.getString(7));
					feedbacks.add(feedback);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return feedbacks;
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
				//FeedBackService feedbackService=new FeedBackService();
				int count=0;
				String sql=null;
				PreparedStatement preparedStatement;
				if("姓名".equals(type)){
					sql="SELECT count(*) FROM feedback,user WHERE feedback.userID=user.ID and user.name like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/**反馈内容*/
					sql="SELECT count(*) FROM feedback WHERE content LIKE ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM feedback limit ?,?";
				
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
	public List<FeedBack> getSearchFeedBacks(String type, String searchName,
			int newPage, int pageNumber) {

		// TODO Auto-generated method stub
		if(newPage==0)
			return null;
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//FeedBackService feedbackService=new FeedBackService();
				String sql=null;
				PreparedStatement preparedStatement;
				if("姓名".equals(type)){
					sql="SELECT feedback.*,name FROM feedback,user WHERE feedback.userID=user.ID and user.name like ? "
							+ "order by id desc limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/**评价内容*/
					sql="SELECT feedback.*,name FROM feedback,user WHERE feedback.userID=user.ID and content like ? "
							+ "order by id desc limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				preparedStatement.setInt(2,(newPage-1)*pageNumber);
				preparedStatement.setInt(3,pageNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<FeedBack> feedbacks=new ArrayList<FeedBack>();
				while(resultSet.next()){
					FeedBack feedback=new FeedBack();
					feedback.setId(resultSet.getInt(1));
					feedback.setUserID(resultSet.getInt(2));
					feedback.setContent(resultSet.getString(3));
					feedback.setWay(resultSet.getString(4));
					feedback.setTime(resultSet.getString(5));
					feedback.setIp(resultSet.getString(6));
					feedback.setName(resultSet.getString(7));
					feedbacks.add(feedback);
					
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return feedbacks;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		
		return null;
	
	}
	
	
	
	
}

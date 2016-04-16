package cn.edu.fjnu.shop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import cn.edu.fjnu.shop.dao.InformationDao;
import cn.edu.fjnu.shop.domain.Information;
import cn.edu.fjnu.shop.utils.DBUtils;

public class InformationService implements InformationDao {
	
	@Override
	public int deleteInformation(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int addInformation(Information information) {
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="insert into information(photo,des,postTime) values (?,?,?)";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setString(1, information.getPhoto());
				preparedStatement.setString(2, information.getDes());
				preparedStatement.setString(3, information.getPostTime());
				int rows= preparedStatement.executeUpdate();
				//List<Information> informations=new ArrayList<Information>();
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
	public Information findInformation(int informationID) {
		// TODO Auto-generated method stub
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT * FROM information where id=?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, informationID);
				ResultSet resultSet= preparedStatement.executeQuery();
				Information information=new Information();
				if(resultSet.first()){
					//Information information=new Information();
					information.setId(resultSet.getInt(1));
					information.setPhoto(resultSet.getString(2));
					information.setDes(resultSet.getString(3));
					information.setPostTime(resultSet.getString(4));
					//information.setpr
				}
				
				
				//return sBuffer.toString();
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return information;
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
	public int getInformationCount() {
		// TODO Auto-generated method stub
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT count(*) FROM information";
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
	public List<Information> getInformations(int newPage, int pageNumber) {
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT * FROM information order by id desc limit ?,?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1,(newPage-1)*pageNumber);
				preparedStatement.setInt(2,pageNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Information> informations=new ArrayList<Information>();
				while(resultSet.next()){
					Information information=new Information();
					information.setId(resultSet.getInt(1));
					information.setPhoto(resultSet.getString(2));
					information.setDes(resultSet.getString(3));
					information.setPostTime(resultSet.getString(4));
					informations.add(information);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return informations;
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
				//InformationService informationService=new InformationService();
				int count=0;
				String sql=null;
				PreparedStatement preparedStatement;
				if("描述".equals(type)){
					sql="SELECT count(*) FROM information WHERE des like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/**发布时间*/
					sql="SELECT count(*) FROM information WHERE postTime LIKE ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM information limit ?,?";
				
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
	public List<Information> getSearchInformations(String type, String searchName,
			int newPage, int pageNumber) {

		// TODO Auto-generated method stub
		if(newPage==0)
			return null;
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//InformationService informationService=new InformationService();
				String sql=null;
				PreparedStatement preparedStatement;
				if("描述".equals(type)){
					sql="SELECT * FROM information WHERE des like ? "
							+ "order by id desc limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/**发布时间*/
					sql="SELECT * FROM information where postTime like ? "
							+ "order by id desc limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				preparedStatement.setInt(2,(newPage-1)*pageNumber);
				preparedStatement.setInt(3,pageNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Information> informations=new ArrayList<Information>();
				while(resultSet.next()){
					Information information=new Information();
					information.setId(resultSet.getInt(1));
					information.setPhoto(resultSet.getString(2));
					information.setDes(resultSet.getString(3));
					information.setPostTime(resultSet.getString(4));
					informations.add(information);
					
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return informations;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		
		return null;
	
	}
	
	
}

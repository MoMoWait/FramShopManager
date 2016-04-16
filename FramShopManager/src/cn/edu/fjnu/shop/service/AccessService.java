package cn.edu.fjnu.shop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.fjnu.shop.dao.AccessDao;
import cn.edu.fjnu.shop.domain.Access;
import cn.edu.fjnu.shop.utils.DBUtils;

public class AccessService implements AccessDao {
	
	@Override
	public int deleteAccess(int accessID) {
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="delete FROM access where accessID=?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, accessID);
				int rows= preparedStatement.executeUpdate();
				//List<Access> accesss=new ArrayList<Access>();
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
	public Access findAccess(int accessID) {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String orderID=null;
				String sql="SELECT accessID,access.orderID,access.userID,name,accessContent FROM "
						+ "access,user WHERE access.userID=user.ID and accessID=?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, accessID);
				ResultSet resultSet= preparedStatement.executeQuery();
				Access access=new Access();
				if(resultSet.first()){
					//Access access=new Access();
					access.setAccessID(accessID);
					orderID=resultSet.getString(2);
					access.setOrderID(orderID);
					access.setUserID(resultSet.getInt(3));
					access.setName(resultSet.getString(4));
					access.setAccessContent(resultSet.getString(5));
					
					//access.setpr
				}
				preparedStatement.close();
				resultSet.close();
				sql="SELECT productNumber,productName,productPrice,productBagPrice,unit "
						+ "from orderdetail,commonproduct where orderID=?"
						+ " AND orderdetail.productID=commonproduct.productID";
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, orderID);
				ResultSet rSet=preparedStatement.executeQuery();
				StringBuffer sBuffer=new StringBuffer();
				while(rSet.next()){
					sBuffer.append(rSet.getString(2)).append("(")
					.append(resultSet.getString(5).equals("斤")?resultSet.getString(3)+"元/斤":resultSet.getString(4)+"元/袋").append("*").append(rSet.getString(1))
					.append(");");
				}
				preparedStatement.close();
				rSet.close();
			//	closeConn();
				access.setProducts(sBuffer.toString());
				//return sBuffer.toString();
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return access;
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
	public int getAccessCount() {
		// TODO Auto-generated method stub
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT count(*) FROM access,user where access.userID=user.ID";
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
	public List<Access> getAccesss(int newPage, int pageNumber) {
		// TODO Auto-generated method stub
		if(newPage == 0)
			return null;
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT accessID,access.orderID,access.userID,name,accessContent FROM "
						+ "access,user WHERE access.userID=user.ID limit ?,?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1,(newPage-1)*pageNumber);
				preparedStatement.setInt(2,pageNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Access> accesss=new ArrayList<Access>();
				while(resultSet.next()){
					Access access=new Access();
					
					access.setAccessID(resultSet.getInt(1));
					String orderID=resultSet.getString(2);
					access.setOrderID(orderID);
					access.setUserID(resultSet.getInt(3));
					access.setName(resultSet.getString(4));
					access.setAccessContent(resultSet.getString(5));
					sql="SELECT productNumber,productName,productPrice,productBagPrice,unit "
							+ "from orderdetail,commonproduct where orderID=?"
							+ " AND orderdetail.productID=commonproduct.productID";
					
					PreparedStatement detailPreparedStatement=connection.prepareStatement(sql);
					detailPreparedStatement.setString(1, orderID);
					ResultSet rSet=detailPreparedStatement.executeQuery();
					StringBuffer sBuffer=new StringBuffer();
					while(rSet.next()){
						sBuffer.append(rSet.getString(2)).append("(")
						.append(rSet.getString(5).equals("斤")?rSet.getString(3)+"元/斤":rSet.getString(4)+"元/袋").append("*").append(rSet.getString(1))
						.append(");");
					}
					detailPreparedStatement.close();
					rSet.close();
					access.setProducts(sBuffer.toString());
					accesss.add(access);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return accesss;
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
				//AccessService accessService=new AccessService();
				int count=0;
				String sql=null;
				PreparedStatement preparedStatement;
				if("姓名".equals(type)){
					sql="SELECT count(*) FROM access,user WHERE access.userID=user.ID and user.name like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/**评价内容*/
					sql="SELECT count(*) FROM access WHERE accessContent LIKE ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM access limit ?,?";
				
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
	public List<Access> getSearchAccesss(String type, String searchName,
			int newPage, int pageNumber) {

		if(newPage==0)
			return null;
		// TODO Auto-generated method stub
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//AccessService accessService=new AccessService();
				String sql=null;
				PreparedStatement preparedStatement;
				if("姓名".equals(type)){
					sql="SELECT accessID,access.orderID,access.userID,name,accessContent "
							+ "FROM access,user WHERE access.userID=user.ID and user.name like ? "
							+ "ORDER BY accessID DESC limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/**评价内容*/
					sql="SELECT accessID,access.orderID,access.userID,name,accessContent "
							+ "FROM access,user WHERE access.userID=user.ID and accessContent like ? "
							+ "ORDER BY accessID DESC limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				preparedStatement.setInt(2,(newPage-1)*pageNumber);
				preparedStatement.setInt(3,pageNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Access> accesss=new ArrayList<Access>();
				while(resultSet.next()){
					Access access=new Access();
					access.setAccessID(resultSet.getInt(1));
					String orderID=resultSet.getString(2);
					access.setOrderID(orderID);
					access.setUserID(resultSet.getInt(3));
					access.setName(resultSet.getString(4));
					access.setAccessContent(resultSet.getString(5));
					sql="SELECT productNumber,productName,productPrice,productBagPrice "
							+ "from orderdetail,commonproduct where orderID=?"
							+ " AND orderdetail.productID=commonproduct.productID";
					
					PreparedStatement detailPreparedStatement=connection.prepareStatement(sql);
					detailPreparedStatement.setString(1, orderID);
					ResultSet rSet=detailPreparedStatement.executeQuery();
					StringBuffer sBuffer=new StringBuffer();
					while(rSet.next()){
						sBuffer.append(rSet.getString(2)).append("(")
						.append(rSet.getString(5).equals("")).append(rSet.getString(1))
						.append(");");
					}
					detailPreparedStatement.close();
					rSet.close();
					access.setProducts(sBuffer.toString());
					accesss.add(access);
					
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return accesss;
			} catch (Exception e) {
				// TODO: handle exception
				
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		
		return null;
	
	}
	
	
	
	
}

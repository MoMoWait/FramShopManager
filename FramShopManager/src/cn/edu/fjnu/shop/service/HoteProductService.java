package cn.edu.fjnu.shop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import cn.edu.fjnu.shop.dao.HoteProductDao;
import cn.edu.fjnu.shop.domain.HoteProduct;
import cn.edu.fjnu.shop.utils.DBUtils;

public class HoteProductService implements HoteProductDao {


	@Override
	public List<HoteProduct> getHoteProducts() {
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT hoteproduct.productID,productName,position,productType FROM hoteproduct,commonproduct WHERE hoteproduct.productID=commonproduct.productID";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<HoteProduct> hoteproducts=new ArrayList<HoteProduct>();
				while(resultSet.next()){
					HoteProduct hoteproduct=new HoteProduct();
					hoteproduct.setProductID(resultSet.getString(1));
					hoteproduct.setProductName(resultSet.getString(2));
					hoteproduct.setPosition(resultSet.getInt(3));
					hoteproduct.setProdcutType(resultSet.getString(4));
					hoteproducts.add(hoteproduct);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return hoteproducts;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		return null;
	}
		
	
	
	
}

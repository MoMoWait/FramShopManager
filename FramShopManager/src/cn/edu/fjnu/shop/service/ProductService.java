package cn.edu.fjnu.shop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import cn.edu.fjnu.shop.dao.ProductDao;
import cn.edu.fjnu.shop.domain.Product;
import cn.edu.fjnu.shop.utils.DBUtils;

public class ProductService implements ProductDao {

	@Override
	public int addProduct(Product Product) {
		// TODO Auto-generated method stub
		/** 验证用户名和密码是否正确 */
		Connection connection = DBUtils.getConn();
		if (connection != null) {
			try {

				String sql = "INSERT INTO commonproduct(productName,productType,productDes,productPrice,productPhoto,productBagPrice) VALUES"+
"(?,?,?,?,?,?)";
				
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);
				preparedStatement.setString(1,Product.getProductName());
				preparedStatement.setString(2,Product.getProductType());
				preparedStatement.setString(3,Product.getProductDes());
				preparedStatement.setFloat(4,Product.getProductPrice());
				preparedStatement.setString(5,Product.getProductPhoto());
				preparedStatement.setFloat(6, Product.getProductBagPrice());
				int rows = preparedStatement.executeUpdate();
				// List<Product> Products=new ArrayList<Product>();
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
	public int deleteProduct(int productID) {
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="delete FROM commonproduct where productID=?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, productID);
				int rows= preparedStatement.executeUpdate();
				//List<Product> Products=new ArrayList<Product>();
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
	public void changeProduct(int productID, Product product) {
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="update commonproduct set productName=?,productType=?,productDes=?,productPrice=?,productPhoto=?,productBagPrice=? WHERE productID=?";
				
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setString(1, product.getProductName());
				preparedStatement.setString(2, product.getProductType());
				preparedStatement.setString(3, product.getProductDes());
				preparedStatement.setFloat(4, product.getProductPrice());
				preparedStatement.setString(5, product.getProductPhoto());
				preparedStatement.setFloat(6, product.getProductBagPrice());
				preparedStatement.setInt(7, productID);
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
	public Product findProduct(int ProductID) {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT * FROM commonproduct where productID=?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, ProductID);
				ResultSet resultSet= preparedStatement.executeQuery();
				Product product=new Product();
				if(resultSet.first()){
					product.setProductID(resultSet.getInt(1));
					product.setProductName(resultSet.getString(2));
					product.setProductType(resultSet.getString(3));
					product.setProductDes(resultSet.getString(4));
					product.setProductPrice(resultSet.getFloat(5));
					product.setProductPhoto(resultSet.getString(6));
					product.setProductBagPrice(resultSet.getFloat(7));
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return product;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		return null;
	
	}

	@Override
	public List<Product> findAllProduct() {
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT * FROM commonproduct";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Product> Products=new ArrayList<Product>();
				while(resultSet.next()){
					Product product=new Product();
					product.setProductID(resultSet.getInt(1));
					product.setProductName(resultSet.getString(2));
					product.setProductType(resultSet.getString(3));
					product.setProductDes(resultSet.getString(4));
					product.setProductPrice(resultSet.getFloat(5));
					product.setProductPhoto(resultSet.getString(6));
					Products.add(product);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return Products;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		return null;
	}

	

	/**
	 * 获取总用户数
	 */
	@Override
	public int getProductCount() {
		// TODO Auto-generated method stub
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT count(*) FROM commonproduct";
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
	public List<Product> getProducts(int newPage, int pageNumber) {
		// TODO Auto-generated method stub
		/**验证用户名和密码是否正确*/
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				
				String sql="SELECT * FROM commonproduct limit ?,?";
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1,(newPage-1)*pageNumber);
				preparedStatement.setInt(2,pageNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Product> Products=new ArrayList<Product>();
				while(resultSet.next()){
				    Product product=new Product();
					product.setProductID(resultSet.getInt(1));
					product.setProductName(resultSet.getString(2));
					product.setProductType(resultSet.getString(3));
					product.setProductDes(resultSet.getString(4));
					product.setProductPrice(resultSet.getFloat(5));
					product.setProductPhoto(resultSet.getString(6));
					product.setProductBagPrice(resultSet.getFloat(7));
					Products.add(product);
					//Products.add(Product);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return Products;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		return null;
	}
	
	
	@Override
	public List<Product> getProducts(String type, String searchName) {
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//ProductService ProductService=new ProductService();
				String sql=null;
				PreparedStatement preparedStatement;
				if("编号".equals(type)){
					sql="select * from commonproduct where productID=?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, searchName);
				}else if("名称".equals(type)){
					sql="select * from commonproduct where productName like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else if("类别".equals(type)){
					sql="select * from commonproduct where productType like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/** type=phoneNumber*/
					sql="select * from commonproduct where productDes like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM Product limit ?,?";
				
				//preparedStatement.setString(2, searchName);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Product> Products=new ArrayList<Product>();
				while(resultSet.next()){
					 Product product=new Product();
					 product.setProductID(resultSet.getInt(1));
					 product.setProductName(resultSet.getString(2));
					 product.setProductType(resultSet.getString(3));
					 product.setProductDes(resultSet.getString(4));
					 product.setProductPrice(resultSet.getFloat(5));
					 product.setProductPhoto(resultSet.getString(6));
					 Products.add(product);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return Products;
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
				//ProductService ProductService=new ProductService();
				int count=0;
				String sql=null;
				PreparedStatement preparedStatement;
				if("编号".equals(type)){
					sql="select count(*) from commonproduct where productID=?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, searchName);
				}else if("名称".equals(type)){
					sql="select count(*) from commonproduct where productName like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else if("类别".equals(type)){
					sql="select count(*) from commonproduct where productType like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/** type=phoneNumber*/
					sql="select count(*) from commonproduct where productDes like ?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM Product limit ?,?";
				
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
	public List<Product> getSearchProducts(String type, String searchName,
			int newPage, int pageNumber) {

		// TODO Auto-generated method stub
		if(newPage==0)
			return null;
		Connection connection=DBUtils.getConn();
		if(connection!=null){
			try {
				//ProductService ProductService=new ProductService();
				String sql=null;
				PreparedStatement preparedStatement;
				if("编号".equals(type)){
					sql="select * from commonproduct where productID=? limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, searchName);
				}else if("名称".equals(type)){
					sql="select * from commonproduct where productName like ? limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else if("类别".equals(type)){
					sql="select * from commonproduct where productType like ? limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}else{
					/** type=phoneNumber*/
					sql="select * from commonproduct where productDes like ? limit ?,?";
					preparedStatement= connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+searchName+"%");
				}
				//String sql="SELECT * FROM Product limit ?,?";
				
				preparedStatement.setInt(2,(newPage-1)*pageNumber);
				preparedStatement.setInt(3,pageNumber);
				//preparedStatement.setString(2, searchName);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<Product> Products=new ArrayList<Product>();
				while(resultSet.next()){
					 Product product=new Product();
					 product.setProductID(resultSet.getInt(1));
					 product.setProductName(resultSet.getString(2));
					 product.setProductType(resultSet.getString(3));
					 product.setProductDes(resultSet.getString(4));
					 product.setProductPrice(resultSet.getFloat(5));
					 product.setProductPhoto(resultSet.getString(6));
					 product.setProductBagPrice(resultSet.getFloat(7));
					 Products.add(product);
				}
				preparedStatement.close();
				resultSet.close();
				DBUtils.closeConn();
				return Products;
			} catch (Exception e) {
				// TODO: handle exception
				DBUtils.closeConn();
				e.printStackTrace();
				
			}
			
		}
		
		return null;
	
	}
	
	
	
}

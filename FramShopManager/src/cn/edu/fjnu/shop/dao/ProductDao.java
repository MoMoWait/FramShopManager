/**
 * 
 */
package cn.edu.fjnu.shop.dao;

import java.util.List;

import cn.edu.fjnu.shop.domain.Product;
import cn.edu.fjnu.shop.domain.User;

/**
 * @author GaoFei
 * 
 */
public interface ProductDao {

	public int addProduct(Product product);
	public int deleteProduct(int productID);
	public void changeProduct(int productID,Product product);
	public Product findProduct(int productID);
	public List<Product> findAllProduct();
	public int getProductCount();
	public List<Product> getProducts(int newPage,int pageNumber);
	public List<Product> getProducts(String type,String searchName);
	public int getSearchCount(String type,String searchName);
	public List<Product> getSearchProducts(String type,String searchName,int newPage,int pageNumber);
	
}

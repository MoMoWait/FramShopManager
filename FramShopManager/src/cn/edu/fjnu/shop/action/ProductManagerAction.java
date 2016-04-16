/**
 * 
 */
package cn.edu.fjnu.shop.action;

import java.io.File;
import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.edu.fjnu.shop.config.Config;
import cn.edu.fjnu.shop.domain.Product;
import cn.edu.fjnu.shop.service.ProductService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GaoFei
 * 
 */
public class ProductManagerAction extends ActionSupport {

	/** 用户总数 */
	private int productNumber;
	/** 每页显示的用户数 */
	private int pageCount = 8;
	/** 用户列表 */
	private List<Product> products = new ArrayList<Product>();
	/** 总页数 */
	private int pages;
	/** 当前页数 */
	private int currentPage = 1;
	/** 跳转页数 */
	private int goPage = 1;
	/** 是否是跳转至指定的页数 */
	private String goSpecial = "false";
	/** 是否删除指定ID的用户 */
	private String deleteProduct = "false";
	/** 删除(修改)用户的ID */
	private int changeID;
	private Product product;
	/** 搜索类型 */
	private String searchType="编号";
	/** 搜索值 */
	private String searchValue;
	/** 是查看还是搜索 */
	private String type = "look";

	private File photoFile;
	private String photoFileFileName;
	private String photoFileContentType;
	
	private File savePhotoFile;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public String showProduct() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("userManager");
		/** 读取数据库中的内容 */
		ProductService productService = new ProductService();
		/** 判断是否是删除 */
		if ("true".equals(deleteProduct)) {
			productService.deleteProduct(changeID);
		}
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("type", type);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchValue", searchValue);
		
		productNumber = productService.getProductCount();
		
		if("search".equals(type)&&searchValue!=null&&!searchValue.equals(""))
			return "search";
		if (productNumber % pageCount > 0) {
			pages = productNumber / pageCount + 1;
		} else {
			pages = productNumber / pageCount;
		}
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {

			products = productService.getProducts(currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;

			products = productService.getProducts(goPage, pageCount);

		}

		// products=productService.findAllUser();
		// this.products=
		return "success";
	}

	public String changeProduct() throws Exception {
		
		ProductService productService = new ProductService();
		product = productService.findProduct(changeID);
		return "success";
	}

	public String commitChange() throws Exception {

		//System.out.println("commitChange操作类型:"+type);
		if(photoFile!=null){
			
			//System.err.println(photoFile.length()/1024);
			//生成文件名
			String realpath = ServletActionContext.getServletContext().getRealPath("/image/product"); 
			//System.out.println(realpath);
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat(product.getProductID()+"yyyyMMddhhmmssSSS");
			//String fileName=""
			Date date=new Date();
			String fileName=simpleDateFormat.format(date);
			fileName+=photoFileFileName.substring(photoFileFileName.length()-4);
			File saveFile=new File(realpath,fileName);
			 // 判断上传目录是否存在            
            if(!saveFile.getParentFile().exists())  
            	saveFile.getParentFile().mkdirs();
            //存储文件
            FileUtils.copyFile(photoFile,saveFile);
            
            product.setProductPhoto(Config.serverIP+"/FramShopManager/image/product/"+fileName);
		}
		ProductService productService = new ProductService();
		productService.changeProduct(product.getProductID(), product);
		return "success";
	}
	
	public String commitAdd() throws Exception {

		//System.out.println("commitChange操作类型:"+type);
		
		ProductService productService=new ProductService();
		if(photoFile!=null){
			//生成文件名
			String realpath = ServletActionContext.getServletContext().getRealPath("/image/product"); 
			//System.out.println(realpath);
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat(product.getProductID()+"yyyyMMddhhmmssSSS");
			//String fileName=""
			Date date=new Date();
			String fileName=simpleDateFormat.format(date);
			fileName+=photoFileFileName.substring(photoFileFileName.length()-4);
			File saveFile=new File(realpath,fileName);
			 // 判断上传目录是否存在            
            if(!saveFile.getParentFile().exists())  
            	saveFile.getParentFile().mkdirs();
            //存储文件
            FileUtils.copyFile(photoFile,saveFile);
            
            product.setProductPhoto(Config.serverIP+"/FramShopManager/image/product/"+fileName);
		}
		productService.addProduct(product);
		productNumber = productService.getProductCount();

		if (productNumber % pageCount > 0) {
			pages = productNumber / pageCount + 1;
		} else {
			pages = productNumber / pageCount;
		}
		currentPage=pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {

			products = productService.getProducts(currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;

			products = productService.getProducts(goPage, pageCount);

		}
		return "success";
	
	}
	
	public String searchProduct() throws Exception {

		ProductService productService = new ProductService();
		productNumber=productService.getSearchCount(searchType, searchValue);
		if (productNumber % pageCount > 0) {
			pages = productNumber / pageCount + 1;
		} else {
			pages = productNumber / pageCount;
		}
		
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > pages)
			currentPage = pages;
		/** 如果不是跳转 */
		if ("false".equals(goSpecial)) {
			
			products = productService.getSearchProducts(searchType, searchValue, currentPage, pageCount);

		} else {
			if (goPage < 1)
				goPage = 1;
			if (goPage > pages)
				goPage = pages;
			currentPage = goPage;
			
			products = productService.getSearchProducts(searchType,searchValue,goPage, pageCount);

		}
		return "success";
	}

	public String addProduct() throws Exception{
		
		return "success";
	}
	
	public int getproductNumber() {
		return productNumber;
	}

	public void setproductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setUsers(List<Product> products) {
		this.products = products;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getGoPage() {
		return goPage;
	}

	public void setGoPage(int goPage) {
		this.goPage = goPage;
	}

	public String getGoSpecial() {
		return goSpecial;
	}

	public void setGoSpecial(String goSpecial) {
		this.goSpecial = goSpecial;
	}

	public String getDeleteProduct() {
		return deleteProduct;
	}

	public void setDeleteProduct(String deleteProduct) {
		this.deleteProduct = deleteProduct;
	}

	public int getChangeID() {
		return changeID;
	}

	public void setChangeID(int changeID) {
		this.changeID = changeID;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product Product) {
		this.product = Product;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
		
	}

	public String getSearchValue() {
		
		return searchValue;
		//if(searchValue)
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public File getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(File photoFile) {
		this.photoFile = photoFile;
	}

	public File getSavePhotoFile() {
		return savePhotoFile;
	}

	public void setSavePhotoFile(File savePhotoFile) {
		this.savePhotoFile = savePhotoFile;
	}

	public String getPhotoFileFileName() {
		return photoFileFileName;
	}

	public void setPhotoFileFileName(String photoFileFileName) {
		this.photoFileFileName = photoFileFileName;
	}

	public String getPhotoFileContentType() {
		return photoFileContentType;
	}

	public void setPhotoFileContentType(String photoFileContentType) {
		this.photoFileContentType = photoFileContentType;
	}

	
	
}

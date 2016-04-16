package cn.edu.fjnu.shop.domain;

public class Product {
	
	private int productID;
	private String productName;
	private String productType;
	private String productDes;
	private float productPrice;
	private String productPhoto;
	private float productBagPrice;
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductDes() {
		return productDes;
	}
	public void setProductDes(String productDes) {
		this.productDes = productDes;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}
	
	public float getProductBagPrice() {
		return productBagPrice;
	}
	
	public void setProductBagPrice(float productBagPrice) {
		this.productBagPrice = productBagPrice;
	}
	
	
	
}

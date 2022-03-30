package livingthing.base;
import product.base.*;
public abstract class Animal implements Growable,Updateable,Buyable,Cloneable{
	private int age;
	private String url;
	private String name;
	private int price;
	private Product product;
	private boolean isGrow;
	private int ageToGrow;
	private Product productWhenKilled;
	private int productCdTime;
	private int productCdTimer;
	
	public int getAgeToGrow() {
		return ageToGrow;
	}

	public void setAgeToGrow(int ageToGrow) {
		this.ageToGrow = ageToGrow;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean getIsGrow() {
		return isGrow;
	}
	public void setIsGrow(boolean isGrow) {
		this.isGrow = isGrow;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Product getProductWhenKilled() {
		return productWhenKilled;
	}
	public void setProductWhenKilled(Product productWhenKilled) {
		this.productWhenKilled = productWhenKilled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProductCdTime() {
		return productCdTime;
	}
	public void setProductCdTime(int productCdTime) {
		this.productCdTime = productCdTime;
	}
	public int getProductCdTimer() {
		return productCdTimer;
	}
	public void setProductCdTimer(int productCdTimer) {
		this.productCdTimer = productCdTimer;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

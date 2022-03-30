package livingthing.base;

import product.base.*;
import product.Corn;

public abstract class Crop implements Buyable, Growable, Updateable, Cloneable {
	private int age;
	private int price;
	private String url;
	private String name;
	private boolean isGrow;
	private int ageToGrow;
	private Product product;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getIsGrow() {
		return isGrow;
	}

	public void setIsGrow(boolean isGrow) {
		this.isGrow = isGrow;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

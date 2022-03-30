package product.base;

public abstract class  Product {
	private String url;
	private int exp;
	
	private int income;
	private int total;
	private String productName;
	
	/*public Product() {
		this.total = 0;
	}
	public Product(Product product) {
		this.income=product.income;
		this.productName=product.productName;
		this.url=product.url;
	}*/
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
}

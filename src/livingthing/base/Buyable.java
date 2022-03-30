package livingthing.base;
import product.base.*;
public interface Buyable {
	public String getUrl();
	public String getName();
	public int getPrice();
	public Product getProduct();
	public int getAgeToGrow();
}

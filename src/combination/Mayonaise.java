package combination;
import combination.base.Combination;
import java.util.*;
import product.base.Product;
import product.Egg;
import product.Milk;
public class Mayonaise extends Combination{
	public Mayonaise() {
		setExp(200);
		setUrl("Mayonaise.png");
		this.setProductName("Mayonaise");
		this.setIngredient(new ArrayList<Product>(Arrays.asList(new Egg(),new Milk())));
		this.setIncome(150);
		this.setExp(6);
	}
}

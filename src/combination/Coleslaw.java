package combination;
import java.util.ArrayList;
import java.util.Arrays;

import combination.base.Combination;
import product.base.Product;
import product.Cabbage;
public class Coleslaw extends Combination{
	public Coleslaw() {
		setUrl("Coleslaw.png");
		this.setProductName("Coleslaw");
		this.setIngredient(new ArrayList<Product>(Arrays.asList(new Cabbage(),new Mayonaise())));
		this.setIncome(200);
		this.setExp(10);
	}
}

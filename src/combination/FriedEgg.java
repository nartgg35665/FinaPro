package combination;

import java.util.ArrayList;
import java.util.Arrays;

import combination.base.Combination;
import product.Egg;
import product.base.Product;

public class FriedEgg extends Combination{
	public FriedEgg() {
		this.setExp(100);
		this.setUrl("FriedEgg.png");
		this.setProductName("FriedEgg");
		this.setIngredient(new ArrayList<Product>(Arrays.asList(new Egg())));
		this.setIncome(30);
		this.setExp(3);
	}
}

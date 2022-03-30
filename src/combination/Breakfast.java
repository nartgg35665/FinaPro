package combination;
import java.util.ArrayList;
import java.util.Arrays;

import combination.base.Combination;
import product.Egg;
import product.CowMeat;
import product.base.Product;

public class Breakfast extends Combination{
	public Breakfast() {
		setUrl("Breakfast.png");
		this.setProductName("Breakfast");
		this.setIngredient(new ArrayList<Product>(Arrays.asList(new Egg(),new CowMeat())));
		this.setExp(10);
		this.setIncome(800);
	}
}

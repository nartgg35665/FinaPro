package combination;
import java.util.ArrayList;
import java.util.Arrays;

import combination.base.Combination;
import product.EggFes;
import product.MilkFes;
import product.base.Product;

public class LuckyLunch extends Combination{
	public LuckyLunch() {
		setExp(1000000);
		setUrl("LuckyLunch.png");
		this.setProductName("LuckyLunch");
		this.setIngredient(new ArrayList<Product>(Arrays.asList(new EggFes(),new MilkFes())));
		this.setIncome(1200);
		this.setExp(50);
	}
}	

package combination.base;

import product.base.Product;
import java.util.*;
public class Combination extends Product implements Cloneable{
	private ArrayList<Product> ingredient = new ArrayList<Product>();
	public ArrayList<Product> getIngredient() {
		return ingredient;
	}
	public void setIngredient(ArrayList<Product> ingredient) {
		this.ingredient = ingredient;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}

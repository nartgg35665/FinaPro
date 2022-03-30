package livingthing;
import livingthing.base.*;
import logic.GameController;
import product.base.*;
import product.Tomato;
public class TomatoTree extends Crop {
	public TomatoTree() {
		setName("Tomato");
		setUrl("TomatoSeed.png");
		setProduct(new Tomato());
		setPrice(500);
		setAgeToGrow((int)(50*GameController.bonusGrowTimeCrop));
	}
	public void grow() {
		setUrl("Tomato.png");
		setIsGrow(true);
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()>=getAgeToGrow()) {
			grow();
		}
	}
}

package livingthing;
import livingthing.base.*;
import logic.GameController;
import product.base.*;
import product.Blueberry;

public class BlueberryTree extends Crop {
	
	public BlueberryTree() {
		setName("Blueberry");
		setUrl("BlueberrySeed.png");
		setProduct(new Blueberry());
		setPrice(20);
		setAgeToGrow((int)(10*GameController.bonusGrowTimeCrop));
	}
	public void grow() {
		setUrl("Blueberry.png");
		setIsGrow(true);
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()>=getAgeToGrow()) {
			grow();
		}
	}
}

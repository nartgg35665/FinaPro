package livingthing;
import livingthing.base.*;
import logic.GameController;
import product.base.*;
import product.Pumpkin;

public class PumpkinTree extends Crop {
	
	public PumpkinTree() {
		setName("Pumpkin");
		setUrl("PumpkinSeed.png");
		setProduct(new Pumpkin());
		setPrice(200);
		setAgeToGrow((int)(45*GameController.bonusGrowTimeCrop));
	}
	public void grow() {
		setUrl("Pumpkin.png");
		setIsGrow(true);
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()>=getAgeToGrow()) {
			grow();
		}
	}
}

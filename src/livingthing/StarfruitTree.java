package livingthing;
import livingthing.base.*;
import logic.GameController;
import product.base.*;
import product.Starfruit;

public class StarfruitTree extends Crop{
	public StarfruitTree() {
		setName("Starfruit");
		setUrl("StarfruitSeed.png");
		setProduct(new Starfruit());
		setPrice(10000);
		setAgeToGrow((int)(60*GameController.bonusGrowTimeCrop));
	}
	public void grow() {
		setUrl("Starfruit.png");
		setIsGrow(true);
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()>=getAgeToGrow()) {
			grow();
		}
	}
}

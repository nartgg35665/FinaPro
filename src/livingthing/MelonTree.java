package livingthing;
import livingthing.base.*;
import logic.GameController;
import product.base.*;
import product.Melon;
public class MelonTree extends Crop {
	public MelonTree() {
		setName("Melon");
		setUrl("MelonSeed.png");
		setProduct(new Melon());
		setPrice(100);
		setAgeToGrow((int)(20*GameController.bonusGrowTimeCrop));
	}
	public void grow() {
		setUrl("Melon.png");
		setIsGrow(true);
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()>=getAgeToGrow()) {
			grow();
		}
	}
}

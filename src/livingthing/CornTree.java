package livingthing;
import livingthing.base.*;
import logic.GameController;
import product.base.*;
import product.Corn;
public class CornTree extends Crop {
	private static final int ageToGrow = 100;
	public CornTree() {
		setName("Corn");
		setUrl("CornSeed.png");
		setProduct(new Corn());
		setPrice(50);
		setAgeToGrow((int)(15*GameController.bonusGrowTimeCrop));
	}
	public void grow() {
		setUrl("Corn.png");
		setIsGrow(true);
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()>=getAgeToGrow()) {
			grow();
		}
	}
}

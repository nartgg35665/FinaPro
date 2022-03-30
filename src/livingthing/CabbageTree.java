package livingthing;
import livingthing.base.*;
import logic.GameController;
import product.base.*;
import product.Cabbage;

public class CabbageTree extends Crop {
	
	public CabbageTree() {
		setName("Cabbage");
		setUrl("CabbageSeed.png");
		setProduct(new Cabbage());
		setPrice(30);
		setAgeToGrow((int)(10*GameController.bonusGrowTimeCrop));
	}
	public void grow() {
		setUrl("Cabbage.png");
		setIsGrow(true);
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()>=getAgeToGrow()) {
			grow();
		}
	}
}

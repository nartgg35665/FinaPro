package livingthing;
import livingthing.base.*;
import logic.GameController;
import product.ChickenMeat;
import product.EggFes;
public class ChickenFes extends Animal{
	
	public ChickenFes() {
		setUrl("ChickenFes.png");
		setPrice(5000);
		setProduct(new EggFes());
		setProductCdTime(30);
		setProductCdTimer(30);
		setName("Festival chicken");
		setIsGrow(true);
		setAgeToGrow(0);
		setProductWhenKilled(new ChickenMeat());
	}
	public void grow() {
		setIsGrow(true);
		setUrl("ChickenFes.png");
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()==getAgeToGrow()*GameController.bonusGrowTimeAnimal) {
			grow();
		}
		setProductCdTimer(getProductCdTimer()-1);
	}
}

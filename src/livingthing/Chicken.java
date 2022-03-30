package livingthing;
import livingthing.base.*;
import logic.GameController;
import product.ChickenMeat;
import product.Egg;
public class Chicken extends Animal{
	
	public Chicken() {
		setName("Baby chicken");
		setUrl("BabyChicken.png");
		setPrice(300);
		setProduct(new Egg());
		setProductWhenKilled(new ChickenMeat());
		setAgeToGrow(30);
	}
	public void grow() {
		setName("Chicken");
		setIsGrow(true);
		setUrl("Chicken.png");
		setProductCdTime(30);
		setProductCdTimer(30);
		getProductWhenKilled().setTotal(1);
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()==getAgeToGrow()*GameController.bonusGrowTimeAnimal) {
			grow();
		}
		setProductCdTimer(getProductCdTimer()-1);
	}
}

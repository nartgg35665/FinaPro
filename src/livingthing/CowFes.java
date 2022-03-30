package livingthing;

import product.CowMeat;
import product.MilkFes;
import livingthing.base.*;
import logic.GameController;
public class CowFes extends Animal{
	public CowFes() {
		setUrl("CowFes.png");
		setPrice(20000);
		setProduct(new MilkFes());
		setName("Festival cow");
		setAgeToGrow(0);
		setIsGrow(true);
		setProductCdTime(60);
		setProductCdTimer(60);
		setProductWhenKilled(new CowMeat());
	}
	public void grow() {
		setIsGrow(true);
		setUrl("CowFes.png");
	}
	public void update() {
		setAge(getAge()+1);
		if(getAge()==getAgeToGrow()*GameController.bonusGrowTimeAnimal) {
			grow();
		}
		setProductCdTimer(getProductCdTimer()-1);
	}
}

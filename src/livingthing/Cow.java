package livingthing;
import product.CowMeat;
import livingthing.base.*;
import logic.GameController;
import product.Milk;
public class Cow extends Animal{
	public Cow() {
		setName("Baby cow");
		setUrl("BabyCow.png");
		setPrice(1000);
		setAgeToGrow(120);
		setProduct(new Milk());
		setProductWhenKilled(new CowMeat());
	}
	public void grow() {
		setIsGrow(true);
		setName("Cow");
		setUrl("Cow.png");
		setProductCdTime(60);
		setProductCdTimer(60);
		getProductWhenKilled().setTotal(1);
	}
	public void update() {
		if(getIsGrow()) {
			setProductCdTimer(getProductCdTimer()-1);
		}
		setAge(getAge()+1);
		if(getAge()==getAgeToGrow()*GameController.bonusGrowTimeAnimal) {
			grow();
		}
		
	}
}

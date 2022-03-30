package livingthing;

import livingthing.base.*;
import logic.GameController;

public class Grazier extends Human {
	public Grazier() {
		this.setUrl("Grazier.png");
		this.setName("Grazier");
		this.setLikeThing("Egg");
	}
	public void levelUp(int order) {
		// TODO Auto-generated method stub
		this.setLevel(getLevel()+1);
		GameController.bonusBuyAnimal-=0.05;
		if(this.getLevel()>=10) {
			GameController.npcPane.getPlayerNPCStatus().showSkillLevelTen(order);
		}
		else if(this.getLevel()>=5) {
			GameController.npcPane.getPlayerNPCStatus().showSkillLevelFive(order);
		}
		GameController.main.generateAnimalStorePane();
	}
	public void abilityLeft() {
		System.out.println("e");
		GameController.bonusHaveChicken+=2;
		GameController.bonusHaveCow+=2;
		GameController.bonusTimeCollectItemAnimal-=0.15;
		GameController.byrePane.setLimit();
		GameController.henHousePane.setLimit();
	}
	public String abilityLeftText() {
		return "Maximum chicken and cow increase by +2\nDecrease time to collect item from animal -15%\n";
	}
	public void abilityRight() {
		GameController.bonusHaveCow+=5;
		GameController.bonusHaveChicken+=5;
		GameController.bonusGrowTimeAnimal-=0.15;
		GameController.main.generateAnimalStorePane();
		GameController.byrePane.setLimit();
		GameController.henHousePane.setLimit();
	}
	public String abilityRightText() {
		return "Maximum Chicken and cow increase by +5\nDecrease grow time of animal -15%\n";
	}
	public void specialAbility() {
		GameController.bonusSell+=0.25;
		GameController.bonusHaveChicken*=2;
		GameController.bonusHaveCow*=2;
		GameController.byrePane.setLimit();
		GameController.henHousePane.setLimit();
	}
	public String specialAbilityText() {
		return "Increase bonus of maximum chicken and cow multiply by x2\nIncrease sell price of your product +25%\n";
	}
	@Override
	public String levelUpAbilityText() {
		return "Decrease buy price of grazier's shop -5%\n";
	}
}
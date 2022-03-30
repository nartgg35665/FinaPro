package livingthing;

import livingthing.base.*;
import logic.GameController;

public class Greengrocer extends Human{
	
	public Greengrocer() {
		this.setUrl("Greengrocer.png");
		this.setName("Greengrocer");
		this.setLikeThing("Tomato");
	}
	public void levelUp(int order) {
		// TODO Auto-generated method stub
		this.setLevel(this.getLevel()+1);
		if(this.getLevel()>=10) {
			GameController.npcPane.getPlayerNPCStatus().showSkillLevelTen(order);
		}
		else if(this.getLevel()>=5) {
			GameController.npcPane.getPlayerNPCStatus().showSkillLevelFive(order);
		}
		GameController.bonusBuyCrop-=0.05;
		GameController.main.generateCropStorePane();
	}
	public void abilityLeft() {
		GameController.bonusNumberOfLand+=1;
		GameController.gameMap.addSoilPane();
	}
	public String abilityLeftText() {
		return "Increase your number of land +1\n";
	}
	public void abilityRight() {
		GameController.bonusGrowTimeCrop-=0.25;
		GameController.main.generateCropStorePane();
	}
	public String abilityRightText() {
		return "Your crop grow faster 25%\n";
	}
	public void specialAbility() {
		GameController.bonusSell+=0.25;
		GameController.bonusNumberOfLand+=2;
		GameController.gameMap.addSoilPane();
	}
	public String specialAbilityText() {
		return "\nIncrease your number of land +2\nIncrease your sell price +25%\n";
	}
	@Override
	public String levelUpAbilityText() {
		// TODO Auto-generated method stub
		return "Decrease buy price of greengrocer's shop -5%\n";
	}
}

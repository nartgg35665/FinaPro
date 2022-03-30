package livingthing;

import livingthing.base.Human;
import livingthing.base.LevelUpable;
import logic.GameController;

public class Wizard extends Human {

	public Wizard() {
		this.setUrl("Wizard.png");
		this.setName("Wizard");
		this.setLikeThing("Festival milk");
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
		GameController.bonusQuestEXPReward+=0.05;
	}
	public void abilityLeft() {
		if(!GameController.canBuyFestivalAnimal) {
			GameController.canBuyFestivalAnimal=true;
			GameController.animalStorePane.addSpecialPane();
		}
		else{
			GameController.canBuyStarfruit=true;
			GameController.cropStorePane.addStarfruit();
		}
	}
	public String abilityLeftText() {
		return "You can buy special thing from shop\n";
	}
	public void abilityRight() {
		GameController.bonusQuestEXPReward+=0.1;
	}
	public String abilityRightText() {
		return "Increase bonus EXP quest reward +10%\n";
	}
	public void specialAbility() {
		GameController.bonusCollectedItemCrop*=2;
		GameController.bonusCollectedItemAnimal*=2;
	}
	public String specialAbilityText() {
		return "Increase product when collect x2\n";
	}
	@Override
	public String levelUpAbilityText() {
		// TODO Auto-generated method stub
		return "Increase bonus EXP quest reward +5%\n";
	}
}

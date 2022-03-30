package livingthing;


import livingthing.base.*;
import logic.GameController;

public class Banker extends Human{
	public Banker() {
		this.setUrl("Banker.png");
		this.setName("Banker");
		this.setLikeThing("Blueberry");
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
		GameController.bonusQuestMoneyReward=0.05;
	}
	public String levelUpAbilityText() {
		return "Increase your money reward from quest +5%\n";
	}
	public void abilityLeft() {
		GameController.bonusQuestMoneyReward+=0.1;
	}
	public String abilityLeftText() {
		return "Increase your money reward from quest +10%\n";
	}
	public void abilityRight() {
		GameController.allowance-=0.25;
	}
	public String abilityRightText() {
		return "Decrease tax -25%\n";
	}
	public void specialAbility() {
		GameController.taxDay=14;
	}
	public String specialAbilityText() {
		return "Change to pay tax every 14 days\n";
	}
}

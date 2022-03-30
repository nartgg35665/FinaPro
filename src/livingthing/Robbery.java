package livingthing;

import livingthing.base.*;
import logic.GameController;

public class Robbery extends Human{
	public Robbery() {
		this.setUrl("Robbery.png");
		this.setName("Robbery");
		this.setLikeThing("Festival egg");
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
		GameController.stealChance+=2;
	}
	public void abilityLeft() {
		GameController.stealChance+=5;
	}
	public String abilityLeftText() {
		return "Increase stealChance + 5%\n";
	}
	public void abilityRight() {
		GameController.bonusMoneyRange+=1000;
	}
	public String abilityRightText() {
		return "Can get higher money\n";
	}
	public void specialAbility() {
		GameController.stealChance*=2;
		GameController.bonusMoneyRange*=2;
	}
	public String specialAbilityText() {
		return "Increase all bonus from robbery x2\n";
	}

	@Override
	public String levelUpAbilityText() {
		// TODO Auto-generated method stub
		return "Increase steal chance +2% and money which you get will random\n";
	}
	
}

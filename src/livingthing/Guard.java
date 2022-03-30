package livingthing;

import livingthing.base.*;
import logic.GameController;

public class Guard extends Human{
	public Guard() {
		this.setUrl("Guard.png");
		this.setName("Guard");
		this.setLikeThing("Milk");
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
		GameController.protectChance+=5;
	}
	public void abilityLeft() {
		GameController.protectYourMoney-=0.2;
	}
	public String abilityLeftText() {
		return "Robber will steal less money -20%\n";
	}
	public void abilityRight() {
		GameController.protectChance+=10;
	}
	public String abilityRightText() {
		return "Increase protect chance +10%\n";
	}
	public void specialAbility() {
		GameController.isSpecialGuard=true;
	}
	public String specialAbilityText() {
		return "Increase protect chance to 100%\n";
	}
	@Override
	public String levelUpAbilityText() {
		// TODO Auto-generated method stub
		return "Increase protect chance +5%\n";
	}
	
}

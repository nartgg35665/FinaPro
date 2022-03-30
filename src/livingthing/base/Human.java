package livingthing.base;

import logic.GameController;
import product.base.Product;

public abstract class Human implements LevelUpable{
	private int level;
	private int currentExp;
	private final int expToLevelUp[] = {50,100,200,400,800,1600,3200,4800,8000,15000};
	private String url;
	private String name;
	private String likeThing;
	/*public Human() {
		this.setCurrentExp(0,0);
		this.setLevel(0);
	}*/
	public abstract String levelUpAbilityText();
	public abstract void abilityLeft();
	public abstract String abilityLeftText();
	public abstract void abilityRight();
	public abstract String abilityRightText();
	public abstract void specialAbility();
	public abstract String specialAbilityText();
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurrentExp() {
		return currentExp;
	}
	public void setCurrentExp(int currentExp,int order) {
		this.currentExp = currentExp;
		while(this.currentExp>=expToLevelUp[level]) {
			this.currentExp-=expToLevelUp[level];
			this.levelUp(order);
			if(this.getLevel()>=10) {
				return;
			}
		}
	}
	public String getText() {
		if(level!=10) {
			return this.name + " Level : " + level+" EXP:" +currentExp + "/" + expToLevelUp[level];
		}
		else { 
			return this.name + " Level : " + level + " max";
		}
	}
	public String getLikeThing() {
		return likeThing;
	}
	public void setLikeThing(String likeThing) {
		this.likeThing = likeThing;
	}

}

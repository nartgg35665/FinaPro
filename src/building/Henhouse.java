package building;

import building.base.Building;
import logic.GameController;

public class Henhouse extends Building{
	private int numberOfEgg;
	private int numberOfFestivalEgg;
	private int totalChicken;
	private final int limitOfAllChicken = 10;
	public Henhouse() {
		setUrl("Henhouse.png");
		totalChicken = 0;
		numberOfEgg=0;
		numberOfFestivalEgg=0;
	}
	public int getNumberOfEgg() {
		return numberOfEgg;
	}
	public void setNumberOfEgg(int numberOfEgg) {
		this.numberOfEgg = numberOfEgg;
	}
	public int getNumberOfFestivalEgg() {
		return numberOfFestivalEgg;
	}
	public void setNumberOfFestivalEgg(int numberOfFestivalEgg) {
		this.numberOfFestivalEgg = numberOfFestivalEgg;
	}
	public int getLimitOfAllChicken() {
		return limitOfAllChicken;
	}
	public void setTotalChicken(int totalChicken) {
		this.totalChicken = totalChicken;
	}
	public void addTotalChicken(int addChicken) {
		totalChicken+=addChicken;
	}
	public int getTotalChicken() {
		return totalChicken; 
	}
}

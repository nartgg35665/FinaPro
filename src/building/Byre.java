package building;

import java.util.ArrayList;

import building.base.Building;
import gui.ByrePane;
import javafx.collections.ObservableList;
import livingthing.base.Animal;
import logic.GameController;
public class Byre extends Building {
	private int numberOfFestivalMilk;
	private int numberOfMilk;
	private final int limitOfAllCow = 10;
	private int totalCow;
	public Byre() {
		setUrl("Byre.png");
		totalCow = 0;
		numberOfFestivalMilk=0;
		numberOfMilk=0;
	}
	public int getNumberOfFestivalMilk() {
		return numberOfFestivalMilk;
	}
	public void setNumberOfFestivalMilk(int numberOfFestivalMilk) {
		this.numberOfFestivalMilk = numberOfFestivalMilk;
	}
	public int getNumberOfMilk() {
		return numberOfMilk;
	}
	public void setNumberOfMilk(int numberOfMilk) {
		this.numberOfMilk = numberOfMilk;
	}
	public int getLimitOfAllCow() {
		return limitOfAllCow;
	}
	public void setTotalCow(int totalCow) {
		this.totalCow = totalCow;
	}
	public void addTotalCow(int addCow) {
		this.totalCow+=addCow;
	}
	public int getTotalCow() {
		return totalCow;
	}
}

package logic;

import java.util.ArrayList;

import exception.RandomNPCForQuestException;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import livingthing.base.Human;
import product.Blueberry;
import product.base.Product;

public class Quest {
	private ArrayList<Product> productList;
	private ArrayList<Integer> productTotal;
	private ArrayList<Text> questInfo;
	private Human ownerOfQuest;
	private int expQuest;
	private int money;

	public Quest() {
		questInfo = new ArrayList<Text>();
		productList = new ArrayList<Product>();
		productTotal = new ArrayList<Integer>();
		generateQuestInfo();
	}

	private void generateQuestInfo() {
		int initialY = 20;
		ownerOfQuest = GameController.randomNPCForQuest();
		expQuest = 20 * GameController.levelQuest;
		money = 1000 * GameController.levelQuest;
		for (int i = 0; i <  Math.min(5, GameController.levelQuest); i++) {
			productList.add(GameController.randomProductForQuest() );
			productTotal.add((int) (6 * GameController.levelQuest + (Math.random() * 6) ));
			Text itemRequest = new Text(productList.get(i).getProductName() + " " + productTotal.get(i));
			itemRequest.setFont(new Font(20));
			itemRequest.setLayoutX(10);
			itemRequest.setLayoutY(initialY);
			initialY += 40;
			this.setQuestInfo(itemRequest);
		}
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public ArrayList<Integer> getProductTotal() {
		return productTotal;
	}

	public String getMoney() {
		return "" + money;
	}

	public int getIntegerMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<Text> getQuestInfo() {
		return questInfo;
	}

	public void setQuestInfo(Text text) {
		this.questInfo.add(text);
	}

	public Human getOwnerOfQuest() {
		return ownerOfQuest;
	}

	public String getExpQuest() {
		return "" + expQuest;
	}

	public int getIntergerExpQuest() {
		return expQuest;
	}

	public void setExpQuest(int expQuest) {
		this.expQuest = expQuest;
	}

}

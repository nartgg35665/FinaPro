package gui;

import java.util.ArrayList;

import exception.RandomNPCForQuestException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import livingthing.Grazier;
import livingthing.Greengrocer;
import livingthing.Guard;
import livingthing.Robbery;
import livingthing.Wizard;
import livingthing.base.Human;
import logic.GameController;
import logic.Quest;

public class EachQuestPane extends AnchorPane {
	private Quest quest;
	private int order;
	private Button submitButton;
	private ArrayList<InventoryCell> itemQuestCells;

	public EachQuestPane() {
		quest = new Quest();
		this.setPrefWidth(260);
		this.setPrefHeight(290);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		generateQuestInfo();
		generateQuestInfoBox();
	}

	private void generateQuestInfo() {
		Image img = new Image(quest.getOwnerOfQuest().getName() + ".png");
		ImageView view = new ImageView(img);
		handleSubmitButton();
		view.setFitWidth(40);
		view.setFitHeight(40);
		view.setLayoutX(10);
		view.setLayoutY(10);
		Text ExpQuest = new Text();
		ExpQuest.setFont(new Font(15));
		ExpQuest.setText("EXP : " + quest.getExpQuest() + " MONEY : " + quest.getMoney());
		ExpQuest.setLayoutX(60);
		ExpQuest.setLayoutY(50);
		Text NPCname = new Text();
		NPCname.setFont(new Font(30));
		NPCname.setText(quest.getOwnerOfQuest().getName());
		NPCname.setLayoutX(60);
		NPCname.setLayoutY(30);
		this.getChildren().addAll(view, NPCname, ExpQuest, submitButton);
	}

	private void handleSubmitButton() {
		submitButton = new Button("SUBMIT");
		submitButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		Image img2 = new Image("MenuButton.png");
		ImageView view2 = new ImageView(img2);
		submitButton.setBackground(new Background(
				new BackgroundImage(img2, null, null, null, new BackgroundSize(80, 30, false, false, false, false))));
		submitButton.setPrefHeight(30);
		submitButton.setPrefWidth(80);
		submitButton.relocate(90, 255);
		submitButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Image img2 = new Image("MenuButtonPressed.png");
				ImageView view2 = new ImageView(img2);
				submitButton.setBackground(new Background(new BackgroundImage(img2, null, null, null,
						new BackgroundSize(80, 30, false, false, false, false))));
			}

		});
		submitButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Image img2 = new Image("MenuButton.png");
				ImageView view2 = new ImageView(img2);
				submitButton.setBackground(new Background(new BackgroundImage(img2, null, null, null,
						new BackgroundSize(80, 30, false, false, false, false))));
			}

		});
		submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				checkInventory();
			}

		});
	}

	private void checkInventory() {
		InventoryPane inventoryPane = new InventoryPane();
		inventoryPane = GameController.inventoryPane;
		itemQuestCells = new ArrayList<InventoryCell>();
		int total = quest.getProductList().size(), iHave = 0;
		for (int i = 0; i < inventoryPane.getInventoryCells().size(); i++) {
			if (inventoryPane.getInventoryCells().get(i).getProduct() == null) {
				continue;
			} else {
				// System.out.println("ez"+total+inventoryPane.getInventoryCells().get(i).getProduct().getProductName());
				for (int j = 0; j < quest.getProductList().size(); j++) {
					if (quest.getProductList().get(j).getProductName()
							.equals(inventoryPane.getInventoryCells().get(i).getProduct().getProductName())) {

						if (quest.getProductTotal().get(j) <= inventoryPane.getInventoryCells().get(i).getTotal()) {
							iHave += 1;
							itemQuestCells.add(inventoryPane.getInventoryCells().get(i));
						}
					}
				}

			}
		}
		if (iHave == total) {
			sendItem();
		}
	}

	private void sendItem() {
		GameController.questDone += 1;
		for (int i = 0; i < itemQuestCells.size(); i++) {
			for (int j = 0; j < quest.getProductList().size(); j++) {
				if (itemQuestCells.get(i).getProduct().getProductName()
						.equals(quest.getProductList().get(j).getProductName())) {
					itemQuestCells.get(i).setTotal(itemQuestCells.get(i).getTotal() - quest.getProductTotal().get(j));
					itemQuestCells.get(i).setTotalText("" + itemQuestCells.get(i).getTotal());
					if (itemQuestCells.get(i).getTotal() == 0) {
						itemQuestCells.get(i).productIsEmpty();
					}
				}
			}
		}
		GameController.money += (int)(quest.getIntegerMoney() * GameController.bonusQuestMoneyReward);
		GameController.moneyPane.setMoneyText();
		int npcOrder = 0;
		switch (quest.getOwnerOfQuest().getName()) {
		case "Banker":
			npcOrder = 0;
			break;
		case "Grazier":
			npcOrder = 1;
			break;
		case "Guard":
			npcOrder = 2;
			break;
		case "Robbery":
			npcOrder = 3;
			break;
		case "Greengrocer":
			npcOrder = 4;
			break;
		case "Wizard":
			npcOrder = 5;
			break;
		}
		Human npc = GameController.npcPane.getPlayerNPCStatus().getNpcList().get(npcOrder);
		npc.setCurrentExp(npc.getCurrentExp() + (int)(quest.getIntergerExpQuest()*GameController.bonusQuestEXPReward), npcOrder);
		GameController.npcPane.getPlayerNPCStatus().setNpcStatus(npc, npcOrder);
		this.getChildren().clear();
		this.setBorder(
				new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, null)));
		this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		GameController.questPane.getMarkQuest()[this.order] = 0;
		if (isEmptyQuest()) {
			GameController.questDone = 0;
			GameController.generateNPCForQuest();
			for (int i = 0; i < 6; i++) {
				GameController.questPane.getCountQuest()[i] = 0;

			}
			GameController.levelQuest += 1;
			GameController.levelQuest = Math.min(5, GameController.levelQuest);
		}
		GameController.questPane.generateEachPane();
	}

	public Boolean isEmptyQuest() {
		if (GameController.questDone == 36)
			return true;
		else
			return false;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(Button submitButton) {
		this.submitButton = submitButton;
	}

	private void generateQuestInfoBox() {
		AnchorPane questInfoBox = new AnchorPane();
		questInfoBox.setPrefHeight(190);
		questInfoBox.setPrefWidth(240);
		questInfoBox.setLayoutX(10);
		questInfoBox.setLayoutY(60);
		questInfoBox.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		questInfoBox
				.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, CornerRadii.EMPTY, Insets.EMPTY)));
		for (int i = 0; i < quest.getQuestInfo().size(); i++) {
			questInfoBox.getChildren().add(quest.getQuestInfo().get(i));
		}
		this.getChildren().add(questInfoBox);
	}

	public Quest getQuest() {
		return quest;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}

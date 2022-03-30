package gui;

import java.util.ArrayList;

import exception.RandomNPCForQuestException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.Quest;

public class QuestPane extends AnchorPane {
	private int countQuest[] = { 0, 0, 0, 0, 0, 0 };
	private int markQuest[] = { 0, 0, 0, 0, 0, 0 };
	private ArrayList<EachQuestPane> listOfQuest;
	//private int amoutOfQuest;
	private EachQuestPane EQP;
	private final int initialX = 5;
	public QuestPane() {
		listOfQuest = new ArrayList<EachQuestPane>();
		setPrefHeight(610);
		setPrefWidth(800);
		this.setLayoutX(240);
		this.setLayoutY(40);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		generateEachPane();
	}

	public void generateEachPane() {
		for (int i = 0; i < 3; i++) {
			try {
				if (GameController.npcListForQuest.size() == 0) {
					throw new RandomNPCForQuestException();
				} else {
					EQP = new EachQuestPane();
				}
			} catch (RandomNPCForQuestException RE) {
				System.out.println("Don't have NPC for random");
				return;
			}
			if (markQuest[i] == 1)
				continue;
			markQuest[i] = 1;
			listOfQuest.add(EQP);
			if (EQP.getQuest().getOwnerOfQuest().getName() == "Banker") {
				countQuest[0] += 1;
				if (countQuest[0] == 6) {
					GameController.deletedNPCFromList("Banker");
					countQuest[0] = 0;
					EQP.setOrder(i);
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Grazier") {
				countQuest[1] += 1;
				if (countQuest[1] == 6) {
					GameController.deletedNPCFromList("Grazier");
					countQuest[1] = 0;
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Greengrocer") {
				countQuest[2] += 1;
				if (countQuest[2] == 6) {
					GameController.deletedNPCFromList("Greengrocer");
					countQuest[2] = 0;
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Guard") {
				countQuest[3] += 1;
				if (countQuest[3] == 6) {
					GameController.deletedNPCFromList("Guard");
					countQuest[3] = 0;
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Robbery") {
				countQuest[4] += 1;
				if (countQuest[4] == 6) {
					GameController.deletedNPCFromList("Robbery");
					countQuest[4] = 0;
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Wizard") {
				countQuest[5] += 1;
				if (countQuest[5] == 6) {
					GameController.deletedNPCFromList("Wizard");
					countQuest[5] = 0;
				}
			}
			EQP.setLayoutX(initialX + 265 * i);
			EQP.setLayoutY(10);
			this.getChildren().add(EQP);
			EQP.setOrder(i);
		}
		for (int i = 0; i < 3; i++) {
			try {
				if (GameController.npcListForQuest.size() == 0) {
					throw new RandomNPCForQuestException();
				} else {
					EQP = new EachQuestPane();
				}
			} catch (RandomNPCForQuestException RE) {
				System.out.println("Don't have NPC for random");
			}
			if (markQuest[i + 3] == 1)
				continue;
			markQuest[i + 3] = 1;
			listOfQuest.add(EQP);
			if (EQP.getQuest().getOwnerOfQuest().getName() == "Banker") {
				countQuest[0] += 1;
				if (countQuest[0] == 6) {
					GameController.deletedNPCFromList("Banker");
					countQuest[0] = 0;
					EQP.setOrder(i);
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Grazier") {
				countQuest[1] += 1;
				if (countQuest[1] == 6) {
					GameController.deletedNPCFromList("Grazier");
					countQuest[1] = 0;
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Greengrocer") {
				countQuest[2] += 1;
				if (countQuest[2] == 6) {
					GameController.deletedNPCFromList("Greengrocer");
					countQuest[2] = 0;
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Guard") {
				countQuest[3] += 1;
				if (countQuest[3] == 6) {
					GameController.deletedNPCFromList("Guard");
					countQuest[3] = 0;
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Robbery") {
				countQuest[4] += 1;
				if (countQuest[4] == 6) {
					GameController.deletedNPCFromList("Robbery");
					countQuest[4] = 0;
				}
			} else if (EQP.getQuest().getOwnerOfQuest().getName() == "Wizard") {
				countQuest[5] += 1;
				if (countQuest[5] == 6) {
					GameController.deletedNPCFromList("Wizard");
					countQuest[5] = 0;
				}
			}
			EQP.setLayoutX(initialX + 265 * i);
			EQP.setLayoutY(310);
			this.getChildren().add(EQP);
			EQP.setOrder(i + 3);
		}
	}

	public int[] getMarkQuest() {
		return markQuest;
	}

	public int[] getCountQuest() {
		return countQuest;
	}

}

package gui;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import livingthing.base.Human;
import logic.GameController;

public class NPCEachPlayerPane extends AnchorPane {
	private ArrayList<Human> npcList;
	private ArrayList<Text> npcStatus;
	private ArrayList<Button> npcAbilityButton;
	private ArrayList<Button> textLV;

	public NPCEachPlayerPane() {
		int initialY = 70;
		int loop = 0;
		npcList = new ArrayList<Human>();
		npcStatus = new ArrayList<Text>();
		npcAbilityButton = new ArrayList<Button>();
		textLV = new ArrayList<Button>();
		GameController.createNPC(npcList);
		for (Human npc : npcList) {
			Image npcImage = new Image(npc.getUrl());
			ImageView npcImageView = setupNPCImage(npcImage,npc);
			npcImageView.setLayoutY(initialY);
			npcImageView.setLayoutX(3);
			Button buttonLeftLV5 = setupButtonLeft(npc, 5);
			Button buttonRightLV5 = setupButtonRight(npc, 5);
			Button buttonLeftLV10 = setupButtonLeft(npc, 10);
			Button buttonRightLV10 = setupButtonRight(npc, 10);
			buttonLeftLV5.relocate(120, initialY + 55);
			buttonLeftLV10.relocate(120, initialY + 55);
			buttonRightLV5.relocate(194, initialY + 55);
			buttonRightLV10.relocate(194, initialY + 55);
			npcAbilityButton.add(buttonLeftLV10);
			npcAbilityButton.add(buttonRightLV10);
			npcAbilityButton.add(buttonLeftLV5);
			npcAbilityButton.add(buttonRightLV5);
			textLV.add(new Button("SELECT ABILITY LV10!"));
			textLV.add(new Button("SELECT ABILITY LV5!"));
			textLV.get(loop * 2).setVisible(false);
			textLV.get(loop * 2).relocate(83, initialY + 22);
			textLV.get(loop * 2).setPrefSize(200, 15);
			textLV.get(loop * 2).setBorder(new Border(
					new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
			textLV.get(loop * 2).setBackground(
					new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
			textLV.get(loop * 2 + 1).setVisible(false);
			textLV.get(loop * 2 + 1).setPrefSize(200, 15);
			textLV.get(loop * 2 + 1).relocate(83, initialY + 22);
			textLV.get(loop * 2 + 1).setBorder(new Border(
					new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
			textLV.get(loop * 2 + 1).setBackground(
					new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
			setAbilityToButton(buttonLeftLV5, npc, 1, 5, loop);
			setAbilityToButton(buttonLeftLV10, npc, 1, 10, loop);
			setAbilityToButton(buttonRightLV10, npc, 2, 10, loop);
			setAbilityToButton(buttonRightLV5, npc, 2, 5, loop);
			npcStatus.add(new Text(npc.getText()));
			npcStatus.get(loop).setFont(new Font(15));
			npcStatus.get(loop).setLayoutX(83);
			npcStatus.get(loop).setLayoutY(initialY + 15);
			initialY += 86;
			loop += 1;
			this.getChildren().add(npcImageView);
		}
		this.getChildren().addAll(textLV);
		this.getChildren().addAll(npcAbilityButton);
		this.getChildren().addAll(npcStatus);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPrefHeight(590);
		this.setPrefWidth(350);
	}

	private ImageView setupNPCImage(Image npcImage,Human npc) {
		ImageView npcImageView = new ImageView(npcImage);
		setupTootltipNPCImage(npcImageView,npc);
		npcImageView.setFitWidth(80);
		npcImageView.setFitHeight(80);
		return npcImageView;
	}

	private void setupTootltipNPCImage(ImageView view,Human npc) {
		Tooltip tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		tooltip.setText(npc.getName()+"\n"+"LEVEL UP: "+npc.levelUpAbilityText());
		view.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
		});
		view.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}

	public ArrayList<Human> getNpcList() {
		return npcList;
	}

	public void setNpcList(ArrayList<Human> npcList) {
		this.npcList = npcList;
	}

	public void setNpcStatus(Human npc, int order) {
		this.npcStatus.get(order).setText(npc.getText());
	}

	private Button setupButtonLeft(Human npc, int lv) {
		Button buttonLeft = new Button("SKILL1");
		buttonLeft.setVisible(false);
		setTooltip(buttonLeft, npc, 1, lv);
		buttonLeft.setBackground(
				new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(10), Insets.EMPTY)));
		buttonLeft.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		return buttonLeft;
	}

	private Button setupButtonRight(Human npc, int lv) {
		Button buttonRight = new Button("SKILL2");
		setTooltip(buttonRight, npc, 2, lv);
		buttonRight.setVisible(false);
		buttonRight.setBackground(
				new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(10), Insets.EMPTY)));
		buttonRight.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		return buttonRight;
	}

	private void setTooltip(Button button, Human npc, int type, int lv) {
		Tooltip tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		if (type == 1) {
			tooltip.setText(npc.abilityLeftText());
			if (lv == 10 &&!npc.getName().equals("Guard")) {
				tooltip.setText(npc.abilityLeftText() + npc.specialAbilityText());
			}
		}
		if (type == 2) {
			tooltip.setText(npc.abilityRightText());
			if (lv == 10) {
				tooltip.setText(npc.abilityRightText() + npc.specialAbilityText());
			}
		}
		button.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
		});
		button.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}

	private void setAbilityToButton(Button button, Human npc, int type, int lv, int order) {
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					if (type == 1) {
						npc.abilityLeft();
						if (lv == 10) {
							npc.specialAbility();
							npcAbilityButton.get(4 * order).setVisible(false);
							npcAbilityButton.get(4 * order + 1).setVisible(false);
							textLV.get(2 * order).setVisible(false);
						}
						if (lv == 5) {
							npcAbilityButton.get(4 * order + 2).setVisible(false);
							npcAbilityButton.get(4 * order + 3).setVisible(false);
							textLV.get(2 * order + 1).setVisible(false);
						}
					} else {
						npc.abilityRight();
						if (lv == 10) {
							npc.specialAbility();
							npcAbilityButton.get(4 * order).setVisible(false);
							npcAbilityButton.get(4 * order + 1).setVisible(false);
							textLV.get(2 * order).setVisible(false);
						}

						if (lv == 5) {
							npcAbilityButton.get(4 * order + 2).setVisible(false);
							npcAbilityButton.get(4 * order + 3).setVisible(false);
							textLV.get(2 * order + 1).setVisible(false);
						}
					}
				}
			}
		});
	}

	public void showSkillLevelFive(int order) {
		npcAbilityButton.get(4 * order + 2).setVisible(true);
		npcAbilityButton.get(4 * order + 3).setVisible(true);
		textLV.get(2 * order + 1).setVisible(true);
	}

	public void showSkillLevelTen(int order) {
		npcAbilityButton.get(4 * order).setVisible(true);
		npcAbilityButton.get(4 * order + 1).setVisible(true);
		textLV.get(2 * order).setVisible(true);
	}
}

package gui;

import application.Main;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.*;

public class MenuButton extends Button {
	private String buttonName;

	public MenuButton(String buttonName) {
		this.setButtonName(buttonName);
		this.setPrefHeight(50);
		this.setPrefWidth(200);
		this.setText(buttonName);
		this.setFont(new Font(20));
		this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		buttonWhenFree();
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY) && GameController.selectedButton == null) {
					buttonWhenPressed();
				} else if (arg0.getButton().equals(MouseButton.PRIMARY) && GameController.selectedButton != null) {
					buttonWhenSecondPressed();
				}
			}
		});
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public void buttonWhenPressed() {
		Image img2 = new Image("MenuButtonPressed.png");
		ImageView view2 = new ImageView(img2);
		this.setBackground(new Background(
				new BackgroundImage(img2, null, null, null, new BackgroundSize(200, 50, false, false, false, false))));
		GameController.selectedButton = this;
		GameController.showPanel();
		GameController.canvasPress.setVisible(true);
		GameController.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
		if (GameController.selectedInventoryCell != null)
			GameController.selectedInventoryCell.setBorder(new Border(
					new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		GameController.inventoryPane.setAmtSell(0);
		GameController.inventoryPane.getSellItem().productIsEmpty();
	}

	public void buttonWhenSecondPressed() {
		Image img = new Image("MenuButton.png");
		ImageView view = new ImageView(img);
		this.setBackground(new Background(
				new BackgroundImage(img, null, null, null, new BackgroundSize(200, 50, false, false, false, false))));
		GameController.hidePanel();
		if (this.buttonName.equals(GameController.selectedButton.buttonName)) {
			GameController.selectedButton = null;
			GameController.canvasPress.setVisible(false);
			if (GameController.selectedInventoryCell != null)
				GameController.selectedInventoryCell.setBorder(new Border(new BorderStroke(Color.BROWN,
						BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
			GameController.inventoryPane.setAmtSell(0);
			GameController.inventoryPane.getSellItem().productIsEmpty();

		} else {

			GameController.selectedButton.buttonWhenFree();
			buttonWhenPressed();
		}
		GameController.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
	}

	public void buttonWhenFree() {
		Image img = new Image("MenuButton.png");
		ImageView view = new ImageView(img);
		this.setBackground(new Background(
				new BackgroundImage(img, null, null, null, new BackgroundSize(200, 50, false, false, false, false))));
	}
}

package gui;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
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
import logic.GameController;

public class MoneyPane extends AnchorPane{
	private Text moneyText;
	public MoneyPane() {
		moneyText=new Text("MONEY: "+GameController.money+"$");
		moneyText.setFont(new Font(20));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setPrefWidth(200);
		this.setPrefHeight(20);
		this.getChildren().add(moneyText);
		this.relocate(1070, 80);
		moneyText.relocate(10, 5);
	}
	public void setMoneyText() {
		this.moneyText.setText("MONEY: "+GameController.money+"$");
	}
}

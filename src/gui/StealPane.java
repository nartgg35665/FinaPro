package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import logic.GameController;

public class StealPane extends AnchorPane{
	private Text moneyYoulost;
	public StealPane() {
		this.setPrefHeight(100);
		this.setPrefWidth(300);
		this.setLayoutX(490);
		this.setLayoutY(210);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		moneyYoulost=new Text();
		moneyYoulost.relocate(10, 30);
		moneyYoulost.setFont(new Font(20));
		Image quitButtonImg = new Image("Quit.png");
		ImageView quitButton = new ImageView(quitButtonImg);
		quitButton.setFitHeight(30);
		quitButton.setFitWidth(30);
		quitButton.relocate(300,10);
		quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					GameController.stealPane.setVisible(false);
				}
			}
		});
		this.getChildren().addAll(quitButton,moneyYoulost);
	}
	public void setMoneyYoulost(String moneyYoulost) {
		this.moneyYoulost.setText(moneyYoulost);
	}
	
}

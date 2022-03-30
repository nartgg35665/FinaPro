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

public class HowToPlayPane extends AnchorPane{
	private Text howToText;
	public HowToPlayPane() {
		this.setPrefHeight(100);
		this.setPrefWidth(350);
		this.setLayoutX(465);
		this.setLayoutY(210);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		howToText=new Text("How To Play\n- Get 100000$ to bring your farm back\n- Don't forget to pay tax!!!\n- Careful robber!!!");
		howToText.relocate(10, 10);
		howToText.setFont(new Font(20));
		Image quitButtonImg = new Image("Quit.png");
		ImageView quitButton = new ImageView(quitButtonImg);
		quitButton.setFitHeight(30);
		quitButton.setFitWidth(30);
		quitButton.relocate(350,10);
		quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					GameController.howToPlayPane.setVisible(false);
				}
			}
		});
		this.getChildren().addAll(quitButton,howToText);
	}
}

package gui;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.GameController;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import livingthing.base.*;
public class NPCPane extends AnchorPane{
	private NPCEachPlayerPane playerNPCStatus = new NPCEachPlayerPane();
	public NPCPane() {
		
		this.setLayoutX(455);
		this.setLayoutY(35);
		this.setPrefHeight(610);
		this.setPrefWidth(370);
		this.setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		setNPCTeamHeader();
		playerNPCStatus.setLayoutX(10);
		playerNPCStatus.setLayoutY(10);
		this.getChildren().add(playerNPCStatus);
	}
	private void setNPCTeamHeader() {
		Text yourTeam=new Text();
		yourTeam.setLayoutX(100);
		yourTeam.setLayoutY(40);
		yourTeam.setText("Your relationship");
		yourTeam.setFont(new Font(20));
		playerNPCStatus.getChildren().add(yourTeam);
	}
	public NPCEachPlayerPane getPlayerNPCStatus() {
		return playerNPCStatus;
	}
	public void setPlayerNPCStatus(NPCEachPlayerPane playernpcStatus) {
		playerNPCStatus = playernpcStatus;
	}

}

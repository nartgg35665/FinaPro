package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuPane extends GridPane{
	private ObservableList<MenuButton> itemButtonList = FXCollections.observableArrayList();
	public MenuPane() {
		this.setHgap(60);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
		this.setLayoutX(0);
		this.setLayoutY(650);
		this.setPrefWidth(1280);
		this.setPrefHeight(70);
		this.setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		addMenuButton();
	}
	public ObservableList<MenuButton> getItemButtonList(){
		return itemButtonList;
	}
	public void addMenuButton() {
		itemButtonList.add(new MenuButton("Animal"));
		itemButtonList.add(new MenuButton("Crop"));
		itemButtonList.add(new MenuButton("NPC"));
		itemButtonList.add(new MenuButton("Quest"));
		itemButtonList.add(new MenuButton("Inventory"));
		int loop=0;
		for(int i=0;i<5;i++) {
			this.add(itemButtonList.get(i), i, 0);
		}
	}
}

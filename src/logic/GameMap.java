package logic;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import building.base.*;
import gui.SoilCell;
import gui.SoilPane;
import building.Henhouse;
import building.Byre;
import building.Factory;

public class GameMap {

	private Image house;
	private Factory factory;
	private Henhouse henhouse;
	private Byre byre;
	private ArrayList<SoilPane> soilPaneList;

	public GameMap() {
		generateGrass();
		generateHouse();
		generateFactory();
		generateHenhouse();
		generateByre();
		generateSoil();
	}

	public void generateHouse() {
		house = new Image("House.png");
		ImageView houseImage = new ImageView(house);
		houseImage.setLayoutX(560);
		houseImage.setLayoutY(60);
		houseImage.setFitWidth(160);
		houseImage.setFitHeight(160);
		GameController.root.getChildren().add(houseImage);
	}

	public void generateFactory() {
		factory = new Factory();
		Image factory = new Image("Factory.png");
		ImageView factoryImage = new ImageView(factory);
		factoryImage.setLayoutX(120);
		factoryImage.setLayoutY(90);
		GameController.root.getChildren().add(factoryImage);
		factoryImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					GameController.craftPane.setVisible(true);
					GameController.canvasPress.setVisible(true);
					GameController.canvasPress.setScaleX(1);
					GameController.canvasPress.setScaleY(1.5);
					GameController.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
					if (GameController.selectedInventoryCell != null)
						GameController.selectedInventoryCell.setBorder(new Border(new BorderStroke(Color.BROWN,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
					GameController.inventoryPane.getSellItem().productIsEmpty();
					GameController.inventoryPane.setAmtSell(0);
				}
			}

		});
	}

	public void generateHenhouse() {
		henhouse = new Henhouse();
		GameController.henhouse = henhouse;
		Image henhouse = new Image("Henhouse.png");
		ImageView henhouseImage = new ImageView(henhouse);
		henhouseImage.setLayoutX(370);
		henhouseImage.setLayoutY(40);
		henhouseImage.setFitHeight(160);
		henhouseImage.setFitWidth(160);
		GameController.root.getChildren().add(henhouseImage);
		henhouseImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					GameController.henHousePane.setVisible(true);
					GameController.canvasPress.setVisible(true);
					GameController.canvasPress.setScaleX(1);
					GameController.canvasPress.setScaleY(1.5);
					GameController.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
					if (GameController.selectedInventoryCell != null)
						GameController.selectedInventoryCell.setBorder(new Border(new BorderStroke(Color.BROWN,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
					GameController.inventoryPane.getSellItem().productIsEmpty();
					GameController.inventoryPane.setAmtSell(0);
				}
			}

		});
	}

	public void generateByre() {
		GameController.byre = new Byre();
		byre = GameController.byre;
		Image byre = new Image("Byre.png");
		ImageView byreImage = new ImageView(byre);
		byreImage.setLayoutX(190);
		byreImage.setLayoutY(40);
		byreImage.setFitHeight(160);
		byreImage.setFitWidth(160);
		byreImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					GameController.byrePane.setVisible(true);
					GameController.canvasPress.setVisible(true);
					GameController.canvasPress.setScaleX(1);
					GameController.canvasPress.setScaleY(1.5);
					GameController.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
					if (GameController.selectedInventoryCell != null)
						GameController.selectedInventoryCell.setBorder(new Border(new BorderStroke(Color.BROWN,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
					GameController.inventoryPane.getSellItem().productIsEmpty();
					GameController.inventoryPane.setAmtSell(0);
				}
			}

		});
		GameController.root.getChildren().add(byreImage);
	}

	public void generateGrass() {
		Image grassImage = new Image("Grass.png");
		Pane grass = new Pane();
		grass.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		grass.setBackground(new Background(new BackgroundImage(grassImage, null, null, null,
				new BackgroundSize(1280, 510, false, false, false, false))));
		grass.setPrefHeight(550);
		grass.setPrefWidth(1280);
		grass.setLayoutY(120);
		GameController.root.getChildren().add(grass);
	}

	public void generateSoil() {
		int initialX = 70;
		soilPaneList = new ArrayList<SoilPane>();
		for (int j = 0; j < 3; j++) {
			int initialY = 230;
			for (int i = 0; i < 2; i++) {
				SoilPane sp = new SoilPane();
				if (j == 0 || j == 2) {
					sp.setVisible(false);
					soilPaneList.add(sp);
				} else {
					GameController.root.getChildren().add(sp);
				}
				sp.relocate(j * 400 + initialX, i * 220 + initialY);
			}
		}
		GameController.root.getChildren().addAll(soilPaneList);
	}

	public void addSoilPane() {
		for (int i = 0; i < Math.min(GameController.bonusNumberOfLand, 2); i++) {
			soilPaneList.get(i).setVisible(true);
		}
		for (int i = 2; i < Math.min(GameController.bonusNumberOfLand, 4); i++) {
			soilPaneList.get(i).setVisible(true);
		}
	}
}

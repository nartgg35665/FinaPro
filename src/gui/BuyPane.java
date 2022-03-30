package gui;

import building.Byre;
import building.Henhouse;
import exception.AnimalLimitExceedException;
import exception.MoneyNotEnoughException;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import livingthing.base.*;
import logic.GameController;
import livingthing.base.Animal;

public class BuyPane extends AnchorPane {
	private Buyable buyItem;
	private Text selectedAmtText;
	private int selectedAmt;
	private Text itemNameText;
	private Text itemDescriptionText;
	private Text growingTimeText;
	private Text itemPriceText;
	private Button buyButton;

	public BuyPane(Buyable buyItem) {
		this.buyItem = buyItem;
		this.setPrefSize(500, 150);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		selectedAmtText = new Text("0");
		selectedAmtText.setFont(new Font(15));
		selectedAmt = 0;
		selectedAmtText.relocate(50, 90);

		Image itemImage = new Image(buyItem.getUrl());
		ImageView itemImageView = new ImageView(itemImage);
		itemImageView.setFitHeight(80);
		itemImageView.setFitWidth(80);
		itemImageView.relocate(10, 10);
		if (buyItem instanceof Crop) {
			itemImageView.relocate(10, 0);
		}
		if (buyItem instanceof Animal) {
			itemDescriptionText = new Text("\"" + buyItem.getName() + " can produce "
					+ buyItem.getProduct().getProductName().toLowerCase() + " when grow up \n and  can obtain "
					+ ((Animal) buyItem).getProductWhenKilled().getProductName().toLowerCase() + " when killed\"");
			itemDescriptionText.setFont(new Font(15));
			itemDescriptionText.relocate(120, 40);
		} else {
			itemDescriptionText = new Text("");
		}
		if (buyItem.getName() != "Festival chicken" && buyItem.getName() != "Festival cow"&&buyItem instanceof Animal) {
			growingTimeText = new Text("Growing time: " + (int)(buyItem.getAgeToGrow()*GameController.bonusGrowTimeAnimal));
			growingTimeText.setFont(new Font(20));
			growingTimeText.relocate(120, 85);
			this.getChildren().add(growingTimeText);
		}
		else if(buyItem instanceof Crop){
			growingTimeText = new Text("Growing time: " + (int)(buyItem.getAgeToGrow()*GameController.bonusGrowTimeCrop));
			growingTimeText.setFont(new Font(20));
			growingTimeText.relocate(120, 85);
			this.getChildren().add(growingTimeText);
		}
		if (buyItem instanceof Animal) {
			itemPriceText = new Text("Price per unit : " + (int)(buyItem.getPrice() * GameController.bonusBuyAnimal));
			itemPriceText.setFont(new Font(20));
			itemPriceText.relocate(120, 110);
		}

		if (buyItem instanceof Crop) {
			itemPriceText = new Text("Price per unit : " + (int)(buyItem.getPrice() * GameController.bonusBuyCrop));
			itemPriceText.setFont(new Font(20));
			growingTimeText.relocate(120, 40);
			itemPriceText.relocate(120, 70);
		}
		itemNameText = new Text(buyItem.getName());
		itemNameText.setFont(new Font(20));
		itemNameText.relocate(120, 2);

		Image addImg = new Image("AddButton.png");
		ImageView addImgView = new ImageView(addImg);
		addImgView.setFitWidth(20);
		addImgView.setFitHeight(20);
		addImgView.relocate(80, 90);
		addImgView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Byre byre = GameController.byre;
				Henhouse henhouse = GameController.henhouse;
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					if (GameController.money >= buyItem.getPrice() * (selectedAmt + 1)) {
						selectedAmt++;
						selectedAmtText.setText(Integer.toString(selectedAmt));
					}
				}
			}
		});

		Image deleteImg = new Image("DeleteButton.png");
		ImageView deleteImgView = new ImageView(deleteImg);
		deleteImgView.setFitWidth(20);
		deleteImgView.setFitHeight(20);
		deleteImgView.relocate(10, 90);
		deleteImgView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY) && selectedAmt != 0) {
					selectedAmt--;
					selectedAmtText.setText(Integer.toString(selectedAmt));
				}
			}
		});

		buyButton = new Button();
		buyButton.setText("BUY");
		buyButton.setFont(new Font(15));
		buyButton.setTextFill(Color.WHITE);
		buyButton.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(10), Insets.EMPTY)));
		buyButton.setPrefSize(60, 30);
		buyButton.relocate(25, 110);
		buyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0){
				Byre byre = GameController.byre;
				Henhouse henhouse = GameController.henhouse;
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					if (buyItem instanceof Animal) {
						try {
							if (buyItem.getName().equals("Baby cow")) {
								for (int i = 0; i < selectedAmt; i++) {
									GameController.byrePane.addAnimal((Animal) ((Animal) buyItem).clone());
									byre.addTotalCow(1);
									GameController.money -= (int) (buyItem.getPrice() * GameController.bonusBuyAnimal);
								}
							} else if (buyItem.getName().equals("Festival cow")) {
								
								for (int i = 0; i < selectedAmt; i++) {
									GameController.byrePane.addAnimal((Animal) ((Animal) buyItem).clone());
									byre.addTotalCow(1);
									GameController.money -= (int) (buyItem.getPrice() * GameController.bonusBuyAnimal);
								}
							} else if (buyItem.getName().equals("Baby chicken")) {
								for (int i = 0; i < selectedAmt; i++) {
									GameController.henHousePane.addAnimal((Animal) ((Animal) buyItem).clone());
									henhouse.addTotalChicken(1);
									GameController.money -= (int) (buyItem.getPrice() * GameController.bonusBuyAnimal);
								}
							} else if (buyItem.getName().equals("Festival chicken")) {
								for (int i = 0; i < selectedAmt; i++) {
									GameController.henHousePane.addAnimal((Animal) ((Animal) buyItem).clone());
									henhouse.addTotalChicken(1);
									GameController.money -= (int) (buyItem.getPrice() * GameController.bonusBuyAnimal);
								}
							}
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						} catch(AnimalLimitExceedException e) {
							e.show();
						} catch(MoneyNotEnoughException e) {
							e.show();
						}
					}
					try {
						if (buyItem instanceof Crop && selectedAmt != 0) {
							ObservableList<SeedInventoryCell> sic = GameController.seedInventory.getSeedCells();
							Boolean buyAlready = false;
							for (int i = 0; i < sic.size(); i++) {
								if (sic.get(i).getCrop() != null) {
									if (sic.get(i).getCrop().getName().equals(buyItem.getName())) {
										sic.get(i).setTotal(sic.get(i).getTotal() + selectedAmt);
										buyAlready = true;
									}
								}
							}
							if (buyAlready == false) {
								for (int i = 0; i < sic.size(); i++) {
									if (sic.get(i).getCrop() == null && buyAlready == false) {
										sic.get(i).setCrop(buyItem);
										sic.get(i).setTotal(sic.get(i).getTotal() + selectedAmt);
										sic.get(i).setGraphic(buyItem.getUrl());
										buyAlready = true;
									}
								}
							}
							if(GameController.money<(int)(buyItem.getPrice() * GameController.bonusBuyCrop * selectedAmt)){
								throw new MoneyNotEnoughException();
							}
							GameController.money -= (int)(buyItem.getPrice() * GameController.bonusBuyCrop * selectedAmt);
							
						}
					}
					catch(MoneyNotEnoughException e) {
						e.show();
					}

					GameController.moneyPane.setMoneyText();
					selectedAmtText.setText("0");
					selectedAmt = 0;
				}
			}
		});
		this.getChildren().add(itemImageView);
		this.getChildren().add(deleteImgView);
		this.getChildren().add(selectedAmtText);
		this.getChildren().add(addImgView);
		this.getChildren().add(itemDescriptionText);
		this.getChildren().add(itemPriceText);
		this.getChildren().add(itemNameText);
		this.getChildren().add(buyButton);

	}
}

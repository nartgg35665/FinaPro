package gui;

import exception.InventoryFullException;
import exception.NoSelectedSeedException;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import livingthing.base.Crop;
import livingthing.base.Updateable;
import logic.GameController;

public class SoilCell extends AnchorPane implements Updateable {
	private Crop crop;
	private ImageView view;
	private Boolean isUpdate;
	private int total;
	public SoilCell() {
		this.setPrefHeight(50);
		this.setPrefWidth(50);
		Image soilImage = new Image("Soil.png");
		this.setBackground(new Background(new BackgroundImage(soilImage, null, null, null,
				new BackgroundSize(50, 50, false, false, false, false))));
		setOnClicked();
	}

	public void onFirstClickHandler() throws CloneNotSupportedException, NoSelectedSeedException{
		if(GameController.selectedSeedCell==null&&this.crop==null) {
			throw new NoSelectedSeedException();
		}
		if (this.crop == null) {
			Image cropImg = new Image(GameController.selectedSeedCell.getCrop().getUrl());
			view = new ImageView(cropImg);
			view.setFitWidth(40);
			view.setFitHeight(40);
			view.setLayoutX(5);
			view.setLayoutY(5);
			this.getChildren().add(view);
			this.crop = (Crop) GameController.selectedSeedCell.getCrop().clone();
			GameController.updateList.add(this);
			GameController.selectedSeedCell.setTotal(GameController.selectedSeedCell.getTotal() - 1);
			if (GameController.selectedSeedCell.getTotal() == 0) {
				GameController.selectedSeedCell.emptyCell();
			}
		}
	}

	public Crop getCrop() {
		return crop;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (this.crop == null) {
			return;
		}
		if (this.crop != null) {
			this.crop.update();
		}
		if (this.crop.getIsGrow() == true && this.crop.getAge() == this.crop.getAgeToGrow()) {
			this.setOnMouseClicked(null);
			Image cropImg = new Image(this.crop.getUrl());
			this.total = (((int) (Math.random() * 2 + 1)*GameController.bonusCollectedItemCrop));
			Tooltip tooltip = new Tooltip();
			tooltip.setFont(new Font(12));
			tooltip.setText("Collect: "+total);
			closeOnClicked();
			view.setImage(cropImg);
			view.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					try{
						tooltip.hide();
						view.setVisible(false);
						GameController.inventoryPane.addProduct(crop.getProduct(),total);
						crop = null;	
						setOnClicked();
						arg0.consume();
					}
					catch(InventoryFullException exception) {
						exception.show();
					}
				}
			});
			view.setOnMouseEntered((MouseEvent e) -> {
				if (this.crop != null) {
					tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
				}
			});
			view.setOnMouseExited((MouseEvent e) -> {
				tooltip.hide();
			});
		}

	}
	private void setOnClicked() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY)&&crop==null) {
					try {
						onFirstClickHandler();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch(NoSelectedSeedException e) {
						e.show();
					}
					
				}
			}
		});
	}
	private void closeOnClicked() {
		this.setOnMouseClicked(null);
	}
}

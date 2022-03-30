package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import livingthing.base.Buyable;
import livingthing.base.Crop;
import logic.GameController;

public class SeedInventoryCell extends AnchorPane {
	private Crop crop;
	private int total;
	private int order;
	private Text totalText;
	private ImageView view;
	public SeedInventoryCell() {
		total = 0;
		totalText = new Text("" + total);
		totalText.setFont(new Font(20));
		totalText.relocate(3, -5);
		totalText.setVisible(false);
		this.getChildren().add(totalText);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPrefWidth(60);
		this.setPrefHeight(60);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				onClickHandler();
			}
		});
	}

	private void onClickHandler() {
		if (this.crop != null) {
			if (GameController.selectedSeedCell != null) {
				GameController.selectedSeedCell.unhighlight();
				if (GameController.selectedSeedCell.getOrder() == this.getOrder()) {
					GameController.selectedSeedCell = null;
				} else {
					GameController.selectedSeedCell = null;
					GameController.selectedSeedCell = this;
					this.highlight();
				}
			} else {
				GameController.selectedSeedCell = this;
				this.highlight();
			}
		}
	}

	public void emptyCell() {
		GameController.selectedSeedCell = null;
		this.unhighlight();
		this.crop = null;
		view.setVisible(false);
		totalText.setVisible(false);
	}

	private void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public Crop getCrop() {
		return crop;
	}

	public void setCrop(Buyable buyItem) {
		this.crop = (Crop) buyItem;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		totalText.setText("" + total);
		totalText.setVisible(true);
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setGraphic(String url) {
		Image cropImg = new Image(url);
		view = new ImageView(cropImg);
		view.setFitHeight(50);
		view.setFitWidth(50);
		view.relocate(5, 5);
		setTooltip();
		this.getChildren().add(view);
	}

	private void setTooltip() {
		Tooltip tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		tooltip.setText("" + this.crop.getName() + "\nTime used : " + this.crop.getAgeToGrow());
		this.setOnMouseMoved((MouseEvent e) -> {
			if (this.crop != null)
				tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}
}

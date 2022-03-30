package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameController;
import product.base.*;

public class InventoryCell extends AnchorPane {
	private Product product;
	private Tooltip tooltip;
	private int total;
	private Text totalText;
	private ImageView view;

	public InventoryCell() {

		totalText = new Text();
		totalText.setFont(new Font(20));
		totalText.relocate(10, 0);
		this.getChildren().add(totalText);
		view = new ImageView();
		this.getChildren().add(view);
		this.total = 0;
		this.setPrefWidth(96);
		this.setPrefHeight(96);
		this.setMinWidth(96);
		this.setMinHeight(96);
		this.setPadding(new Insets(8));
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO fill in this method
				onClickHandler();
			}
		});

		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
	}

	private void onClickHandler() {
		if (product == null)
			return;
		if (GameController.selectedInventoryCell != null) {
			GameController.selectedInventoryCell.setBorder(new Border(
					new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
			GameController.inventoryPane.setAmtSell(0);
			GameController.inventoryPane.setIncome(0);
		}
		GameController.selectedInventoryCell = this;
		GameController.inventoryPane.getSellItem()
				.setBackground(new Image(GameController.selectedInventoryCell.getProduct().getUrl()));
		// GameController.inventoryPane.setSellItem(this);
		this.setBorder(new Border(
				new BorderStroke(Color.LIGHTGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
	}

	public void productIsEmpty() {
		view.setVisible(false);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.totalText.setVisible(false);
		this.product = null;
		this.setOnMouseMoved(null);
		this.setOnMouseExited(null);
		GameController.selectedInventoryCell = null;
	}

	public void setBackground(Image image) {
		view.setVisible(true);
		view.setImage(image);
		view.setFitHeight(50);
		view.setFitHeight(50);
		view.relocate(25, 20);
	}

	public void setUpTooltip() {
		tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		tooltip.setText(this.product.getProductName() + " Total : " + this.getTotal());
		if (product != null) {
			this.setOnMouseMoved((MouseEvent e) -> {
				tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
			});
			this.setOnMouseExited((MouseEvent e) -> {
				tooltip.hide();
			});
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		setTotalText(Integer.toString(total));
	}

	public ImageView getView() {
		return view;
	}

	public void setView(ImageView view) {
		this.view = view;
	}

	public Text getTotalText() {
		return totalText;
	}

	public void setTotalText(String totalText) {
		this.totalText.setVisible(true);
		this.totalText.setText(totalText);
		;
	}

}

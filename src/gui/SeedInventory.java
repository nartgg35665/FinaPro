package gui;

import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import logic.GameController;

public class SeedInventory extends AnchorPane {
	private ObservableList<SeedInventoryCell> seedCells=FXCollections.observableArrayList();

	public SeedInventory() {
		this.relocate(1200, 130);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPrefHeight(507);
		this.setPrefWidth(66);
		int initialY=3,order=0;
		for (int i = 0; i < 8; i++) {
			SeedInventoryCell cell=new SeedInventoryCell();
			cell.relocate(4,i*63+initialY);
			cell.setOrder(order);
			order++;
			seedCells.add(cell);
			this.getChildren().add(cell);
		}
	}

	public ObservableList<SeedInventoryCell> getSeedCells() {
		return seedCells;
	}

	public void setSeedCells(ObservableList<SeedInventoryCell> seedCells) {
		this.seedCells = seedCells;
	}
}

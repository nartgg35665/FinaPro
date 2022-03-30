package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;

public class SoilPane extends GridPane{
	private ObservableList<SoilCell> sc=FXCollections.observableArrayList();
	public SoilPane() {
		this.setHgap(10);
		this.setVgap(10);
		for(int i=0;i<3;i++) {
			for(int j=0;j<5;j++) {
				this.add(new SoilCell(), j, i);
			}
		}
	}
}

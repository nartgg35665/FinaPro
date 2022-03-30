package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InventoryFullException extends Exception{
	public InventoryFullException() {
		super("Inventory full");
	}
	public void show() {
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText(null);
		a.setContentText("Inventory full!");
		a.setTitle("Warning");
		a.show();
	}
}

package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NoSelectedSeedException extends Exception{
	public NoSelectedSeedException() {
		super("No selected seed!");
	}
	public void show() {
		Alert a = new Alert(AlertType.WARNING);
		a.setTitle("Warning");
		a.setHeaderText(null);
		a.setContentText("No selected seed!");
		a.show();
	}
}

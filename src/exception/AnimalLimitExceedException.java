package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AnimalLimitExceedException extends Exception{
	public AnimalLimitExceedException() {
		super("Animal limit exceed");
	}
	public void show() {
		Alert a = new Alert(AlertType.WARNING);
		a.setTitle("Warning");
		a.setHeaderText(null);
		a.setContentText("Animal limit exceed");
		a.show();
	}
}

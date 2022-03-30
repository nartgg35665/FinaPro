package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MoneyNotEnoughException extends Exception{
	public MoneyNotEnoughException() {
		super("Money Not Enough");
	}
	public void show() {
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText(null);
		a.setContentText("Money Not Enough!");
		a.setTitle("Warning");
		a.show();
	}
}

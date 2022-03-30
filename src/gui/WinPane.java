package gui;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameController;

public class WinPane extends AnchorPane{
	private Text win;
	private Text timeUsed;
	private Button newGameBtn;
	public WinPane() {
		win = new Text("You win!");
		win.setTextAlignment(TextAlignment.CENTER);
		win.relocate(45, 20);
		win.setFont(new Font(30));
		int days = GameController.timeUsed/(24*60*60);
		int hours = (GameController.timeUsed%(24*60*60))/(60*60);
		int mins = (GameController.timeUsed%(60*60))/60;
		int sec = GameController.timeUsed%60;
		timeUsed = new Text("Time used: "+days+"d"+' '+hours+"hr"+' '+mins+"min"+' '+sec+"s");
		timeUsed.relocate(40,40);
		newGameBtn = new Button("New game");
		newGameBtn.relocate(65, 60);
		newGameBtn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(20), Insets.EMPTY)));
		newGameBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				GameController.timerThread.stop();
				GameController.playSoundLoop(GameController.menuSound);
				GameController.stage.setScene(GameController.startScene);
				GameController.canvasPress.setScaleY(1);
				GameController.canvasPress.setVisible(false);
				GameController.soundDay.stop();
				GameController.soundNight.stop();
			}
		});
		newGameBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				newGameBtn.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(20), Insets.EMPTY)));
			}
		});
		newGameBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				newGameBtn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(20), Insets.EMPTY)));
			}
		});
		this.setPrefSize(200, 100);
		this.relocate(540, 310);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.getChildren().addAll(win,newGameBtn,timeUsed);
	}
	public Text getTimeUsed() {
		return timeUsed;
	}
	public void setTimeUsed(int timeUsed) {
		String days = Integer.toString(timeUsed/(24*60*60));
		String hours = Integer.toString((timeUsed%(24*60*60))/(60*60));
		String mins = Integer.toString((timeUsed%(60*60))/60);
		String sec = Integer.toString(timeUsed%60);
		this.timeUsed.setText("Time used: "+days+"d"+' '+hours+"hr"+' '+mins+"min"+' '+sec+"s");
	}
}

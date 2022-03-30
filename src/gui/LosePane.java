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

public class LosePane extends AnchorPane{
	private Text lose;
	private Button newGameBtn;
	public LosePane() {
		lose = new Text("You lose!");
		lose.setTextAlignment(TextAlignment.CENTER);
		lose.relocate(45, 20);
		lose.setFont(new Font(30));
		newGameBtn = new Button("New game");
		newGameBtn.relocate(65, 50);
		newGameBtn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(20), Insets.EMPTY)));
		newGameBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				GameController.timerThread.stop();
				GameController.playSoundLoop(GameController.menuSound);
				GameController.stage.setScene(GameController.startScene);
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
		this.getChildren().addAll(lose,newGameBtn);
	}
}

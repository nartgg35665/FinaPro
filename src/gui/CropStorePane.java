package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import livingthing.*;
import logic.GameController;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import gui.BuyPane;

public class CropStorePane extends GridPane {
	private Label shop;
	private BuyPane buyBlueberryPane;
	private BuyPane buyCabbagePane;
	private BuyPane buyCornPane;
	private BuyPane buyMelonPane;
	private BuyPane buyPumpkinPane;
	private BuyPane buyStarfruitPane;
	private BuyPane buyTomatoPane;

	public CropStorePane() {
		this.setPrefSize(1000, 600);
		this.setPadding(new Insets(5));
		this.setHgap(5);
		this.setVgap(5);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.relocate(136, 30);
		buyBlueberryPane = new BuyPane(new BlueberryTree());
		buyCabbagePane = new BuyPane(new CabbageTree());
		buyCornPane = new BuyPane(new CornTree());
		buyMelonPane = new BuyPane(new MelonTree());
		buyPumpkinPane = new BuyPane(new PumpkinTree());
		buyTomatoPane = new BuyPane(new TomatoTree());
		shop = new Label("SHOP");
		shop.setFont(new Font(80));
		this.setHalignment(shop, HPos.CENTER);
		this.add(buyBlueberryPane, 1, 0);
		this.add(buyCabbagePane, 0, 1);
		this.add(buyCornPane, 0, 3);
		this.add(buyTomatoPane, 1, 1);
		this.add(buyPumpkinPane, 0, 2);
		this.add(buyMelonPane, 1, 2);
		this.add(shop, 0, 0);
	}
	public void addStarfruit() {
		buyStarfruitPane = new BuyPane(new StarfruitTree());
		this.add(buyStarfruitPane, 1, 3);
	}
}

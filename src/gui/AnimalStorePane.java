package gui;

import javafx.event.EventHandler;
import livingthing.ChickenFes;
import livingthing.CowFes;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameController;
import livingthing.Cow;
import livingthing.Chicken;

public class AnimalStorePane extends AnchorPane {
	private Text shop;
	private BuyPane buyChickenPane;
	private BuyPane buyCowPane;
	private BuyPane buyChickenFesPane;
	private BuyPane buyCowFesPane;

	public AnimalStorePane() {

		this.relocate(125, 100);
		this.setPrefSize(1030, 400);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		shop=new Text("SHOP");
		shop.setFont(new Font(50));
		shop.relocate(450,5);
	
		buyChickenPane = new BuyPane(new Chicken());
		buyChickenPane.relocate(10, 80);

		buyCowPane = new BuyPane(new Cow());
		buyCowPane.relocate(520, 80);
		
		this.getChildren().addAll(shop,buyCowPane, buyChickenPane);
	}
	public void addSpecialPane() {
		buyChickenFesPane = new BuyPane(new ChickenFes());
		buyChickenFesPane.relocate(10, 240);
		buyCowFesPane = new BuyPane(new CowFes());
		buyCowFesPane.relocate(520, 240);
		this.getChildren().addAll(buyCowFesPane, buyChickenFesPane);
	}
}

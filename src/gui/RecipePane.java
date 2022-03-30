package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import combination.base.*;
import java.util.*;
import product.base.*;
public class RecipePane extends VBox{
	private Combination combination;
	public RecipePane(Combination combination) {
		this.combination = combination;
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
		this.setSpacing(5);
		ArrayList<Product> ingredient = combination.getIngredient();
		
		for(int i=0;i<ingredient.size();i++) {
			ProductPane productPane = new ProductPane(ingredient.get(i));
			this.getChildren().add(productPane);
			this.setMargin(productPane, new Insets(5));
		}
	}
}

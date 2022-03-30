package gui;
import product.base.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class ProductPane extends AnchorPane{
	private Product product;
	private Text productNameText;
	private Image productImg;
	private ImageView productImgView;
	public ProductPane(Product product) {
		this.product = product;
		this.setPrefSize(150, 100);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		productImg = new Image(product.getUrl());
		productImgView = new ImageView(productImg);
		productImgView.relocate(20,20);
		productNameText = new Text(product.getProductName());
		productNameText.relocate(70, 40);
		productNameText.setFont(new Font(15));
		this.getChildren().addAll(productImgView,productNameText);
	}
}

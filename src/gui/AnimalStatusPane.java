package gui;

import javafx.event.EventHandler;
import gui.HenHousePane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import livingthing.base.*;
import logic.GameController;

import java.util.ArrayList;

import building.Byre;
import exception.InventoryFullException;
public class AnimalStatusPane extends AnchorPane implements Updateable{
	private ImageView animalImgView;
	private Image animalImg;
	private Image productImg;
	private ImageView productImgView;
	private String animalName;
	private Text animalNameText;
	private int numberOfProduct;
	private Text numberOfProductText;
	private Animal animal;
	private Button killButton;
	public AnimalStatusPane(Animal animal) {
		this.animal = animal;
		this.setPrefSize(400, 160);
		
		animalImg = new Image(animal.getUrl());
		animalImgView = new ImageView(animalImg);
		animalImgView.setFitHeight(80);
		animalImgView.setFitWidth(90);
		animalImgView.relocate(10, 10);
		
		
		productImg = new Image(animal.getProduct().getUrl());
		productImgView = new ImageView(productImg);
		productImgView.setFitHeight(40);
		productImgView.setFitWidth(40);
		productImgView.relocate(30,105);
		
		numberOfProduct = 0;
		numberOfProductText = new Text("Number of "+animal.getProduct().getProductName().toLowerCase()+": "+numberOfProduct);
		numberOfProductText.relocate(120, 115);
		
		animalName = animal.getName();
		animalNameText = new Text(animalName);
		animalNameText.setFont(new Font(30));
		animalNameText.relocate(120, 30);
		
		
		
		
		killButton = new Button();
		killButton.setPrefSize(60,20);
		killButton.relocate(330,10);
		killButton.setText("Kill");
		killButton.setTextFill(Color.WHITE);
		killButton.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		killButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					if(animal.getName()!="Baby cow" && animal.getName()!="Baby chicken") {
						try{
							GameController.inventoryPane.addProduct(animal.getProductWhenKilled(),GameController.bonusCollectedItemAnimal);
						}
						catch(InventoryFullException e) {
							e.show();
						}
					}
					if(animal.getName()=="Baby cow" || animal.getName()=="Cow" || animal.getName()=="Festival cow")	removeFromByre();
					else																							removeFromHenHouse();
				}
			}
		});
		
		this.getChildren().addAll(animalImgView,productImgView,animalNameText,numberOfProductText,killButton);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void setAnimalName(String animallName) {
		this.animalName = animalName;
		animalNameText.setText(animallName);
	}
	public void setNumberOfProduct(int numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
		numberOfProductText.setText("Number of "+animal.getProduct().getProductName().toLowerCase()+": "+numberOfProduct);
	}
	public int getNumberOfProduct() {
		return numberOfProduct;
	}
	public Animal getAnimal() {
		return animal;
	}
	private void removeFromByre() {
		ArrayList<AnimalStatusPane> animalInByre = new ArrayList<AnimalStatusPane>();
		animalInByre = GameController.byrePane.getAnimalInByre();
		GameController.byre.addTotalCow(-1);
		for(int i=0;i<animalInByre.size();i++) {
			if(this==animalInByre.get(i)) {
				GameController.byrePane.getAnimalContainer().getChildren().remove(this);
				animalInByre.remove(i);
				break;
			}
		}
		if(GameController.byrePane.getAnimalInByre().isEmpty()) {
			GameController.byrePane.getSp().setVisible(false);
		}
	}
	
	private void removeFromHenHouse(){
		ArrayList<AnimalStatusPane> animalInHenHouse= new ArrayList<AnimalStatusPane>();
		animalInHenHouse = GameController.henHousePane.getAnimalInHenHouse();
		GameController.henhouse.addTotalChicken(-1);
		for(int i=0;i<animalInHenHouse.size();i++) {
			if(this==animalInHenHouse.get(i)) {
				GameController.henHousePane.getAnimalContainer().getChildren().remove(this);
				animalInHenHouse.remove(i);
				break;
			}
		}
		if(GameController.henHousePane.getAnimalInHenHouse().isEmpty()) {
			GameController.henHousePane.getSp().setVisible(false);
		}
	}
	public void update() {
		animal.update();
		if(animal.getIsGrow()==true) {
			setAnimalName(animal.getName());
			animalImg = new Image(animal.getUrl());
			animalImgView.setImage(animalImg);
			if(animal.getProductCdTimer()==0) {
				setNumberOfProduct(getNumberOfProduct()+GameController.bonusCollectedItemAnimal);
				animal.setProductCdTimer((int)(animal.getProductCdTime()*GameController.bonusTimeCollectItemAnimal));
			}
		}
	}
}

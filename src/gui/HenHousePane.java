package gui;
import java.util.ArrayList;


import exception.AnimalLimitExceedException;
import exception.InventoryFullException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import livingthing.base.Animal;
import logic.GameController;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class HenHousePane extends AnchorPane{
	private ArrayList<AnimalStatusPane> animalInHenHouse = new ArrayList<AnimalStatusPane>();
	private VBox animalContainer;
	private ScrollPane sp;
	private Button claimEggButton;
	private Button claimEggFesButton;
	private Text limit;
	public HenHousePane() {
		this.setPrefSize(520, 600);
		this.relocate(390, 40);
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		
		
		animalContainer = new VBox();
		
		
		sp = new ScrollPane();
		sp.setVmax(560);
		sp.setPrefSize(435, USE_COMPUTED_SIZE);
		sp.setMaxSize(435, 500);
        sp.relocate(60, 30);
        sp.setContent(animalContainer);
        sp.setPadding(new Insets(7));
		sp.setBorder(new Border(
				new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		sp.setVisible(false);
		sp.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		//sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		this.setPadding(new Insets(5));
		VBox.setVgrow(sp, Priority.ALWAYS);
		
		limit = new Text("Maximum chicken: "+GameController.henhouse.getLimitOfAllChicken());
		limit.setFont(new Font(20));
		limit.relocate(185, 5);
		
		claimEggButton = new Button();
		claimEggButton.setPrefSize(210,40);
		claimEggButton.relocate(60,540);
		claimEggButton.setText("Claim egg");
		claimEggButton.setTextFill(Color.WHITE);
		claimEggButton.setFont(new Font(20));
		claimEggButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		claimEggButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					for(AnimalStatusPane asp:animalInHenHouse) {
						if(asp.getAnimal().getProduct().getProductName()!="Egg")	continue;
						try{
							GameController.inventoryPane.addProduct(asp.getAnimal().getProduct(),asp.getNumberOfProduct()*GameController.bonusCollectedItemAnimal);
							asp.setNumberOfProduct(0);
						}
						catch(InventoryFullException e) {
							e.show();
						}

					}
					
				}
			}
		});
		
		claimEggFesButton = new Button();
		claimEggFesButton.setPrefSize(210,40);
		claimEggFesButton.relocate(285,540);
		claimEggFesButton.setText("Claim festival egg");
		claimEggFesButton.setTextFill(Color.WHITE);
		claimEggFesButton.setFont(new Font(20));
		claimEggFesButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		claimEggFesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					for(AnimalStatusPane asp:animalInHenHouse) {
						if(asp.getAnimal().getProduct().getProductName()!="Festival egg")	continue;
						try{
							GameController.inventoryPane.addProduct(asp.getAnimal().getProduct(),asp.getNumberOfProduct()*GameController.bonusCollectedItemAnimal);
							asp.setNumberOfProduct(0);
						}
						catch(InventoryFullException e) {
							e.show();
						}
					}
					
				}
			}
		});
		
		Image quitButtonImg = new Image("Quit.png");
		ImageView quitButton = new ImageView(quitButtonImg);
		quitButton.setFitHeight(30);
		quitButton.setFitWidth(30);
		quitButton.relocate(500,10);
		quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					GameController.henHousePane.setVisible(false);
					GameController.canvasPress.setVisible(false);
					GameController.canvasPress.setScaleX(1);
					GameController.canvasPress.setScaleY(1);
					GameController.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
				}
			}
		});
		animalContainer.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		animalContainer.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		animalContainer.setSpacing(5);
		this.getChildren().addAll(animalContainer,sp,quitButton,claimEggButton,claimEggFesButton,limit);
	}
	public ArrayList<AnimalStatusPane> getAnimalInHenHouse(){
		return animalInHenHouse;
	}
	public VBox getAnimalContainer() {
		return animalContainer;
	}
	public ScrollPane getSp() {
		return sp;
	}
	public void addAnimal(Animal animal) throws CloneNotSupportedException,AnimalLimitExceedException{
		if(GameController.bonusHaveChicken+GameController.henhouse.getLimitOfAllChicken()==
		   GameController.henhouse.getTotalChicken()) {
			throw new AnimalLimitExceedException();
		}
		sp.setVisible(true);
		AnimalStatusPane animalStatusPane = new AnimalStatusPane((Animal)animal.clone());
		animalContainer.getChildren().add(animalStatusPane);
		animalInHenHouse.add(animalStatusPane);
		GameController.updateList.add(animalStatusPane);
	}
	public void setLimit() {
		limit.setText("Maximum chciken: "+(GameController.henhouse.getLimitOfAllChicken()+GameController.bonusHaveChicken));
	}
}

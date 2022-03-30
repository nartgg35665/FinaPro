package gui;
import java.util.ArrayList;
import exception.MoneyNotEnoughException;
import java.util.Set;

import building.Byre;
import building.Henhouse;
import exception.AnimalLimitExceedException;
import exception.InventoryFullException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import livingthing.Cow;
import livingthing.CowFes;
import livingthing.base.Animal;
import logic.GameController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
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
public class ByrePane extends AnchorPane{
	private ArrayList<AnimalStatusPane> animalInByre = new ArrayList<AnimalStatusPane>();
	private ImageView quitButton;
	private VBox animalContainer;
	private ScrollPane sp;
	private Button claimMilkButton;
	private Button claimMilkFesButton;
	private Text limit;
	public ByrePane() {
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
		
		limit = new Text("Maximum cow: "+GameController.byre.getLimitOfAllCow());
		limit.setFont(new Font(20));
		limit.relocate(190, 5);
		
		claimMilkButton = new Button();
		claimMilkButton.setPrefSize(210,40);
		claimMilkButton.relocate(60,540);
		claimMilkButton.setText("Claim milk");
		claimMilkButton.setTextFill(Color.WHITE);
		claimMilkButton.setFont(new Font(20));
		claimMilkButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		claimMilkButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0){
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					for(AnimalStatusPane asp:animalInByre) {
						if(asp.getAnimal().getProduct().getProductName()!="Milk")	continue;
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
		
		claimMilkFesButton = new Button();
		claimMilkFesButton.setPrefSize(210,40);
		claimMilkFesButton.relocate(285,540);
		claimMilkFesButton.setText("Claim festival milk");
		claimMilkFesButton.setTextFill(Color.WHITE);
		claimMilkFesButton.setFont(new Font(20));
		claimMilkFesButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		claimMilkFesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					for(AnimalStatusPane asp:animalInByre) {
						if(asp.getAnimal().getProduct().getProductName()!="Festival milk")	continue;
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
					GameController.byrePane.setVisible(false);
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
		this.getChildren().addAll(animalContainer,sp,quitButton,claimMilkButton,claimMilkFesButton,limit);
	}
	public ArrayList<AnimalStatusPane> getAnimalInByre(){
		return animalInByre;
	}
	public VBox getAnimalContainer() {
		return animalContainer;
	}
	public ScrollPane getSp() {
		return sp;
	}
	public void setLimit() {
		limit.setText("Maximum cow: "+(GameController.byre.getLimitOfAllCow()+GameController.bonusHaveCow));
	}
	public void addAnimal(Animal animal) throws CloneNotSupportedException,AnimalLimitExceedException,MoneyNotEnoughException{
		if(GameController.money<(int) (animal.getPrice() * GameController.bonusBuyAnimal)) {
			throw new MoneyNotEnoughException();
		}
		if(GameController.byre.getLimitOfAllCow()+GameController.bonusHaveCow==
		   GameController.byre.getTotalCow()) {
			throw new AnimalLimitExceedException();
		}
		sp.setVisible(true);
		AnimalStatusPane animalStatusPane = new AnimalStatusPane((Animal)animal.clone());
		animalContainer.getChildren().add(animalStatusPane);
		animalInByre.add(animalStatusPane);
		GameController.updateList.add(animalStatusPane);
	}
}

package gui;
import combination.base.*;
import exception.InventoryFullException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameController;
import product.base.Product;
import combination.Breakfast;
import combination.Coleslaw;
import combination.FriedEgg;
import combination.LuckyLunch;
import combination.Mayonaise;
public class CraftPane extends AnchorPane{
	private Combination combination[] = {new Breakfast(),new Coleslaw(),new FriedEgg(),new LuckyLunch(),new Mayonaise()};
	private Image combinationImg[] = new Image[5];
	private Button combinationName[] = new Button[5];
	private ImageView combinationImgView[] = new ImageView[5]; 
	private RecipePane recipePane[] = new RecipePane[5];
	private Image add = new Image("AddButton.png");
	private Image del = new Image("DeleteButton.png");
	private ImageView leftButton;
	private ImageView rightButton;
	private ImageView addButton;
	private ImageView delButton;
	private int craftAmt;
	private int currentId;
	private Text craftAmtText;
	private Button craftButton;
	public CraftPane() {
		this.setPrefSize(350, 500);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		this.relocate(465, 50);

		for(int i=0;i<5;i++) {
			recipePane[i] = new RecipePane(combination[i]);
			combinationImg[i] = new Image(combination[i].getUrl());
			combinationImgView[i] = new ImageView(combinationImg[i]);
			recipePane[i].relocate(100, 130);
			recipePane[i].setVisible(false);
			combinationImgView[i].relocate(140,40);
			combinationImgView[i].setFitHeight(70);
			combinationImgView[i].setFitWidth(70);
			combinationImgView[i].setVisible(false);
			combinationName[i] = new Button();
			combinationName[i].setPrefSize(350, 20);
			combinationName[i].setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
			combinationName[i] .setText(combination[i].getProductName());
			combinationName[i].setTextAlignment(TextAlignment.CENTER);
			combinationName[i].setVisible(false);
			combinationName[i].setFont(new Font(20));
			this.getChildren().addAll(recipePane[i],combinationImgView[i],combinationName[i]);
		}
		recipePane[0].setVisible(true);
		combinationImgView[0].setVisible(true);
		combinationName[0].setVisible(true);
		currentId = 0;
		leftButton = new ImageView(del);
		leftButton.setFitHeight(30);
		leftButton.setFitWidth(30);
		leftButton.relocate(95, 60);
		leftButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				currentId = (currentId+4)%5;
				changeCombination(currentId);
				setCraftAmt(0);
			}
		});
		
		rightButton = new ImageView(add);
		rightButton.setFitHeight(30);
		rightButton.setFitWidth(30);
		rightButton.relocate(230, 60);
		rightButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				currentId = (currentId+1)%5;
				changeCombination(currentId);
				setCraftAmt(0);
			}
		});
		
		addButton = new ImageView(add);
		addButton.setFitHeight(30);
		addButton.setFitWidth(30);
		addButton.relocate(230, 380);
		addButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				int limit = 1000000;
				for(Product ingredient:combination[currentId].getIngredient()) {
					int total = 0;
					for(InventoryCell ic:GameController.inventoryPane.getInventoryCells()) {
						if(ic.getProduct()==null)	continue;
						if(ic.getProduct().getClass().equals(ingredient.getClass())) {
							total+=ic.getTotal();
						}
					}
					limit = Math.min(limit,total);
				}
				if(craftAmt+1<=limit) {
					setCraftAmt(getCraftAmt()+1);
				}
				
			}
		});
		delButton = new ImageView(del);
		delButton.setFitHeight(30);
		delButton.setFitWidth(30);
		delButton.relocate(95, 380);
		delButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				if(craftAmt>=1) {
					setCraftAmt(getCraftAmt()-1);
				}
			}
		});
		
		craftAmt = 0;
		craftAmtText = new Text("0");
		craftAmtText.relocate(170, 388);
		craftAmtText.setFont(new Font(20));
		
		craftButton = new Button();
		craftButton.setText("Craft");
		craftButton.setTextFill(Color.WHITE);
		craftButton.setPrefSize(60, 30);
		craftButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		craftButton.relocate(145, 420);
		craftButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				try{
					for(Product ingredient:combination[currentId].getIngredient()) {
						System.out.println(ingredient.getClass());
						GameController.inventoryPane.deleteProduct(ingredient,craftAmt);
					}
					GameController.inventoryPane.addProduct((Product)combination[currentId].clone(), craftAmt);
					setCraftAmt(0);
				}
				catch(CloneNotSupportedException e) {
					System.out.println(e);
				}
				catch(InventoryFullException e) {
					e.show();
				}
				
			}
		});
		
		Image quitButtonImg = new Image("Quit.png");
		ImageView quitButton = new ImageView(quitButtonImg);
		quitButton.setFitHeight(30);
		quitButton.setFitWidth(30);
		quitButton.relocate(300,10);
		quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					GameController.craftPane.setVisible(false);
					GameController.canvasPress.setVisible(false);
					GameController.canvasPress.setScaleX(1);
					GameController.canvasPress.setScaleY(1);
					GameController.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
				}
			}
		});
		
		
		
		this.getChildren().addAll(leftButton,rightButton,addButton,delButton,craftAmtText,craftButton,quitButton);
	}
	public void changeCombination(int index) {
		for(int i=0;i<5;i++) {
			recipePane[i].setVisible(false);
			combinationImgView[i].setVisible(false);
			combinationName[i].setVisible(false);
		}
		recipePane[index].setVisible(true);
		combinationImgView[index].setVisible(true);
		combinationName[index].setVisible(true);
	}
	
	public void setCraftAmt(int craftAmt) {
		this.craftAmt = craftAmt;
		craftAmtText.setText(Integer.toString(craftAmt));
	}
	public int getCraftAmt() {
		return craftAmt;
	}
}

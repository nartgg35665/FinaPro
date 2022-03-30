package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import livingthing.base.Crop;
import livingthing.base.Human;
import logic.GameController;
import product.base.Product;
import exception.InventoryFullException;
import gui.InventoryCell;

public class InventoryPane extends AnchorPane {
	private ObservableList<InventoryCell> inventoryCells = FXCollections.observableArrayList();
	private int amtSell;
	private Text amtSellText;
	private Text inventoryText;
	private Button SellButton;
	private int income;
	private Text incomeText;
	private InventoryCell sellItem;
	private ImageView npcImage;
	private Button sellButton;
	private Button giftButton;
	private GridPane inventory;
	private Text incomeDescription;
	private Text npcName;
	private ImageView addImgView;
	private ImageView deleteImgView;
	private ImageView addNPCImgView;
	private ImageView deleteNPCImgView;
	private int orderOfNPC;

	public InventoryPane() {
		setupNPC();
		setupGiftButton();
		setupDeleteNPCButton();
		setupAddNPCButton();
		setupAmtSellText();
		setupIncomeText();
		setupInventoryPane();
		setupInventory();
		setupSellItem();
		setupAddButton();
		setupDeleteButton();
		setupIncomeDescription();
		setupSellButton();
		this.getChildren().addAll(inventory, sellItem, addImgView, deleteImgView, npcName, npcImage, giftButton,
				addNPCImgView, deleteNPCImgView, amtSellText, incomeText, incomeDescription, sellButton, inventoryText);
	}

	private void setupInventory() {
		inventory = new GridPane();
		inventory.setHgap(1);
		inventory.setVgap(1);
		inventory.setPrefHeight(416);
		inventory.setPrefWidth(416);
		inventory.setLayoutX(80);
		inventory.setLayoutY(60);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				InventoryCell inventoryCell = new InventoryCell();
				inventory.add(inventoryCell, i, j);
				inventoryCells.add(inventoryCell);
			}
		}
	}

	public void setupGiftButton() {
		giftButton = new Button();
		giftButton.setText("GIFT");
		giftButton.setTextFill(Color.BLACK);
		giftButton.relocate(559, 460);
		giftButton.setBackground(
				new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(10), Insets.EMPTY)));
		giftButton.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
		giftButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				if (arg0.getButton().equals(MouseButton.PRIMARY) && GameController.selectedInventoryCell != null
						&& GameController.selectedInventoryCell.getProduct() != null
						&& GameController.npcPane.getPlayerNPCStatus().getNpcList().get(orderOfNPC).getLevel() < 10) {
					giftHandler();
				}
			}
		});
	}

	public void giftHandler() {
		int oldAmt = GameController.selectedInventoryCell.getTotal();
		Human npc = GameController.npcPane.getPlayerNPCStatus().getNpcList().get(orderOfNPC);
		GameController.selectedInventoryCell.setTotal(oldAmt - amtSell);
		if (GameController.selectedInventoryCell.getProduct().getProductName().equals(npc.getLikeThing())) {
			npc.setCurrentExp(
					npc.getCurrentExp() + GameController.selectedInventoryCell.getProduct().getExp() * 2 * amtSell,
					orderOfNPC);
		} else {
			npc.setCurrentExp(
					npc.getCurrentExp() + GameController.selectedInventoryCell.getProduct().getExp() * amtSell,
					orderOfNPC);
		}
		GameController.selectedInventoryCell.setTotalText("" + GameController.selectedInventoryCell.getTotal());
		GameController.npcPane.getPlayerNPCStatus().setNpcStatus(npc, orderOfNPC);
		setAmtSell(0);
		setIncome(0);
		GameController.selectedInventoryCell.setUpTooltip();
		if (GameController.selectedInventoryCell.getTotal() == 0) {
			GameController.selectedInventoryCell.productIsEmpty();
			GameController.inventoryPane.getSellItem().productIsEmpty();
		}
	}

	public void setupInventoryPane() {
		inventoryText = new Text("Inventory");
		inventoryText.setFont(new Font(40));
		inventoryText.relocate(250, 1);
		this.setPrefWidth(700);
		this.setPrefHeight(500);
		this.setLayoutX(290);
		this.setLayoutY(70);
		this.setAmtSell(0);
		this.setIncome(0);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
	}

	public void setupSellItem() {
		sellItem = new InventoryCell();
		sellItem.relocate(530, 60);
	}

	public void setupAddButton() {
		Image addImg = new Image("AddButton.png");
		addImgView = new ImageView(addImg);
		addImgView.setFitWidth(25);
		addImgView.setFitHeight(25);
		addImgView.relocate(605, 170);
		addImgView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY) && GameController.selectedInventoryCell != null
						&& GameController.selectedInventoryCell.getProduct() != null
						&& GameController.selectedInventoryCell.getTotal() >= getAmtSell() + 1) {
					setAmtSell(getAmtSell() + 1);
					setIncome((int) ((GameController.selectedInventoryCell.getProduct().getIncome() * amtSell)
							* GameController.bonusSell));
				}
			}
		});
	}

	public void setupDeleteButton() {
		Image deleteImg = new Image("DeleteButton.png");
		deleteImgView = new ImageView(deleteImg);
		deleteImgView.setFitWidth(25);
		deleteImgView.setFitHeight(25);
		deleteImgView.relocate(527, 170);
		deleteImgView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY) && amtSell != 0
						&& GameController.selectedInventoryCell != null
						&& GameController.selectedInventoryCell.getProduct() != null) {
					setAmtSell(getAmtSell() - 1);
					setIncome((int) ((GameController.selectedInventoryCell.getProduct().getIncome() * amtSell)
							* GameController.bonusSell));
				}
			}
		});
	}

	public void setupAmtSellText() {
		amtSellText = new Text();
		setAmtSell(0);
		amtSellText.relocate(573.5, 172);
	}

	public void setupIncomeText() {
		incomeText = new Text();
		incomeText.relocate(630, 210);
		setIncome(0);
	}

	public void setupIncomeDescription() {
		incomeDescription = new Text("Income you will get: ");
		incomeDescription.relocate(520, 210);
	}

	public void setupNPC() {
		npcImage = new ImageView();
		npcImage.setFitHeight(96);
		npcImage.setFitHeight(120);
		npcImage.relocate(520, 300);
		npcName = new Text();
		npcName.setFont(new Font(14));
		orderOfNPC = 0;
		changeNPCImage();
	}

	public void changeNPCImage() {
		Image npcImage = new Image(GameController.npcPane.getPlayerNPCStatus().getNpcList().get(orderOfNPC).getUrl());
		this.npcImage.setImage(npcImage);
		npcName.setText(GameController.npcPane.getPlayerNPCStatus().getNpcList().get(orderOfNPC).getName());
		if (GameController.npcPane.getPlayerNPCStatus().getNpcList().get(orderOfNPC).getName().equals("Greengrocer")) {
			npcName.relocate(545, 430);
		} else if (GameController.npcPane.getPlayerNPCStatus().getNpcList().get(orderOfNPC).getName()
				.equals("Robbery")) {
			npcName.relocate(560, 430);
		} else {
			npcName.relocate(565, 430);
		}
	}

	public void setupAddNPCButton() {
		Image addImg = new Image("AddButton.png");
		addNPCImgView = new ImageView(addImg);
		addNPCImgView.setFitWidth(25);
		addNPCImgView.setFitHeight(25);
		addNPCImgView.relocate(630, 430);
		addNPCImgView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					orderOfNPC += 1;
					if (orderOfNPC > 5) {
						orderOfNPC = 0;
					}
					changeNPCImage();
				}
			}
		});
	}

	public void setupDeleteNPCButton() {
		Image deleteImg = new Image("DeleteButton.png");
		deleteNPCImgView = new ImageView(deleteImg);
		deleteNPCImgView.setFitWidth(25);
		deleteNPCImgView.setFitHeight(25);
		deleteNPCImgView.relocate(515, 430);
		deleteNPCImgView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					orderOfNPC -= 1;
					if (orderOfNPC < 0) {
						orderOfNPC = 5;
					}
					changeNPCImage();
				}
			}
		});
	}

	public void setupSellButton() {
		sellButton = new Button();
		sellButton.setText("Sell");
		sellButton.setTextFill(Color.BLACK);
		sellButton.relocate(559, 240);
		sellButton.setBackground(
				new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(10), Insets.EMPTY)));
		sellButton.setBorder(new Border(
				new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
		sellButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				if (arg0.getButton().equals(MouseButton.PRIMARY) && GameController.selectedInventoryCell != null
						&& GameController.selectedInventoryCell.getProduct() != null) {
					int oldAmt = GameController.selectedInventoryCell.getTotal();
					GameController.selectedInventoryCell.setTotal(oldAmt - amtSell);
					GameController.money += income * GameController.bonusSell;
					GameController.moneyPane.setMoneyText();
					GameController.selectedInventoryCell
							.setTotalText("" + GameController.selectedInventoryCell.getTotal());
					setAmtSell(0);
					setIncome(0);
					GameController.selectedInventoryCell.setUpTooltip();
					if (GameController.selectedInventoryCell.getTotal() == 0) {
						GameController.selectedInventoryCell.getTotalText().setVisible(false);
						GameController.selectedInventoryCell.productIsEmpty();
						GameController.inventoryPane.getSellItem().productIsEmpty();
						GameController.selectedInventoryCell = null;
					}
				}
			}
		});
	}

	public void addProduct(Product product, int total) throws InventoryFullException{
		for (int i = 0; i < total; i++) {
			Boolean isKeep = false;
			for (InventoryCell ic : inventoryCells) {
				if (ic.getProduct() != null) {
					if (product.getProductName().equals(ic.getProduct().getProductName())) {
						ic.setTotal(ic.getTotal() + 1);
						ic.setUpTooltip();
						isKeep = true;
						ic.setTotalText("" + ic.getTotal());
						break;
					}
				}
			}
			if (isKeep == true)
				continue;
			for (InventoryCell ic : inventoryCells) {
				if (ic.getProduct() == null) {
					ic.setProduct(product);
					ic.setTotal(1);
					Image productImage = new Image(product.getUrl());
					ic.setBackground(productImage);
					ic.setUpTooltip();
					ic.setTotalText("1");
					isKeep = true;
					break;
				}
			}
			if(!isKeep) {
				throw new InventoryFullException();
			}
		}
	}

	public int getAmtSell() {
		return amtSell;
	}

	public void setAmtSell(int amtSell) {
		this.amtSell = amtSell;
		this.amtSellText.setText("" + amtSell);
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
		this.incomeText.setText("" + income);
	}

	public InventoryCell getSellItem() {
		return sellItem;
	}

	public void setSellItem(InventoryCell sellItem) {
		this.sellItem = sellItem;
	}

	public ObservableList<InventoryCell> getInventoryCells() {
		return inventoryCells;
	}

	public void setInventoryCells(ObservableList<InventoryCell> inventoryCells) {
		this.inventoryCells = inventoryCells;
	}

	public void deleteProduct(Product product, int total) {
		for (int i = 1; i <= total; i++) {
			for (int j = inventoryCells.size() - 1; j >= 0; j--) {
				if(inventoryCells.get(j).getProduct()==null)	continue;
				if (inventoryCells.get(j).getProduct() != null
						&& inventoryCells.get(j).getProduct().getClass().equals(product.getClass())) {
					inventoryCells.get(j).setTotal(inventoryCells.get(j).getTotal() - 1);
					if (inventoryCells.get(j).getTotal() == 0) {
						inventoryCells.get(j).productIsEmpty();
					}
				}
			}
		}
	}

}

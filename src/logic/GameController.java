package logic;

import gui.HenHousePane;
import gui.HowToPlayPane;
import gui.ByrePane;
import gui.CloudThread;
import gui.CraftPane;
import livingthing.base.*;
import livingthing.*;
import gui.CropStorePane;
import java.util.ArrayList;
import java.util.Observable;
import combination.*;
import product.*;
import application.Main;
import building.Byre;
import building.Henhouse;
import livingthing.Banker;
import livingthing.Grazier;
import livingthing.Greengrocer;
import livingthing.Guard;
import livingthing.Robbery;
import livingthing.Wizard;
import gui.InventoryPane;
import gui.LosePane;
import gui.MenuButton;
import gui.MoneyPane;
import gui.NPCPane;
import gui.QuestPane;
import gui.SeedInventory;
import gui.SeedInventoryCell;
import gui.SoilCell;
import gui.WinPane;
import gui.YourStealingPane;
import gui.StealPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import product.base.*;
import gui.InventoryCell;
import gui.AnimalStorePane;
import gui.ByrePane;

public class GameController {
	public static Main main;
	public static int time;
	public static int day;
	public static boolean isWin;
	public static boolean isLose;
	public static boolean isSpecialGuard;
	public static boolean stealAlready;
	public static int money ;
	public static int billing ;
	public static double bonusBuyAnimal ;
	public static double bonusBuyCrop ;
	public static double bonusSell;
	public static int bonusNumberOfLand;
	public static int bonusHaveChicken;
	public static int bonusHaveCow ;
	public static double bonusGrowTimeCrop ;
	public static double bonusGrowTimeAnimal ;
	public static double bonusTimeCollectItemAnimal ;
	public static int bonusCollectedItemCrop;
	public static int bonusCollectedItemAnimal;
	public static int bonusMoneyRange;
	public static double protectYourMoney;
	public static int protectChance;
	public static double bonusQuestMoneyReward ;
	public static double bonusQuestEXPReward ;
	public static int stealChance;
	public static double allowance;
	public static int stealDay;
	public static int taxDay;
	public static InventoryCell selectedInventoryCell;
	public static InventoryCell sellItem;
	public static AnimalStorePane animalStorePane;
	public static CropStorePane cropStorePane;
	public static ByrePane byrePane;
	public static CraftPane craftPane;
	public static HenHousePane henHousePane;
	public static NPCPane npcPane;
	public static MenuButton selectedButton;
	public static InventoryPane inventoryPane;
	public static QuestPane questPane;
	public static int levelQuest;
	public static ArrayList<Human> npcListForQuest;
	public static GameMap gameMap;
	public static AnchorPane root;
	public static MoneyPane moneyPane;
	public static StealPane stealPane;
	public static Byre byre;
	public static Henhouse henhouse;
	public static SeedInventory seedInventory;
	public static SeedInventoryCell selectedSeedCell;
	public static ArrayList<Updateable> updateList;
	public static Canvas canvasPress;
	public static int questDone = 0;
	public static Boolean canBuyStarfruit;
	public static Boolean canBuyFestivalAnimal;
	public static Stage stage;
	public static Scene startScene;
	public static LosePane losePane;
	public static WinPane winPane;
	public static HowToPlayPane howToPlayPane;
	public static YourStealingPane yourStealingPane;
	public static Thread timerThread;
	public static AudioClip soundNight;
	public static AudioClip soundDay;
	public static AudioClip menuSound;
	public static AudioClip soundLose;
	public static Boolean isDay;
	public static Boolean keepTax;
	public static int timeUsed;
	private final static int winCondition=100000; 
	public static void initializeMap() {
		gameMap = new GameMap();
	}

	public static void initializeGameController() {
		keepTax=false;
		isDay=true;
		canBuyStarfruit = false;
		canBuyFestivalAnimal = false;
		levelQuest = 1;
		money = 3000;
		time=120;
		day=1;
		isWin = false;
		isLose = false;
		isSpecialGuard = false;
		stealAlready = false;
		billing = 6000;
		bonusBuyAnimal = 1;
		bonusBuyCrop = 1;
		bonusSell = 1;
		bonusNumberOfLand = 0;
		bonusHaveChicken = 0;
		bonusHaveCow = 0;
		bonusGrowTimeCrop = 1;
		bonusGrowTimeAnimal = 1;
		bonusTimeCollectItemAnimal = 1;
		bonusCollectedItemCrop = 1;
		bonusCollectedItemAnimal = 1;
		bonusMoneyRange = 1000;
		protectYourMoney = 1;
		protectChance = 0;
		bonusQuestMoneyReward = 1;
		bonusQuestEXPReward = 1;
		stealChance = 0;
		allowance = 1;
		taxDay = 7;
		timeUsed = 0;
	}

	public static void randomStealDay() {
		stealDay = (int) ((Math.random() * 5) + 2);
	}

	public static void checkRobberSteal() {
		int random = (int) ((Math.random() * 100) + 1);

		if (random > protectChance) {
			GameController.stealPane.setMoneyYoulost(
					"Your money is stolen by robber\n" + (int) ((0.5 * money) * protectYourMoney) + "$");
			money -= (int) ((0.5 * money) * protectYourMoney);
			GameController.stealPane.setVisible(true);
		}
		System.out.println(money);
		stealAlready = true;
	}
	public static void howToPlayHandle() {
		howToPlayPane.setVisible(true);
	}
	public static void createNPC(ArrayList<Human> npcList) {
		Banker banker = new Banker();
		npcList.add(banker);
		Grazier grazier = new Grazier();
		npcList.add(grazier);
		Guard guard = new Guard();
		npcList.add(guard);
		Robbery robbery = new Robbery();
		npcList.add(robbery);
		Greengrocer greengrocer = new Greengrocer();
		npcList.add(greengrocer);
		Wizard wizard = new Wizard();
		npcList.add(wizard);
	}

	public static boolean checkLoseCondition() {
		if (money >= (int) (billing * GameController.allowance)) {
			money -= (int) (billing * GameController.allowance);
			billing += 1000;
			GameController.moneyPane.setMoneyText();
			GameController.keepTax=true;
		} else {
			isLose = true;
			soundDay.stop();
			soundNight.stop();
			soundLose=new AudioClip(ClassLoader.getSystemResource("LoseSound.wav").toString());
			playSoundOneTime(soundLose);
			canvasPress.setVisible(true);
			canvasPress.setScaleY(1.5);
		}
		return isLose;
	}

	public static boolean checkWinCondition() {
		if (money >= winCondition) {
			isWin = true;
			GameController.canvasPress.setVisible(true);
			GameController.canvasPress.setScaleY(1.5);
			GameController.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("WinSound.mp3").toString()));
			return isWin;
		}
		return false;
	}

	public static void getMoneyFromRobbery() {
		int random = (int) ((Math.random() * 100) + 1);
		if (stealChance >= random) {
			int increase=(int) ((Math.random() * GameController.bonusMoneyRange) + 1);
			money += increase;
			GameController.moneyPane.setMoneyText();
			GameController.yourStealingPane.setVisible(true);
			GameController.yourStealingPane.setMoneyYouGet("You get "+increase+"$ from your robber");
		}
	}

	public static void showPanel() {

		if (selectedButton.getButtonName() == "NPC") {
			npcPane.setVisible(true);
		} else if (selectedButton.getButtonName() == "Inventory") {
			inventoryPane.setVisible(true);
		} else if (selectedButton.getButtonName() == "Quest") {
			questPane.setVisible(true);
		} else if (selectedButton.getButtonName() == "Animal") {
			animalStorePane.setVisible(true);
		} else if (selectedButton.getButtonName() == "Crop") {
			cropStorePane.setVisible(true);
		}
	}

	public static void hidePanel() {
		npcPane.setVisible(false);
		inventoryPane.setVisible(false);
		questPane.setVisible(false);
		animalStorePane.setVisible(false);
		cropStorePane.setVisible(false);
	}

	public static void generateNPCForQuest() {
		npcListForQuest = new ArrayList<Human>();
		npcListForQuest.add(new Banker());
		npcListForQuest.add(new Grazier());
		npcListForQuest.add(new Guard());
		npcListForQuest.add(new Robbery());
		npcListForQuest.add(new Greengrocer());
		npcListForQuest.add(new Wizard());
	}

	public static Human randomNPCForQuest() {
		int random = (int) (Math.random() * npcListForQuest.size());
		return npcListForQuest.get(random);
	}

	public static void deletedNPCFromList(String npcName) {
		for (int i = 0; i < npcListForQuest.size(); i++) {
			if (npcListForQuest.get(i).getName().equals(npcName)) {
				npcListForQuest.remove(i);
				break;
			}
		}
	}

	public static Product randomProductForQuest() {
		int random = (int) ((Math.random() * 15) + 1);
		Product randomProductName = null;
		switch (random) {
		case 1:
			randomProductName = new Blueberry();
			break;
		case 2:
			randomProductName = new Cabbage();
			break;
		case 3:
			randomProductName = new ChickenMeat();
			break;
		case 4:
			randomProductName = new Corn();
			break;
		case 5:
			randomProductName = new CowMeat();
			break;
		case 6:
			randomProductName = new Egg();
			break;
		case 7:
			randomProductName = new Melon();
			break;
		case 8:
			randomProductName = new Milk();
			break;
		case 9:
			randomProductName = new Pumpkin();
			break;
		case 10:
			randomProductName = new Tomato();
			break;
		case 11:
			randomProductName = new Breakfast();
			break;
		case 12:
			randomProductName = new Coleslaw();
			break;
		case 13:
			randomProductName = new FriedEgg();
			break;
		case 14:
			randomProductName = new LuckyLunch();
			break;
		case 15:
			randomProductName = new Mayonaise();
			break;
		default:
			break;
		}
		return randomProductName;
	}

	public GameMap getGameMap() {
		return gameMap;
	}

	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	public static void playSoundLoop(AudioClip sound) {
		sound.setCycleCount(AudioClip.INDEFINITE);
		sound.play();
	}
	public static void playSoundOneTime(AudioClip sound) {
		sound.play();
	}
}

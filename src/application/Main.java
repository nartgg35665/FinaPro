package application;

import java.util.ArrayList;
import gui.HenHousePane;
import gui.HowToPlayPane;

import java.util.List;
import java.util.Optional;

import livingthing.*;

import javax.swing.GroupLayout.Alignment;

import gui.InventoryPane;
import gui.LosePane;
import gui.MenuButton;
import gui.MenuPane;
import gui.MoneyPane;
import gui.NPCPane;
import gui.QuestPane;
import gui.SeedInventory;
import gui.StealPane;
import gui.WinPane;
import gui.YourStealingPane;
import gui.CropStorePane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import livingthing.base.Human;
import livingthing.base.Updateable;
import logic.*;
import gui.AnimalStorePane;
import gui.ByrePane;
import gui.CloudThread;
import gui.CraftPane;

public class Main extends Application {
	private MenuPane menuPane;
	private int currentTime;
	private Canvas canvas;
	private Thread timerThread;
	private AnchorPane root;
	private AnchorPane menuRoot;
	private ImageView viewBackground;
	private Button retryButton;
	private AudioClip soundDay;
	private AudioClip soundNight;
	private AudioClip menuSound;

	private Scene gameStart(Stage primaryStage) {
		// TODO Auto-generated method stub
		GameController.updateList = new ArrayList<Updateable>();
		GameController.main = this;
		setAudioForGame();
		root = new AnchorPane();
		GameController.root = root;
		generateDayBackground();
		canvas = new Canvas(1280, 720);
		canvas.setLayoutX(0);
		canvas.setLayoutY(0);
		root.getChildren().add(canvas);
		timerHandle();
		setButtonRetryForGame();
		GameController.initializeMap();
		generatePane();
		GameController.randomStealDay();
		root.getChildren().addAll(this.menuPane, GameController.seedInventory, GameController.canvasPress,
				GameController.npcPane, GameController.inventoryPane, GameController.questPane, GameController.byrePane,
				GameController.moneyPane, GameController.henHousePane, GameController.craftPane, retryButton);
		generateCropStorePane();
		generateAnimalStorePane();
		root.getChildren().addAll(GameController.losePane, GameController.winPane, GameController.stealPane,
				GameController.yourStealingPane);
		Scene scene = new Scene(root, 1280, 720);
		return scene;
	}

	private void setButtonRetryForGame() {
		BackgroundSize bgSize = new BackgroundSize(100, 50, false, false, false, false);
		BackgroundImage buttonImg = new BackgroundImage(new Image("MenuButton.png"), null, null, null, bgSize);
		retryButton = new Button("Retry");
		retryButton.setPrefSize(100, 50);
		retryButton.relocate(10, 10);
		retryButton.setBackground(new Background(buttonImg));
		retryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				Alert retryAlert = new Alert(AlertType.CONFIRMATION);
				retryAlert.setTitle("retry");
				retryAlert.setHeaderText(null);
				retryAlert.setContentText("Do you want to retry?");
				GameController
						.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
				Optional<ButtonType> option = retryAlert.showAndWait();
				if (option.get() == ButtonType.OK) {
					GameController.timerThread.stop();
					soundDay.stop();
					soundNight.stop();
					GameController.stage.setScene(createMainMenuScene());
				}
			}
		});
		retryButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				setRetryButtonWhenPressed();
			}
		});
		retryButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				setRetryButtonWhenReleased();
			}
		});
	}

	public void setAudioForGame() {
		soundDay = new AudioClip(ClassLoader.getSystemResource("DaySound.mp3").toString());
		soundNight = new AudioClip(ClassLoader.getSystemResource("NightSound.mp3").toString());
		GameController.soundDay = soundDay;
		GameController.soundNight = soundNight;
		GameController.playSoundLoop(soundDay);
	}

	private void timerHandle() {
		this.currentTime = GameController.time;
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawCurrentTimeDayString(gc);
		this.timerThread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
					currentTime -= 1;
					GameController.timeUsed += 1;
					for (int i = 0; i < GameController.updateList.size(); i++) {
						GameController.updateList.get(i).update();
					}

					if (GameController.day % GameController.taxDay == 0 && !GameController.keepTax) {
						if (GameController.checkLoseCondition()) {
							GameController.losePane.setVisible(true);
							break;
						}
					}
					if (GameController.checkWinCondition()) {
						GameController.winPane.setTimeUsed(GameController.timeUsed);
						GameController.winPane.setVisible(true);
						break;
					}
					if (this.currentTime < 0) {
						if (GameController.day % 7 == 0) {
							GameController.randomStealDay();
							GameController.stealAlready = false;
						}
						if (!GameController.isDay) {
							soundNight.stop();
							GameController.playSoundLoop(soundDay);
						}
						GameController.isDay = true;
						GameController.getMoneyFromRobbery();
						viewBackground.setVisible(true);
						currentTime = GameController.time;
						GameController.day += 1;
						if (GameController.day % GameController.taxDay == 1) {
							GameController.keepTax = false;
						}
					} else if (this.currentTime <= 60) {
						if (GameController.stealDay == GameController.day % 7 && GameController.stealAlready == false
								&& GameController.isSpecialGuard == false) {
							GameController.checkRobberSteal();
							GameController.moneyPane.setMoneyText();
						}
						generateNightBackgound();
						if (GameController.isDay) {
							soundDay.stop();
							GameController.playSoundLoop(soundNight);
						}
						GameController.isDay = false;
						viewBackground.setVisible(false);
					}
					drawCurrentTimeDayString(gc);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Stop Timer Thread");
					break;
				}
			}
		});
		GameController.timerThread = timerThread;
		this.timerThread.start();
	}

	public void start(Stage primaryStage) {
		Scene menuScene = createMainMenuScene();
		GameController.stage = primaryStage;
		primaryStage.setScene(menuScene);
		primaryStage.setTitle("Harvest Business");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private Scene createMainMenuScene() {
		menuSound = new AudioClip(ClassLoader.getSystemResource("MenuSound.mp3").toString());
		GameController.menuSound = menuSound;
		GameController.playSoundLoop(menuSound);
		menuRoot = new AnchorPane();
		Scene menuScene = new Scene(menuRoot, 1280, 720);
		Text gameName = new Text("Harvest Business");
		gameName.setFont(new Font(100));
		gameName.relocate(290, 200);
		setButtonForMenu();
		GameController.howToPlayPane = new HowToPlayPane();
		GameController.howToPlayPane.setVisible(false);
		BackgroundSize bgSize = new BackgroundSize(1280, 720, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(new Image("BackgroundImage.png"), null, null, null, bgSize);
		menuRoot.setBackground(new Background(bgImg));
		CloudThread cloudThread1 = new CloudThread();
		CloudThread cloudThread2 = new CloudThread();
		menuRoot.getChildren().addAll(gameName, cloudThread1.getCloud(), GameController.howToPlayPane);
		GameController.startScene = menuScene;
		return menuScene;
	}

	private void setButtonForMenu() {
		Button startButton = new Button("Start");
		startButton.relocate(590, 400);
		startButton.setPrefSize(100, 50);
		startButton.setFont(new Font(20));
		startButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(100), Insets.EMPTY)));
		startButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				startButton.setBackground(
						new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(100), Insets.EMPTY)));
			}
		});
		startButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				startButton.setBackground(
						new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(100), Insets.EMPTY)));
			}
		});
		Button HowToButton = new Button("How to play");
		HowToButton.relocate(580, 450);
		HowToButton.setFont(new Font(20));
		HowToButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(100), Insets.EMPTY)));
		HowToButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				HowToButton.setBackground(
						new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(100), Insets.EMPTY)));
			}
		});
		HowToButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				HowToButton.setBackground(
						new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(100), Insets.EMPTY)));
			}
		});
		Button Exit = new Button("Exit");
		Exit.relocate(610, 500);
		Exit.setFont(new Font(20));
		Exit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(100), Insets.EMPTY)));
		Exit.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				Exit.setBackground(
						new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(100), Insets.EMPTY)));
			}
		});
		Exit.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				Exit.setBackground(
						new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(100), Insets.EMPTY)));
			}
		});
		startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				GameController.initializeGameController();
				menuSound.stop();
				GameController
						.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
				GameController.stage.setScene(gameStart(GameController.stage));
			}
		});
		HowToButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				GameController
						.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
				GameController.howToPlayHandle();
			}
		});
		Exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				GameController
						.playSoundOneTime(new AudioClip(ClassLoader.getSystemResource("ClickSound.wav").toString()));
				menuSound.stop();
				GameController.stage.close();
			}
		});
		menuRoot.getChildren().addAll(startButton, HowToButton, Exit);
	}

	public void setMenuPane(MenuPane menuPane) {
		this.menuPane = menuPane;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void drawCurrentTimeDayString(GraphicsContext gc) {

		if (GameController.isDay == true) {
			gc.setFill(Color.BLACK);
		} else {
			gc.setFill(Color.YELLOW);
		}
		gc.setFont(new Font(20));
		gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		gc.fillText("" + this.currentTime, this.canvas.getWidth() / 2 - 12, 25);
		gc.fillText("DAY : " + GameController.day, this.canvas.getWidth() - 90, 25);
		gc.fillText("PAY TAX EVERY " + GameController.taxDay + " DAY: "
				+ (int) (GameController.billing * GameController.allowance), this.canvas.getWidth() - 280, 60);

	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	private void generatePane() {
		setMenuPane(new MenuPane());
		GameController.canvasPress = new Canvas(1280, 650);
		GameController.canvasPress.setVisible(false);
		GraphicsContext gc2 = GameController.canvasPress.getGraphicsContext2D();
		gc2.setFill(Color.rgb(200, 200, 200, 0.5));
		gc2.fillRect(0, 0, GameController.canvasPress.getWidth(), GameController.canvasPress.getHeight());
		GameController.seedInventory = new SeedInventory();
		GameController.moneyPane = new MoneyPane();
		GameController.npcPane = new NPCPane();
		GameController.npcPane.setVisible(false);
		GameController.inventoryPane = new InventoryPane();
		GameController.inventoryPane.setVisible(false);
		GameController.generateNPCForQuest();
		GameController.questPane = new QuestPane();
		GameController.questPane.setVisible(false);
		GameController.byrePane = new ByrePane();
		GameController.byrePane.setVisible(false);
		GameController.henHousePane = new HenHousePane();
		GameController.henHousePane.setVisible(false);
		GameController.craftPane = new CraftPane();
		GameController.craftPane.setVisible(false);
		GameController.losePane = new LosePane();
		GameController.losePane.setVisible(false);
		GameController.winPane = new WinPane();
		GameController.winPane.setVisible(false);
		GameController.stealPane = new StealPane();
		GameController.stealPane.setVisible(false);
		GameController.yourStealingPane = new YourStealingPane();
		GameController.yourStealingPane.setVisible(false);
	}

	public void generateAnimalStorePane() {
		GameController.animalStorePane = new AnimalStorePane();
		GameController.animalStorePane.setVisible(false);
		root.getChildren().add(GameController.animalStorePane);
	}

	public void generateCropStorePane() {
		GameController.cropStorePane = new CropStorePane();
		GameController.cropStorePane.setVisible(false);
		root.getChildren().add(GameController.cropStorePane);
	}

	private void generateDayBackground() {
		Image backgroundImg = new Image("BackgroundImage.png");
		viewBackground = new ImageView(backgroundImg);
		viewBackground.setLayoutY(-300);
		viewBackground.setFitHeight(750);
		viewBackground.setFitWidth(1280);
		GameController.root.getChildren().add(viewBackground);
	}

	private void generateNightBackgound() {
		viewBackground.setVisible(false);
		GameController.root
				.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void setRetryButtonWhenPressed() {
		BackgroundSize bgSize = new BackgroundSize(100, 50, false, false, false, false);
		BackgroundImage buttonImg = new BackgroundImage(new Image("MenuButtonPressed.png"), null, null, null, bgSize);
		retryButton.setBackground(new Background(buttonImg));
	}

	private void setRetryButtonWhenReleased() {
		BackgroundSize bgSize = new BackgroundSize(100, 50, false, false, false, false);
		BackgroundImage buttonImg = new BackgroundImage(new Image("MenuButton.png"), null, null, null, bgSize);
		retryButton.setBackground(new Background(buttonImg));
	}
}

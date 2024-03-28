package edu.ntnu.idatt2001.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This class is responsible for the layout of the page where the game is played.
 */
public class PlayingGameView {
  private static Scene startingGameScene;
  private Button quitButton;
  private Alert alert;
  private Image backgroundImage;
  private HBox bottomBar;
  private HBox pointsHbox;
  private ImageView characterImage;
  private VBox storyVbox;
  private VBox storyContentVbox;
  private HBox choiceButtonsHbox;
  private Text healthPointsText;
  private Text goldPointsText;
  private Text scorePointsText;
  private Text inventoryText;
  private Label storyText;
  private ImageView healthIcon;
  private ImageView goldIcon;
  private ImageView scoreIcon;
  private ImageView inventoryIcon;
  private VBox startGameVbox;
  private Button yesButton;
  private Label headingLabel;
  private Button resultButton;

  /**
   * Constructor for the class PlayingGameView.
   *
   */
  public PlayingGameView() {
    initializeComponents();
    layoutComponents();
  }

  /**
   * Private help method responsible for initializing the components of the scene.
   */
  private void initializeComponents() {

    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(1300);
    backgroundImageView.setFitHeight(750);
    backgroundImageView.setSmooth(true);
    backgroundImageView.setPreserveRatio(true);
    backgroundImageView.setCache(true);

    characterImage = new ImageView();
    characterImage.setFitHeight(550);
    characterImage.setFitWidth(350);

    SelectPlayerView selectPlayerView = new SelectPlayerView();
    headingLabel = new Label("Now the journey begins! "
        + selectPlayerView.setPlayer().getName() + ", are you ready?");
    headingLabel.getStyleClass().add("headingGame");
    headingLabel.setWrapText(true);

    yesButton = new Button("YES!");
    yesButton.getStyleClass().addAll("storyChoiceButtons", "storyChoiceButtons:hover");

    quitButton = new Button("Quit");
    quitButton.getStyleClass().addAll("uploadGameButtons", "uploadGameButtons:hover");

    alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm that you want to go back to the home page");
    alert.setHeaderText("Are you sure you want to quit the game and go back to the home page?");
    alert.setContentText("You will lose all progress");

    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add("edu.ntnu.idatt2001/styles.css");
    dialogPane.getStyleClass().add("alert-dialog");

    healthPointsText = new Text("");
    healthPointsText.getStyleClass().add("pointsText");

    goldPointsText = new Text("");
    goldPointsText.getStyleClass().add("pointsText");

    scorePointsText = new Text("");
    scorePointsText.getStyleClass().add("pointsText");

    inventoryText = new Text("");
    inventoryText.getStyleClass().add("itemAction");

    storyText = new Label();
    storyText.getStyleClass().add("instructions");
    storyText.setWrapText(true);

    Image healthIconPath = new Image("file:src/main/resources"
        + "/edu.ntnu.idatt2001/dataFiles/healthIcon.png");
    healthIcon = new ImageView(healthIconPath);
    healthIcon.setFitHeight(80);
    healthIcon.setFitWidth(80);

    Image goldIconPath = new Image(
        "file:src/main/resources/edu.ntnu.idatt2001/dataFiles/goldIcon.png");
    goldIcon = new ImageView(goldIconPath);
    goldIcon.setFitHeight(80);
    goldIcon.setFitWidth(80);

    Image scoreIconPath = new Image(
        "file:src/main/resources/edu.ntnu.idatt2001/dataFiles/scoreIcon.png");
    scoreIcon = new ImageView(scoreIconPath);
    scoreIcon.setFitHeight(80);
    scoreIcon.setFitWidth(80);

    Image inventoryIconPath = new Image("file:src/main/resources"
        + "/edu.ntnu.idatt2001/dataFiles/inventoryIcon.png");
    inventoryIcon = new ImageView(inventoryIconPath);
    inventoryIcon.setFitHeight(80);
    inventoryIcon.setFitWidth(80);

    storyContentVbox = new VBox();

    pointsHbox = new HBox();

    resultButton = new Button("Reveal your results!");
    resultButton.getStyleClass().addAll("storyChoiceButtons", "storyChoiceButtons:hover");
  }

  /**
   * Private help method responsible for laying out the components of the scene.
   */
  private void layoutComponents() {
    StackPane root = new StackPane();
    backgroundImage = setBackgroundGame();

    BackgroundImage background = new BackgroundImage(backgroundImage,
        null, null, null,
        new BackgroundSize(1300, 750, false, false, false, false));
    root.setBackground(new Background(background));

    characterImage = setCharacterImage();

    pointsHbox = new HBox();
    pointsHbox.getStyleClass().addAll("instructions", "whiteBackground");
    pointsHbox.setPrefWidth(1000);
    pointsHbox.setPrefHeight(0);
    pointsHbox.getChildren().addAll(healthIcon, healthPointsText, goldIcon,
        goldPointsText, scoreIcon, scorePointsText, inventoryIcon, inventoryText);
    pointsHbox.setSpacing(20);
    pointsHbox.setAlignment(Pos.CENTER_LEFT);
    HBox.setMargin(healthIcon, new Insets(0, 0, 0, 20));

    startGameVbox = new VBox();
    startGameVbox.getChildren().addAll(headingLabel, yesButton);
    startGameVbox.setAlignment(Pos.CENTER);
    VBox.setMargin(headingLabel, new Insets(0, 0, 0, 50));
    startGameVbox.setSpacing(20);
    startGameVbox.setPrefWidth(700);
    startGameVbox.setPrefHeight(500);
    startGameVbox.getStyleClass().add("vBoxRounded");

    HBox topBar = new HBox();
    topBar.getChildren().addAll(pointsHbox, quitButton);
    HBox.setMargin(pointsHbox, new Insets(0, 0, 0, 20));
    pointsHbox.getStyleClass().add("vBoxRounded");
    topBar.setPadding(new Insets(20, 20, 20, 20));
    topBar.setSpacing(100);
    topBar.fillHeightProperty().setValue(true);

    choiceButtonsHbox = new HBox();
    choiceButtonsHbox.setPadding(new Insets(20, 20, 20, 20));
    choiceButtonsHbox.setSpacing(40);
    choiceButtonsHbox.fillHeightProperty().setValue(true);
    choiceButtonsHbox.setAlignment(Pos.CENTER);

    storyVbox = new VBox();
    storyContentVbox.getStyleClass().addAll("instructions", "whiteBackground");
    storyContentVbox.setPrefWidth(700);
    storyContentVbox.setPrefHeight(500);
    storyContentVbox.getStyleClass().add("vBoxRounded");
    storyContentVbox.getChildren().addAll(storyText);
    VBox.setMargin(storyText, new Insets(40, 40, 30, 40));
    storyVbox.getStyleClass().add("vBoxRounded");
    storyVbox.getChildren().addAll(storyContentVbox, choiceButtonsHbox);
    storyVbox.setAlignment(Pos.CENTER);

    bottomBar = new HBox();
    bottomBar.getChildren().addAll(characterImage, startGameVbox);
    HBox.setMargin(storyVbox, new Insets(0, 20, 20, 0));
    bottomBar.setSpacing(150);
    bottomBar.fillHeightProperty().setValue(true);

    VBox gameScene = new VBox();
    gameScene.getChildren().addAll(topBar, bottomBar);
    gameScene.setSpacing(50);

    root.getChildren().addAll(gameScene);

    startingGameScene = new Scene(root, 1300, 750);
  }

  /**
   * Method for reading from file.
   *
   * @param file (File)
   * @return line (String)
   * @throws RuntimeException if file is not found
   */
  public String readFromFile(File file) {
    try (Scanner scanner = new Scanner(file)) {
      if (scanner.hasNext()) {
        String line = scanner.next();
        scanner.close();
        return line;
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  /**
   * Method for setting the background image, depending on the location chosen.
   *
   * @return backgroundImage (Image)
   */
  public Image setBackgroundGame() {
    File file = new File("src/main/resources/edu.ntnu.idatt2001/gameData/selectedLocation");

    if (Objects.equals(readFromFile(file), "Sea")) {
      return new Image("file:src/main/resources/edu.ntnu.idatt2001/dataFiles/Under_the_sea.jpg");
    } else if (Objects.equals(readFromFile(file), "Forest")) {
      return new Image("file:src/main/resources/edu.ntnu.idatt2001/dataFiles/in_the_forest.jpg");
    } else if (Objects.equals(readFromFile(file), "House")) {
      return new Image("file:src/main/resources/edu.ntnu.idatt2001/dataFiles/Haunted_house.jpg");
    } else if (Objects.equals(readFromFile(file), "Space")) {
      return new Image("file:src/main/resources/edu.ntnu.idatt2001/dataFiles/Space.jpg");
    } else {
      throw new RuntimeException("No background image found.");
    }
  }

  /**
   * Method for setting the character image, depending on the character chosen.
   *
   * @return characterImage (ImageView)
   * @throws RuntimeException "No character image found."
   */
  public ImageView setCharacterImage() {
    File file = new File("src/main/resources/edu.ntnu.idatt2001/gameData/selectedCharacter");

    if (Objects.equals(readFromFile(file), "Toppy")) {
      return new ImageView("file:src/main/resources/edu.ntnu.idatt2001/dataFiles/green_troll.png");
    } else if (Objects.equals(readFromFile(file), "Poppy")) {
      return new ImageView("file:src/main/resources/edu.ntnu.idatt2001/dataFiles/pink_troll.png");
    } else {
      throw new RuntimeException("No character image found.");
    }
  }


  /**
   * Method for accessing the starting game scene.
   *
   * @return starting game scene (Scene)
   */
  public static Scene getStartingGameScene() {
    return startingGameScene;
  }

  /**
   * Method for accessing the quit button.
   *
   * @return quit button (Button)
   */
  public Button getQuitButton() {
    return quitButton;
  }

  /**
   * Method for accessing the points text area.
   *
   * @return points text area (TextArea)
   */
  public HBox getBottomBar() {
    return bottomBar;
  }

  /**
   * Method for accessing the VBox storing the story and the choice buttons.
   *
   * @return storyVBox (VBox)
   */
  public VBox getStoryVbox() {
    return storyVbox;
  }

  /**
   * Method for accessing the HBox storing the choice buttons.
   *
   * @return choiceButtonsHBox (HBox)
   */
  public HBox getChoiceButtonsHbox() {
    return choiceButtonsHbox;
  }

  /**
   * Method for accessing the alert.
   *
   * @return alert (Alert)
   */
  public Alert getAlert() {
    return alert;
  }

  /**
   * Method for accessing the health points text.
   *
   * @return healthPointsText (Text)
   */
  public Text getHealthPointsText() {
    return healthPointsText;
  }

  /**
   * Method for accessing the story text.
   *
   * @return storyText (Label)
   */
  public Label getStoryText() {
    return storyText;
  }


  /**
   * Method for accessing the score icon image.
   *
   * @return scoreIcon (ImageView)
   */
  public ImageView getScoreIcon() {
    return scoreIcon;
  }

  /**
   * Method for accessing the inventory icon image.
   *
   * @return inventoryIcon (ImageView)
   */
  public ImageView getInventoryIcon() {
    return inventoryIcon;
  }

  /**
   * Method for accessing the gold points text.
   *
   * @return goldPointsText (Text)
   */
  public Text getGoldPointsText() {
    return goldPointsText;
  }

  /**
   * Method for accessing the score points text.
   *
   * @return scorePointsText (Text)
   */
  public Text getScorePointsText() {
    return scorePointsText;
  }

  /**
   * Method for accessing the inventory text.
   *
   * @return inventoryText (Text)
   */
  public Text getInventoryText() {
    return inventoryText;
  }

  /**
   * Method for accessing the start game Vbox.
   *
   * @return startGameVbox (VBox)
   */
  public VBox getStartGameVbox() {
    return startGameVbox;
  }

  /**
   * Method for accessing the yes button.
   *
   * @return yesButton (Button)
   */
  public Button getYesButton() {
    return yesButton;
  }

  /**
   * Method for accessing the result button.
   *
   * @return resultButton (Button)
   */
  public Button getResultButton() {
    return resultButton;
  }
}

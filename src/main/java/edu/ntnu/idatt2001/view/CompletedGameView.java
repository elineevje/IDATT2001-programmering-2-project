package edu.ntnu.idatt2001.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**
 * Class representing the view for the completed game scene.
 */
public class CompletedGameView {
  private static Scene completedGameScene;
  private Label winnerText;
  private Label winningStatsText;
  private Label goldGoalFulfilled;
  private Label healthGoalFulfilled;
  private Label scoreGoalFulfilled;
  private Label inventoryGoalFulfilled;
  private Button homeButton;
  private Alert alert;
  private ImageView podium;
  private ImageView confetti;
  private ImageView healthImage;
  private ImageView goldImage;
  private ImageView scoreImage;
  private ImageView inventoryImage;
  private Image backgroundImage;
  private ImageView characterImage;
  private MediaPlayer fanfarePlayer;
  private HBox winningStatsHbox;

  /**
   * Constructor for the completed game view.
   */
  public CompletedGameView() {
    initializeComponents();
    goalIsFulfilledStatus();
    layoutComponents();
  }

  /**
   * Method for initializing the components of the completed game view.
   */
  private void initializeComponents() {

    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(1300);
    backgroundImageView.setFitHeight(750);
    backgroundImageView.setSmooth(true);
    backgroundImageView.setCache(true);
    backgroundImageView.setPreserveRatio(true);

    characterImage = new ImageView();
    characterImage.setFitHeight(550);
    characterImage.setFitWidth(350);

    SelectPlayerView selectPlayerView = new SelectPlayerView();
    winnerText = new Label("Congratulations, "
        + selectPlayerView.setPlayer().getName() + "!" + "\n" + "You completed the path!");
    winnerText.getStyleClass().add("winnerTextBig");

    goldGoalFulfilled = new Label("Gold goal");
    healthGoalFulfilled = new Label("Health goal");
    scoreGoalFulfilled = new Label("Score goal");
    inventoryGoalFulfilled = new Label("Inventory goal");
    winningStatsText = new Label("""
            Here are all the goals you sat\s
            in the beginning of the game.\s
               The ones that are fulfilled
             are marked green.""");

    winningStatsText.getStyleClass().add("winnerTextSmall");

    Image pathPodium = new Image("file:src/main/resources"
        + "/edu.ntnu.idatt2001/dataFiles/podium.png");
    podium = new ImageView(pathPodium);

    Image pathConfetti = new Image("file:src/main/resources"
        + "/edu.ntnu.idatt2001/dataFiles/confetti.png");
    confetti = new ImageView(pathConfetti);

    Image pathHealth = new Image("file:src/main/resources"
        + "/edu.ntnu.idatt2001/dataFiles/healthIcon.png");
    healthImage = new ImageView(pathHealth);

    Image pathGold = new Image("file:src/main/resources"
        + "/edu.ntnu.idatt2001/dataFiles/goldIcon.png");
    goldImage = new ImageView(pathGold);

    Image pathScore = new Image("file:src/main/resources"
        + "/edu.ntnu.idatt2001/dataFiles/scoreIcon.png");
    scoreImage = new ImageView(pathScore);

    Image pathInventory = new Image("file:src/main/resources"
        + "/edu.ntnu.idatt2001/dataFiles/inventoryIcon.png");
    inventoryImage = new ImageView(pathInventory);

    homeButton = new Button("Homepage");
    homeButton.getStyleClass().add("buttons");

    alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm that you want to go back to the home page");
    alert.setHeaderText("Are you sure you want to go back to the home page?");
    alert.setContentText("You will lose all progress");

    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add("edu.ntnu.idatt2001/styles.css");
    dialogPane.getStyleClass().add("alert-dialog");

    String pathFanfare = "src/main/resources/edu.ntnu.idatt2001/soundFiles/fanfare.mp3";
    Media fanfare = new Media(new File(pathFanfare).toURI().toString());
    fanfarePlayer = new MediaPlayer(fanfare);
  }

  /**
   * Method for setting the layout of the components of the completed game view.
   */
  private void layoutComponents() {
    StackPane root = new StackPane();

    PlayingGameView playingGameView = new PlayingGameView();
    backgroundImage = playingGameView.setBackgroundGame();

    BackgroundImage background = new BackgroundImage(backgroundImage,
        null, null, null,
        new BackgroundSize(1300, 750, false, false, false, false));
    root.setBackground(new Background(background));

    characterImage = playingGameView.setCharacterImage();

    confetti.setFitHeight(600);
    confetti.setFitWidth(800);

    podium.setFitHeight(250);
    podium.setFitWidth(750);

    characterImage.setFitWidth(350);
    characterImage.setFitHeight(550);

    healthImage.setFitHeight(50);
    healthImage.setFitWidth(50);

    goldImage.setFitHeight(50);
    goldImage.setFitWidth(50);

    scoreImage.setFitHeight(50);
    scoreImage.setFitWidth(50);

    inventoryImage.setFitHeight(50);
    inventoryImage.setFitWidth(50);

    HBox healthHbox = new HBox();
    healthHbox.getChildren().addAll(healthImage, healthGoalFulfilled);
    healthHbox.setAlignment(Pos.CENTER_LEFT);
    healthHbox.setSpacing(20);
    healthHbox.setPadding(new Insets(0, 0, 0, 30));

    HBox goldHbox = new HBox();
    goldHbox.getChildren().addAll(goldImage, goldGoalFulfilled);
    goldHbox.setSpacing(20);
    goldHbox.setPadding(new Insets(0, 0, 0, 30));
    goldHbox.setAlignment(Pos.CENTER_LEFT);

    HBox scoreHbox = new HBox();
    scoreHbox.getChildren().addAll(scoreImage, scoreGoalFulfilled);
    scoreHbox.setSpacing(20);
    scoreHbox.setPadding(new Insets(0, 0, 0, 30));
    scoreHbox.setAlignment(Pos.CENTER_LEFT);

    HBox inventoryHbox = new HBox();
    inventoryHbox.getChildren().addAll(inventoryImage, inventoryGoalFulfilled);
    inventoryHbox.setSpacing(20);
    inventoryHbox.setPadding(new Insets(0, 0, 0, 30));
    inventoryHbox.setAlignment(Pos.CENTER_LEFT);

    VBox winningStats = new VBox();
    winningStats.getChildren().addAll(winningStatsText, healthHbox, goldHbox, scoreHbox,
        inventoryHbox);
    winningStats.setAlignment(Pos.CENTER);
    winningStats.setSpacing(20);
    winningStats.getStyleClass().add("vBoxRounded");

    winningStats.setPrefWidth(350);
    winningStats.setPrefHeight(420);
    winningStats.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

    VBox winnerTextVbox = new VBox();
    winnerTextVbox.getChildren().addAll(winnerText);
    winnerTextVbox.setAlignment(Pos.CENTER);
    VBox.setMargin(winnerText, new Insets(10, 10, 10, 10));
    winnerTextVbox.getStyleClass().add("vBoxRoundedWinning");

    VBox buttonVbox = new VBox();
    buttonVbox.getChildren().addAll(homeButton);
    buttonVbox.setAlignment(Pos.TOP_RIGHT);
    VBox.setMargin(homeButton, new Insets(30, -300, 0, 0));

    HBox topBar = new HBox();
    topBar.getChildren().addAll(winnerTextVbox, buttonVbox);
    topBar.setAlignment(Pos.TOP_CENTER);
    HBox.setMargin(winningStats, new Insets(200, 0, 0, 200));
    HBox.setMargin(winnerTextVbox, new Insets(10, 0, 0, 0));

    winningStatsHbox = new HBox();
    winningStatsHbox.getChildren().addAll(winningStats);
    winningStatsHbox.setAlignment(Pos.CENTER_RIGHT);
    HBox.setMargin(winningStats, new Insets(50, 30, 0, 0));

    VBox contentVbox = new VBox();
    contentVbox.getChildren().addAll(topBar, winningStatsHbox);

    VBox trollPodium = new VBox();
    trollPodium.getChildren().addAll(characterImage);
    trollPodium.setAlignment(Pos.BOTTOM_CENTER);
    //trollPodiumPlacement();

    VBox podiumVbox = new VBox();
    podiumVbox.getChildren().addAll(podium, confetti);
    podiumVbox.setAlignment(Pos.BOTTOM_CENTER);
    VBox.setMargin(podium, new Insets(0, 350, -610, 0));
    VBox.setMargin(confetti, new Insets(0, 350, -40, 0));

    root.getChildren().addAll(podiumVbox, trollPodium, contentVbox);

    completedGameScene = new Scene(root, 1300, 750);
  }

  /**
   * Method for determining the color of the goals on the completed
   * game view. If the goal is fulfilled, the label of the goal will
   * be set to green. If the goal is not fulfilled, the color of the
   * goal is red. Reads from file to determine if the goal is fulfilled.
   * If the line of a goal contains "True", the goal is fulfilled. If
   * the line of a goal contains "False", the goal is not fulfilled.
   */
  private void goalIsFulfilledStatus() {
    List<Label> labels = Arrays.asList(goldGoalFulfilled, healthGoalFulfilled,
        scoreGoalFulfilled, inventoryGoalFulfilled);
    Path pathToIsFulfilled = Paths.get("src/main/resources"
        + "/edu.ntnu.idatt2001/gameData/goalIsFulfilled");

    try (BufferedReader reader = Files.newBufferedReader(pathToIsFulfilled,
        StandardCharsets.UTF_8)) {
      String line;
      int index = 0;
      while ((line = reader.readLine()) != null && index < labels.size()) {
        if (line.contains("True")) {
          labels.get(index).getStyleClass().remove("goalNotFulfilled");
          labels.get(index).getStyleClass().add("goalFulfilled");
        } else {
          labels.get(index).getStyleClass().remove("goalFulfilled");
          labels.get(index).getStyleClass().add("goalNotFulfilled");
        }
        index++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for placing the troll on the podium depending on how many goals are fulfilled.
   * If all goals are fulfilled, the troll will be placed on the first place.
   * If two or more goals are fulfilled, the troll will be placed on the second place.
   * If less than two goals are fulfilled, the troll will be placed on the third place.
   */
  public void trollPodiumPlacement() {
    int numGoalsFulfilled = (goldGoalFulfilled.getStyleClass().contains("goalFulfilled") ? 1 : 0)
            + (healthGoalFulfilled.getStyleClass().contains("goalFulfilled") ? 1 : 0)
            + (scoreGoalFulfilled.getStyleClass().contains("goalFulfilled") ? 1 : 0)
            + (inventoryGoalFulfilled.getStyleClass().contains("goalFulfilled") ? 1 : 0);

    if (numGoalsFulfilled == 4) {
      VBox.setMargin(characterImage, new Insets(0, 0, 135, -350));
    } else if (numGoalsFulfilled >= 2) {
      VBox.setMargin(characterImage, new Insets(0, 0, 80, -700));
    } else {
      VBox.setMargin(characterImage, new Insets(0, 0, 60, 50));
    }
  }

  /**
   * Method for accessing the completed game scene.
   *
   * @return the completed game scene (Scene)
   */
  public static Scene getCompletedGameScene() {
    return completedGameScene;
  }

  /**
   * Method for accessing the home button.
   *
   * @return the home button (Button)
   */
  public Button getHomeButton() {
    return homeButton;
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
   * Method for accessing the fanfare player.
   *
   * @return fanfarePlayer (MediaPlayer)
   */
  public MediaPlayer getFanfarePlayer() {
    return fanfarePlayer;
  }

  /**
   * Method for accessing the winning stats HBox.
   *
   * @return winningStatsHbox (HBox)
   */
  public HBox getWinningStatsHbox() {
    return winningStatsHbox;
  }

  /**
   * Method for accessing the character image.
   *
   * @return character image (ImageView)
   */
  public ImageView getCharacterImage() {
    return characterImage;
  }
}
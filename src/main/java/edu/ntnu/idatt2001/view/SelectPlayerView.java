package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.Player;
import edu.ntnu.idatt2001.model.builder.PlayerBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class is responsible for layout of the select player scene.
 */
public class SelectPlayerView {

  private static Scene selectPlayerScene;
  private Label selectPlayerTitle;
  private Button backButton;
  private ImageView toppyImage;
  private ImageView poppyImage;
  private Button toppyButton;
  private Button poppyButton;
  private Label nameLabel;
  private Label healthLabel;
  private Label goldLabel;
  private Label errorNameLabel;
  private Label errorHealthLabel;
  private Label errorGoldLabel;
  private TextField nameField;
  private TextField healthField;
  private TextField goldField;
  private Label selectGoalsTitle;
  private Label goldGoalLabel;
  private Button tenGoldButton;
  private Button thirtyGoldButton;
  private Button fiftyGoldButton;
  private Label healthGoalLabel;
  private Button oneHealthButton;
  private Button twoHealthButton;
  private Button threeHealthButton;
  private Label scoreGoalLabel;
  private Button thirtyScoreButton;
  private Button fiftyScoreButton;
  private Button hundredScoreButton;
  private Label inventoryGoalLabel;
  private ComboBox<String> inventoryGoalComboBox;
  private Label errorLabel;
  private Button playButton;
  private VBox goalsVbox;

  /**
   * Constructor of the class SelectPlayerView.
   */
  public SelectPlayerView() {
    initializeComponents();
    layoutComponents();
  }

  /**
   * Private help method responsible for initializing the components of the scene.
   */
  private void initializeComponents() {

    selectPlayerTitle = new Label("SELECT PLAYER");
    selectPlayerTitle.getStyleClass().add("titles");

    backButton = new Button("Back");
    backButton.getStyleClass().addAll("buttons", "buttons:hover");

    toppyImage = new ImageView("file:src/main/resources/edu.ntnu.idatt2001"
            + "/dataFiles/green_troll.png");
    toppyImage.setFitHeight(450);
    toppyImage.setFitWidth(350);

    poppyImage = new ImageView("file:src/main/resources/edu.ntnu.idatt2001"
            + "/dataFiles/pink_troll.png");
    poppyImage.setFitHeight(450);
    poppyImage.setFitWidth(400);

    toppyButton = new Button("Toppy");
    toppyButton.getStyleClass().addAll("uploadGameButtons", "uploadGameButtons:hover");
    toppyButton.setId("Toppy");

    poppyButton = new Button("Poppy");
    poppyButton.getStyleClass().addAll("uploadGameButtons", "uploadGameButtons:hover");
    poppyButton.setId("Poppy");

    nameLabel = new Label("Name: ");
    nameLabel.getStyleClass().add("generalText");

    errorNameLabel = new Label("Must consist of letters: a-å and A-Å");
    errorNameLabel.getStyleClass().add("validationText");

    errorHealthLabel = new Label();
    errorHealthLabel.getStyleClass().add("validationText");

    errorGoldLabel = new Label();
    errorGoldLabel.getStyleClass().add("validationText");

    healthLabel = new Label("Health: ");
    healthLabel.getStyleClass().add("generalText");

    goldLabel = new Label("Gold: ");
    goldLabel.getStyleClass().add("generalText");

    nameField = new TextField();
    nameField.getStyleClass().addAll("whiteBackground", "fieldInputs");

    healthField = new TextField();
    healthField.getStyleClass().addAll("whiteBackground", "fieldInputs");

    goldField = new TextField();
    goldField.getStyleClass().addAll("whiteBackground", "fieldInputs");

    selectGoalsTitle = new Label("SELECT YOUR DESIRED GOALS");
    selectGoalsTitle.getStyleClass().add("generalText");

    goldGoalLabel = new Label("Gold:   ");
    goldGoalLabel.getStyleClass().add("generalText");

    tenGoldButton = new Button("10");
    tenGoldButton.getStyleClass().addAll("goalButtons", "goalButtons:hover");
    tenGoldButton.setId("goldGoal(10)");

    thirtyGoldButton = new Button("30");
    thirtyGoldButton.getStyleClass().addAll("goalButtons", "goalButtons:hover");
    thirtyGoldButton.setId("goldGoal(30)");

    fiftyGoldButton = new Button("50");
    fiftyGoldButton.getStyleClass().addAll("goalButtons", "goalButtons:hover");
    fiftyGoldButton.setId("goldGoal(50)");

    healthGoalLabel = new Label("Health:");
    healthGoalLabel.getStyleClass().add("generalText");

    oneHealthButton = new Button("1");
    oneHealthButton.getStyleClass().addAll("goalButtons", "goalButtons:hover");
    oneHealthButton.setId("healthGoal(1)");

    twoHealthButton = new Button("2");
    twoHealthButton.getStyleClass().addAll("goalButtons", "goalButtons:hover");
    twoHealthButton.setId("healthGoal(2)");

    threeHealthButton = new Button("3");
    threeHealthButton.getStyleClass().addAll("goalButtons", "goalButtons:hover");
    threeHealthButton.setId("healthGoal(3)");

    scoreGoalLabel = new Label("Score:  ");
    scoreGoalLabel.getStyleClass().add("generalText");

    thirtyScoreButton = new Button("30");
    thirtyScoreButton.getStyleClass().addAll("goalButtons", "goalButtons:hover");
    thirtyScoreButton.setId("scoreGoal(30)");

    fiftyScoreButton = new Button("50");
    fiftyScoreButton.getStyleClass().addAll("goalButtons", "goalButtons:hover");
    fiftyScoreButton.setId("scoreGoal(50)");

    hundredScoreButton = new Button("100");
    hundredScoreButton.getStyleClass().addAll("goalButtons", "goalButtons:hover");
    hundredScoreButton.setId("scoreGoal(100)");

    inventoryGoalLabel = new Label("Inventory: ");
    inventoryGoalLabel.getStyleClass().add("generalText");

    inventoryGoalComboBox = new ComboBox<>();
    inventoryGoalComboBox.getItems().addAll("Map", "Sunglasses", "Goggles", "Compass");
    inventoryGoalComboBox.setPromptText("Item");
    inventoryGoalComboBox.getStyleClass().addAll("whiteBackground", "fieldInputs");

    errorLabel = new Label("Please make sure that you have selected a character, filled in "
            + "all the fields and chosen goals (if enabled)");
    errorLabel.getStyleClass().add("validationText");

    playButton = new Button("Play");
    playButton.getStyleClass().addAll("uploadGameButtons", "uploadGameButtons:hover");
  }

  /**
   * Private help method responsible for layout of the components of the scene.
   */
  private void layoutComponents() {
    HBox topHbox = new HBox();
    topHbox.getChildren().addAll(selectPlayerTitle, backButton);
    topHbox.setSpacing(820);
    HBox.setMargin(backButton, new Insets(0, -10, 0, 20));
    HBox.setMargin(selectPlayerTitle, new Insets(0, 0, 0, 30));
    topHbox.setPadding(new Insets(20, -5, 0, -5));

    HBox imageHbox = new HBox();
    imageHbox.getChildren().addAll(poppyImage, toppyImage);
    imageHbox.setSpacing(-50);
    imageHbox.setPadding(new Insets(0, 0, 0, 50));

    HBox trollsButtonsHbox = new HBox();
    trollsButtonsHbox.getChildren().addAll(poppyButton, toppyButton);
    trollsButtonsHbox.setSpacing(130);
    trollsButtonsHbox.setPadding(new Insets(0, 0, 0, 170));

    VBox leftMiddleVbox = new VBox();
    leftMiddleVbox.getChildren().addAll(imageHbox, trollsButtonsHbox);

    HBox goldGoalHbox = new HBox();
    goldGoalHbox.getChildren().addAll(goldGoalLabel, tenGoldButton,
            thirtyGoldButton, fiftyGoldButton);
    HBox.setMargin(goldGoalLabel, new Insets(0, 0, 10, 0));
    HBox.setMargin(tenGoldButton, new Insets(0, 0, 10, 0));
    HBox.setMargin(thirtyGoldButton, new Insets(0, 0, 10, 0));

    goldGoalHbox.setSpacing(10);

    HBox healthGoalHbox = new HBox();
    healthGoalHbox.getChildren().addAll(healthGoalLabel, oneHealthButton,
            twoHealthButton, threeHealthButton);
    HBox.setMargin(healthGoalLabel, new Insets(0, 0, 10, 0));
    HBox.setMargin(oneHealthButton, new Insets(0, 0, 10, 0));
    HBox.setMargin(twoHealthButton, new Insets(0, 0, 10, 0));
    HBox.setMargin(threeHealthButton, new Insets(0, 0, 10, 0));
    healthGoalHbox.setSpacing(10);
    healthGoalHbox.setPadding(new Insets(0, 10, 0, 0));

    HBox scoreGoalHbox = new HBox();
    scoreGoalHbox.getChildren().addAll(scoreGoalLabel, thirtyScoreButton,
            fiftyScoreButton, hundredScoreButton);
    HBox.setMargin(scoreGoalLabel, new Insets(0, 0, 10, 0));
    HBox.setMargin(thirtyScoreButton, new Insets(0, 0, 10, 0));
    HBox.setMargin(fiftyScoreButton, new Insets(0, 0, 10, 0));
    HBox.setMargin(hundredScoreButton, new Insets(0, 0, 10, 0));
    scoreGoalHbox.setSpacing(10);
    scoreGoalHbox.setPadding(new Insets(0, 10, 0, 0));

    HBox inventoryGoalHbox = new HBox();
    inventoryGoalComboBox.setPrefWidth(180);
    inventoryGoalHbox.getChildren().addAll(inventoryGoalLabel, inventoryGoalComboBox);
    inventoryGoalHbox.setSpacing(10);
    inventoryGoalHbox.setPadding(new Insets(0, 10, 0, 0));

    goalsVbox = new VBox();
    goalsVbox.getChildren().addAll(selectGoalsTitle, healthGoalHbox, goldGoalHbox,
            scoreGoalHbox, inventoryGoalHbox);
    goalsVbox.setSpacing(10);
    VBox.setMargin(selectGoalsTitle, new Insets(-20, 20, 20, 0));
    goalsVbox.setPadding(new Insets(60, 0, 0, 20));

    HBox nameHbox = new HBox();
    errorNameLabel.setVisible(false);
    nameHbox.getChildren().addAll(nameLabel, errorNameLabel);

    HBox healthHbox = new HBox();
    errorHealthLabel.setVisible(false);
    healthHbox.getChildren().addAll(healthLabel, errorHealthLabel);

    HBox goldHbox = new HBox();
    errorGoldLabel.setVisible(false);
    goldHbox.getChildren().addAll(goldLabel, errorGoldLabel);

    VBox rightMiddleVbox = new VBox();
    rightMiddleVbox.getChildren().addAll(nameHbox, nameField, healthHbox,
            healthField, goldHbox, goldField, goalsVbox);
    rightMiddleVbox.setSpacing(10);

    HBox middleHbox = new HBox();
    middleHbox.getChildren().addAll(leftMiddleVbox, rightMiddleVbox);
    middleHbox.setSpacing(120);

    HBox bottomHbox = new HBox();
    bottomHbox.getChildren().addAll(errorLabel, playButton);
    errorLabel.setVisible(false);
    bottomHbox.setSpacing(140);
    HBox.setMargin(errorLabel, new Insets(0, 0, 0, 20));
    HBox.setMargin(playButton, new Insets(20, 0, 60, 0));

    VBox selectPlayerVbox = new VBox();
    selectPlayerVbox.getChildren().addAll(topHbox, middleHbox, bottomHbox);
    selectPlayerVbox.setSpacing(30);
    selectPlayerVbox.getStyleClass().add("blueBackground");

    selectPlayerScene = new Scene(selectPlayerVbox, 1300, 750);
  }


  /**
   * Method used for reading from a file.
   *
   * @param file (File)
   * @return List<String> (List of strings)
   */
  public List<String> readFromFile(File file) {
    List<String> lines = new ArrayList<>();

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        lines.add(line);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return lines;
  }

  /**
   * Method used for setting the player. Using the readFromFile method
   * to read the lines from the file containing the user input from the
   * select player scene. Making a new player with the playerBuilder class.
   *
   * @return Player (Player)
   */
  public Player setPlayer() {
    File file = new File("src/main/resources/edu.ntnu.idatt2001/gameData/selectedPlayer");
    List<String> lines = readFromFile(file);
    String name = lines.get(0);
    int health = Integer.parseInt(lines.get(1));
    int gold = Integer.parseInt(lines.get(2));
    PlayerBuilder playerBuilder = new PlayerBuilder(name, health, gold);
    return new Player(playerBuilder);
  }

  /**
   * Method for accessing the select player scene.
   *
   * @return uploadGameScene (Scene)
   */
  public static Scene getSelectPlayerScene() {
    return selectPlayerScene;
  }

  /**
   * Method for accessing the back button.
   *
   * @return backButton (Button)
   */
  public Button getBackButton() {
    return backButton;
  }

  /**
   * Method for accessing the Toppy button.
   *
   * @return ToppyButton (Button)
   */
  public Button getToppyButton() {
    return toppyButton;
  }

  /**
   * Method for accessing the Poppy button.
   *
   * @return PoppyButton (Button)
   */
  public Button getPoppyButton() {
    return poppyButton;
  }

  /**
   * Method for accessing the name field.
   *
   * @return nameField (TextField)
   */
  public TextField getNameField() {
    return nameField;
  }

  /**
   * Method for accessing the health field.
   *
   * @return healthField (TextField)
   */
  public TextField getHealthField() {
    return healthField;
  }

  /**
   * Method for accessing the gold field.
   *
   * @return goldField (TextField)
   */
  public TextField getGoldField() {
    return goldField;
  }

  /**
   * Method for accessing the ten gold button.
   *
   * @return tenGoldButton (Button)
   */
  public Button getTenGoldButton() {
    return tenGoldButton;
  }

  /**
   * Method for accessing the thirty gold button.
   *
   * @return thirtyGoldButton (Button)
   */
  public Button getThirtyGoldButton() {
    return thirtyGoldButton;
  }

  /**
   * Method for accessing the fifty gold button.
   *
   * @return fiftyGoldButton (Button)
   */
  public Button getFiftyGoldButton() {
    return fiftyGoldButton;
  }

  /**
   * Method for accessing the one health button.
   *
   * @return oneHealthButton (Button)
   */
  public Button getOneHealthButton() {
    return oneHealthButton;
  }

  /**
   * Method for accessing the two health button.
   *
   * @return twoHealthButton (Button)
   */
  public Button getTwoHealthButton() {
    return twoHealthButton;
  }

  /**
   * Method for accessing the three health button.
   *
   * @return threeHealthButton (Button)
   */
  public Button getThreeHealthButton() {
    return threeHealthButton;
  }

  /**
   * Method for accessing the ten score button.
   *
   * @return tenScoreButton (Button)
   */
  public Button getThirtyScoreButton() {
    return thirtyScoreButton;
  }

  /**
   * Method for accessing the fifty score button.
   *
   * @return fiftyScoreButton (Button)
   */
  public Button getFiftyScoreButton() {
    return fiftyScoreButton;
  }

  /**
   * Method for accessing the hundred score button.
   *
   * @return hundredScoreButton (Button)
   */
  public Button getHundredScoreButton() {
    return hundredScoreButton;
  }

  /**
   * Method for accessing the one inventory button.
   *
   * @return oneInventoryButton (Button)
   */
  public ComboBox<String> getInventoryGoalComboBox() {
    return inventoryGoalComboBox;
  }

  /**
   * Method for accessing the error label.
   *
   * @return errorLabel (Label)
   */
  public Label getErrorLabel() {
    return errorLabel;
  }

  /**
   * Method for accessing the play button.
   *
   * @return playButton (Button)
   */
  public Button getPlayButton() {
    return playButton;
  }

  /**
   * Method for accessing the goals VBox.
   *
   * @return goalsVBox (VBox)
   */
  public VBox getGoalsVbox() {
    return goalsVbox;
  }

  /**
   * Method for accessing the error name label.
   *
   * @return errorNameLabel (Label)
   */
  public Label getErrorNameLabel() {
    return errorNameLabel;
  }

  /**
   * Method for accessing the error health label.
   *
   * @return errorHealthLabel (Label)
   */
  public Label getErrorHealthLabel() {
    return errorHealthLabel;
  }

  /**
   * Method for accessing the error gold label.
   *
   * @return errorGoldLabel (Label)
   */
  public Label getErrorGoldLabel() {
    return errorGoldLabel;
  }
}




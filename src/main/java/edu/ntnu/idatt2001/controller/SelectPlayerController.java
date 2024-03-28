package edu.ntnu.idatt2001.controller;

import static edu.ntnu.idatt2001.view.components.SceneName.PLAYING_GAME;
import static edu.ntnu.idatt2001.view.components.SceneName.SELECT_LOCATION;

import edu.ntnu.idatt2001.view.SelectPlayerView;
import edu.ntnu.idatt2001.view.components.SceneSwitcher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class for the select player scene.
 */
public class SelectPlayerController {
  private final Stage primaryStage;
  private final SelectPlayerView selectPlayerView;
  private Button lastSelectedGoldGoal;
  private Button lastSelectedHealthGoal;
  private Button lastSelectedScoreGoal;
  private String lastSelectedInventoryGoal;
  private Button lastSelectedCharacter;


  /**
   * Constructor for the select player controller.
   *
   * @param primaryStage (Stage)
   */
  public SelectPlayerController(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    this.selectPlayerView = new SelectPlayerView();

    //Setting scene and stylesheet
    Scene selectPlayerScene = SelectPlayerView.getSelectPlayerScene();
    selectPlayerScene.getStylesheets().add("edu.ntnu.idatt2001/styles.css");

    //Reading selected story from file and checking for actions
    File file = new File("src/main/resources/edu.ntnu.idatt2001/gameData/selectedStory");
    Scanner scanner = new Scanner(file);
    String storyName = scanner.next();
    checkStoryForActions("src/main/resources/edu.ntnu.idatt2001/storyFiles/" + storyName);

    //Setting action on buttons and fields
    selectPlayerView.getBackButton().setOnAction(e -> {
      try {
        goBackOneScene();
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
    });
    selectPlayerView.getPlayButton().setOnAction(e -> {
      try {
        validatingPlayButton();
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
    });
    selectPlayerView.getPoppyButton().setOnAction(e -> poppyButtonHandler());
    selectPlayerView.getToppyButton().setOnAction(e -> toppyButtonHandler());
    selectPlayerView.getNameField().setOnKeyTyped(e -> nameFieldHandler());
    selectPlayerView.getHealthField().setOnKeyTyped(e -> healthFieldHandler());
    selectPlayerView.getGoldField().setOnKeyTyped(e -> goldFieldHandler());
    selectPlayerView.getTenGoldButton().setOnAction(e -> tenGoldButtonHandler());
    selectPlayerView.getThirtyGoldButton().setOnAction(e -> thirtyGoldButtonHandler());
    selectPlayerView.getFiftyGoldButton().setOnAction(e -> fiftyGoldButtonHandler());
    selectPlayerView.getOneHealthButton().setOnAction(e -> oneHealthButtonHandler());
    selectPlayerView.getTwoHealthButton().setOnAction(e -> twoHealthButtonHandler());
    selectPlayerView.getThreeHealthButton().setOnAction(e -> threeHealthButtonHandler());
    selectPlayerView.getThirtyScoreButton().setOnAction(e -> thirtyScoreButtonHandler());
    selectPlayerView.getFiftyScoreButton().setOnAction(e -> fiftyScoreButtonHandler());
    selectPlayerView.getHundredScoreButton().setOnAction(e -> hundredScoreButtonHandler());
    selectPlayerView.getInventoryGoalComboBox().setOnAction(e -> inventoryGoalComboBoxHandler());
  }

  /**
   * Method for checking if the story contains actions, and if so, enabling the goalsVBox.
   * If not, the goalsVBox is disabled.
   *
   * @param filePath (String)
   * @throws RuntimeException (RuntimeException)
   */
  public void checkStoryForActions(String filePath) {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (line.contains("{") && line.contains(":") && line.endsWith("}")) {
          selectPlayerView.getGoalsVbox().setDisable(false);
          break;
        } else {
          selectPlayerView.getGoalsVbox().setDisable(true);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Private help method for switching scene, when the back button is pressed.
   * Also clears all files in the gameData folder.
   *
   * @throws Exception (Exception)
   */
  private void goBackOneScene() throws Exception {
    StartPageController startPageController = new StartPageController(primaryStage);
    startPageController.clearFile("src/main/resources"
        + "/edu.ntnu.idatt2001/gameData/selectedLocation");
    startPageController.clearFile("src/main/resources"
        + "/edu.ntnu.idatt2001/gameData/selectedCharacter");
    startPageController.clearFile("src/main/resources"
        + "/edu.ntnu.idatt2001/gameData/selectedPlayer");
    startPageController.clearFile("src/main/resources"
        + "/edu.ntnu.idatt2001/gameData/selectedGoals");
    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(SELECT_LOCATION);
  }

  /**
   * Private help method for switching scene, when the play button is pressed.
   * Writes the selected character to file.
   * Writes the selected player to file.
   * And also writes the selected goals to file, if the goalsVBox is not disabled.
   *
   * @throws Exception (Exception)
   */
  private void goToNextScene() throws Exception {
    writeSelectedCharacterToFile();
    writeSelectedPlayerToFile();
    if (!selectPlayerView.getGoalsVbox().isDisabled()) {
      writeSelectedGoalsToFile();
    }
    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(PLAYING_GAME);
  }

  /**
   * Private help method for validating the play button before switching to a new scene.
   * Checking if any of the fields are empty,
   * the inputs are valid and that character and goals are selected.
   * If not, an error message will be shown, and the play button will not work.
   *
   * @throws Exception (Exception)
   */
  private void validatingPlayButton() throws Exception {
    String name = selectPlayerView.getNameField().getText();
    String health = selectPlayerView.getHealthField().getText();
    String gold = selectPlayerView.getGoldField().getText();


    //If goalsVBox is disabled, the goals will not be validated
    if (selectPlayerView.getGoalsVbox().isDisabled()) {
      try {
        if (name.isEmpty() || health.isEmpty() || gold.isEmpty() || validateNameField(name)
            || validateHealthField(health) || validateGoldField(gold)) {
          selectPlayerView.getErrorLabel().setVisible(true);
        } else {
          selectPlayerView.getErrorLabel().setVisible(false);
          goToNextScene();
        }
      } catch (NullPointerException e) {
        e.printStackTrace();
        selectPlayerView.getErrorLabel().setVisible(true);
      }
    } else {
      try {
        //If goalsVBox is enabled, the goals will also be validated
        if (name.isEmpty() || health.isEmpty() || gold.isEmpty() || validateNameField(name)
            || validateHealthField(health) || validateGoldField(gold)
            || validateHealthFieldWithGoals(health) || validateGoldFieldWithGoals(gold)
            || lastSelectedCharacter == null || lastSelectedGoldGoal == null
            || lastSelectedHealthGoal == null || lastSelectedScoreGoal == null
            || lastSelectedInventoryGoal.isEmpty()) {
          selectPlayerView.getErrorLabel().setVisible(true);
        } else {
          selectPlayerView.getErrorLabel().setVisible(false);
          goToNextScene();
        }
      } catch (NullPointerException e) {
        e.printStackTrace();
        selectPlayerView.getErrorLabel().setVisible(true);
      }
    }
  }

  /**
   * Private help method for handling selection of Poppy button.
   */
  private void poppyButtonHandler() {
    handleCharacterButtonSelection(selectPlayerView.getPoppyButton());
  }

  /**
   * Private help method for handling selection of Toppy button.
   */
  private void toppyButtonHandler() {
    handleCharacterButtonSelection(selectPlayerView.getToppyButton());
  }

  /**
   * Private help method for handling selection of character.
   * Changing the style of the selected character button.
   */
  private void handleCharacterButtonSelection(Button updatedCharacter) {
    if (lastSelectedCharacter != null) {
      lastSelectedCharacter.getStyleClass().remove("selectedCharacterButtons");
    }
    if (updatedCharacter.equals(lastSelectedCharacter)) {
      lastSelectedCharacter = null;
    } else {
      updatedCharacter.getStyleClass().add("selectedCharacterButtons");
      lastSelectedCharacter = updatedCharacter;
    }
  }

  /**
   * Method for accessing the last selected character.
   *
   * @return lastSelectedCharacter (Button)
   */
  public Button getLastSelectedCharacter() {
    return lastSelectedCharacter;
  }

  /**
   * Private help method for writing the selected character to file.
   * If no character is selected, nothing will be written to file.
   */
  private void writeSelectedCharacterToFile() {
    Button lastSelectedCharacterButton = getLastSelectedCharacter();
    if (lastSelectedCharacterButton != null) {
      String lastSelectedCharacter = lastSelectedCharacterButton.getId();

      try (FileWriter fileWriter = new FileWriter("src/main/resources"
          + "/edu.ntnu.idatt2001/gameData/selectedCharacter", true)) {
        fileWriter.write(lastSelectedCharacter + System.lineSeparator());
        fileWriter.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Private help method for writing the selected player to file.
   * If no player is selected, nothing will be written to file.
   */
  private void writeSelectedPlayerToFile() {
    String name = selectPlayerView.getNameField().getText();
    String health = selectPlayerView.getHealthField().getText();
    String gold = selectPlayerView.getGoldField().getText();
    try (FileWriter fileWriter = new FileWriter("src/main/resources"
        + "/edu.ntnu.idatt2001/gameData/selectedPlayer", true)) {
      fileWriter.write(name + System.lineSeparator());
      fileWriter.write(health + System.lineSeparator());
      fileWriter.write(gold + System.lineSeparator());
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Private help method for writing the selected goals to file.
   * If no goals are selected, nothing will be written to file.
   */
  private void writeSelectedGoalsToFile() {
    String healthGoal = lastSelectedHealthGoal.getId();
    String goldGoal = lastSelectedGoldGoal.getId();
    String scoreGoal = lastSelectedScoreGoal.getId();
    String inventoryGoal = lastSelectedInventoryGoal;

    try (FileWriter fileWriter = new FileWriter("src/main/resources"
        + "/edu.ntnu.idatt2001/gameData/selectedGoals", true)) {
      fileWriter.write(healthGoal + System.lineSeparator());
      fileWriter.write(goldGoal + System.lineSeparator());
      fileWriter.write(scoreGoal + System.lineSeparator());
      fileWriter.write(inventoryGoal + System.lineSeparator());
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  /** Private help method for validating the name field.
   * Checking if the name field is empty or not,
   * and setting the error label accordingly.
   */
  private void nameFieldHandler() {
    String name = selectPlayerView.getNameField().getText();
    selectPlayerView.getErrorNameLabel().setVisible(validateNameField(name));
  }

  /**
   * Private help method for validating the health field.
   * Checking if the goal box is disabled or not,
   * and setting the error label accordingly.
   */
  private void healthFieldHandler() {
    String health = selectPlayerView.getHealthField().getText();
    boolean goalBoxIsDisabled = selectPlayerView.getGoalsVbox().isDisabled();
    if (!goalBoxIsDisabled && validateHealthFieldWithGoals(health)) {
      selectPlayerView.getErrorHealthLabel().setVisible(true);
      selectPlayerView.getErrorHealthLabel().setText("Type an integer: 0 to 3");
    } else if (validateHealthField(health)) {
      selectPlayerView.getErrorHealthLabel().setVisible(true);
      selectPlayerView.getErrorHealthLabel().setText("Type an integer (0 to 999)");
    } else {
      selectPlayerView.getErrorHealthLabel().setVisible(false);
    }
  }

  /**
   * Private help method for validating the gold field.
   * Checking if the goal box is disabled or not,
   * and setting the error label accordingly.
   */
  private void goldFieldHandler() {
    String gold = selectPlayerView.getGoldField().getText();
    boolean goalBoxIsDisabled = selectPlayerView.getGoalsVbox().isDisabled();
    if (!goalBoxIsDisabled && validateGoldFieldWithGoals(gold)) {
      selectPlayerView.getErrorGoldLabel().setVisible(true);
      selectPlayerView.getErrorGoldLabel().setText("Type an integer: 0 to (goal gold - 10)");
    } else if (validateGoldField(gold)) {
      selectPlayerView.getErrorGoldLabel().setVisible(true);
      selectPlayerView.getErrorGoldLabel().setText("Type an integer (0 to 999)");
    } else {
      selectPlayerView.getErrorGoldLabel().setVisible(false);
    }
  }

  /**
   * Private help method for handling the ten gold button.
   */
  private void tenGoldButtonHandler() {
    handleGoldGoalButtons(selectPlayerView.getTenGoldButton());
  }

  /**
   * Private help method for handling the thirty gold button.
   */
  private void thirtyGoldButtonHandler() {
    handleGoldGoalButtons(selectPlayerView.getThirtyGoldButton());
  }

  /**
   * Private help method for handling the fifty gold button.
   */
  private void fiftyGoldButtonHandler() {
    handleGoldGoalButtons(selectPlayerView.getFiftyGoldButton());
  }

  /**
   * Private help method for handling the one health button.
   */
  private void oneHealthButtonHandler() {
    handleHealthGoalButtons(selectPlayerView.getOneHealthButton());
  }

  /**
   * Private help method for handling the two health button.
   */
  private void twoHealthButtonHandler() {
    handleHealthGoalButtons(selectPlayerView.getTwoHealthButton());
  }

  /**
   * Private help method for handling the three health button.
   */
  private void threeHealthButtonHandler() {
    handleHealthGoalButtons(selectPlayerView.getThreeHealthButton());
  }

  /**
   * Private help method for handling the thirty score button.
   */
  private void thirtyScoreButtonHandler() {
    handleScoreGoalButtons(selectPlayerView.getThirtyScoreButton());
  }

  /**
   * Private help method for handling the fifty score button.
   */
  private void fiftyScoreButtonHandler() {
    handleScoreGoalButtons(selectPlayerView.getFiftyScoreButton());

  }

  /**
   * Private help method for handling the hundred score button.
   */
  private void hundredScoreButtonHandler() {
    handleScoreGoalButtons(selectPlayerView.getHundredScoreButton());

  }

  /**
   * Private help method for handling the inventory goal combo box.
   */
  private void inventoryGoalComboBoxHandler() {
    lastSelectedInventoryGoal = selectPlayerView.getInventoryGoalComboBox().getValue();
    selectPlayerView.getInventoryGoalComboBox().getStyleClass().add("orangeBackground");
  }

  /**
   * Private help method for handling the gold goal buttons.
   * Removing the style of the last selected button,
   * and adding the style to the new selected button.
   *
   * @param updatedGoldGoal (Button)
   */
  private void handleGoldGoalButtons(Button updatedGoldGoal) {
    if (lastSelectedGoldGoal != null) {
      lastSelectedGoldGoal.getStyleClass().remove("selectedGoalButtons");
    }
    if (updatedGoldGoal.equals(lastSelectedGoldGoal)) {
      lastSelectedGoldGoal = null;
    } else {
      updatedGoldGoal.getStyleClass().add("selectedGoalButtons");
      lastSelectedGoldGoal = updatedGoldGoal;
    }
  }

  /**
   *  Private help method for handling the health goal buttons.
   *  Removing the style of the last selected button,
   *  and adding the style to the new selected button.
   *
   * @param updatedHealthGoal (Button)
   */
  private void handleHealthGoalButtons(Button updatedHealthGoal) {
    if (lastSelectedHealthGoal != null) {
      lastSelectedHealthGoal.getStyleClass().remove("selectedGoalButtons");
    }

    if (updatedHealthGoal.equals(lastSelectedHealthGoal)) {
      lastSelectedHealthGoal = null;
    } else {
      updatedHealthGoal.getStyleClass().add("selectedGoalButtons");
      lastSelectedHealthGoal = updatedHealthGoal;
    }
  }

  /**
   * Private help method for handling the gold score buttons.
   * Removing the style of the last selected button,
   * and adding the style to the new selected button.
   *
   * @param updatedScoreGoal (Button)
   */
  private void handleScoreGoalButtons(Button updatedScoreGoal) {
    if (lastSelectedScoreGoal != null) {
      lastSelectedScoreGoal.getStyleClass().remove("selectedGoalButtons");
    }

    if (updatedScoreGoal.equals(lastSelectedScoreGoal)) {
      lastSelectedScoreGoal = null;
    } else {
      updatedScoreGoal.getStyleClass().add("selectedGoalButtons");
      lastSelectedScoreGoal = updatedScoreGoal;
    }
  }

  /**
   * Private help method for validating the name field.
   * Allowing only letters and spaces.
   *
   * @param text (String)
   * @return boolean (true if valid, false if not)
   */
  private boolean validateNameField(String text) {
    return !text.matches("[a-zA-ZøæåØÆÅ ]+");
  }

  /**
   * Private help method for validating the health field.
   * Allowing only numbers from 0 to 999.
   *
   * @param text (String)
   * @return boolean (true if valid, false if not)
   */
  private boolean validateHealthField(String text) {
    return !text.matches("[0-9]\\d{0,2}");
  }

  /**
   * Private help method for validating the health field, when the goals are enabled.
   * Allowing only numbers from 0 to 3.
   *
   * @param text (String)
   * @return boolean (true if valid, false if not)
   */
  private boolean validateHealthFieldWithGoals(String text) {
    return !text.matches("[0-3]");
  }

  /**
   * Private help method for validating the gold field.
   * Allowing only numbers from 0 to 999.
   *
   * @param text (String)
   * @return boolean (true if valid, false if not)
   */
  private boolean validateGoldField(String text) {
    return !text.matches("[0-9]\\d{0,2}");
  }

  /**
   * Private help method for validating the gold field, when the goals are enabled.
   * Allowing only numbers from 0 to the selected goal minus 10.
   *
   * @param text (String)
   * @return boolean (true if valid, false if not)
   */
  private boolean validateGoldFieldWithGoals(String text) {
    if (lastSelectedGoldGoal == null) {
      return true;
    }
    int maxAllowedGold = Integer.parseInt(lastSelectedGoldGoal.getText()) - 10;
    int minAllowedGold = 0;
    int inputValue;
    try {
      inputValue = Integer.parseInt(text);
    } catch (NumberFormatException e) {
      return true;
    }
    return minAllowedGold > inputValue || inputValue > maxAllowedGold;
  }
}


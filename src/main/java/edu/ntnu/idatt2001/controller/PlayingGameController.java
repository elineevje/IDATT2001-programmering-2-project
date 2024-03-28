package edu.ntnu.idatt2001.controller;

import static edu.ntnu.idatt2001.view.components.SceneName.COMPLETED_GAME;
import static edu.ntnu.idatt2001.view.components.SceneName.START_PAGE;

import edu.ntnu.idatt2001.model.Game;
import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Passage;
import edu.ntnu.idatt2001.model.Player;
import edu.ntnu.idatt2001.model.Story;
import edu.ntnu.idatt2001.model.goal.Goal;
import edu.ntnu.idatt2001.model.goal.GoldGoal;
import edu.ntnu.idatt2001.model.goal.HealthGoal;
import edu.ntnu.idatt2001.model.goal.InventoryGoal;
import edu.ntnu.idatt2001.model.goal.ScoreGoal;
import edu.ntnu.idatt2001.readersandwriters.StoryReader;
import edu.ntnu.idatt2001.view.PlayingGameView;
import edu.ntnu.idatt2001.view.SelectPlayerView;
import edu.ntnu.idatt2001.view.components.ErrorAlert;
import edu.ntnu.idatt2001.view.components.SceneSwitcher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Controller class responsible for the starting game scene.
 */
public class PlayingGameController {
  private final Game game;
  private static PlayingGameView playingGameView;
  private final Stage primaryStage;
  private final SelectPlayerView selectPlayerView;
  private final Player player;
  private final List<Goal> goals;

  /**
   * Constructor for the starting game controller.
   *
   * @param primaryStage (Stage)
   */
  public PlayingGameController(Stage primaryStage) {
    this.primaryStage = primaryStage;

    playingGameView = new PlayingGameView();
    Scene startingGameScene = PlayingGameView.getStartingGameScene();
    startingGameScene.getStylesheets().add("edu.ntnu.idatt2001/styles.css");

    selectPlayerView = new SelectPlayerView();
    player = selectPlayerView.setPlayer();

    Story story = setStory();

    goals = setGoals();

    game = new Game(player, story, goals);

    File file = new File("src/main/resources/edu.ntnu.idatt2001/gameData/selectedStory");
    String storyName = playingGameView.readFromFile(file);
    checkStoryForActions("src/main/resources/edu.ntnu.idatt2001/storyFiles/" + storyName);

    //Set action listeners for the buttons and the tableview
    playingGameView.getQuitButton().setOnAction(e -> {
      try {
        playingGameView.getAlert().showAndWait().filter(response -> response == ButtonType.OK)
            .ifPresent(response -> homePageButtonHandler());
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
    });
    playingGameView.getYesButton().setOnAction(e -> makeVisible());
    playingGameView.getResultButton().setOnAction(e -> {
      try {
        isFulfilledToFile();
        goToNextScene();
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
    });

    updateView(game.begin());
    generateButtons(game.begin().getLinks());

  }

  /**
   * Private help method for switching scene, when the result button is pressed.
   *
   * @throws Exception (Exception)
   */
  private void goToNextScene() throws Exception {
    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(COMPLETED_GAME);
  }


  /**
   * Private help method for accessing the existing files in the storyFiles folder.
   *
   * @return existingFiles (List<String>)
   * @throws NullPointerException (NullPointerException)
   */
  private List<String> getExistingFiles() throws NullPointerException {
    List<String> existingFiles = new ArrayList<>();
    File folder = new File("src/main/resources/edu.ntnu.idatt2001/storyFiles");
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
      if (file.isFile()) {
        existingFiles.add(file.getName());
      }
    }
    return existingFiles;
  }

  /**
   * Private method for setting the story to be played. The story is read from
   * the file selectedStory. The method then loops through the existing files
   * in the storyFiles folder, and checks if the storyName is contained in the
   * existing files. If it is, the story is read from the file.
   *
   * @return story (Story)
   */
  private Story setStory() {
    File file = new File("src/main/resources/edu.ntnu.idatt2001/gameData/selectedStory");
    String storyName = playingGameView.readFromFile(file);

    try {
      for (String s : getExistingFiles()) {
        if (s.contains(storyName)) {
          StoryReader storyReader = new StoryReader();
          File storyFile = new File("src/main/resources/edu.ntnu.idatt2001/storyFiles/" + s);
          return storyReader.readStory(storyFile);
        }
      }
    } catch (Exception e) {
      ErrorAlert.show("Error", "Could not play the game."
          + e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Method that sets the goals for the game. The goals are read from the
   * file selectedGoals. The method splits the lines in the file into
   * goalType and goalNumber. The goalType is used to determine which
   * type of goal it is, and the goalNumber is used to determine the
   * goalValue. The method then creates a new goal object based on the
   * goalType and goalValue, and adds it to the list of goals. If there are
   * no numbers in the line, the content of the line will be assumed to be an
   * item, and will be added to the list of mandatory items.
   *
   * @return goals (List<Goal>)
   */
  public List<Goal> setGoals() {
    File file = new File("src/main/resources/edu.ntnu.idatt2001/gameData/selectedGoals");

    List<String> lines = selectPlayerView.readFromFile(file);

    List<Goal> goals = new ArrayList<>();
    List<String> mandatoryItems = new ArrayList<>();

    for (String line : lines) {
      if (line.contains("(") && line.contains(")")) {
        String goalType = line.split("\\(")[0];
        String goalNumber = line.replaceAll("[^\\d.]", "");

        if (goalNumber.isEmpty()) {
          continue;
        }

        int goalValue = Integer.parseInt(goalNumber);

        switch (goalType) {
          case "healthGoal" -> goals.add(new HealthGoal(goalValue));
          case "goldGoal" -> goals.add(new GoldGoal(goalValue));
          case "scoreGoal" -> goals.add(new ScoreGoal(goalValue));
          default -> throw new IllegalArgumentException("Unknown goal type: " + goalType);
        }
      } else {
        mandatoryItems.add(line);
      }
    }
    if (!mandatoryItems.isEmpty()) {
      goals.add(new InventoryGoal(mandatoryItems));
    }
    return goals;
  }

  /**
   * Private help method for making the storyVbox visible, by removing the startGameVbox.
   */
  private void makeVisible() {
    playingGameView.getBottomBar().getChildren().remove(playingGameView.getStartGameVbox());
    playingGameView.getBottomBar().getChildren().add(playingGameView.getStoryVbox());
  }

  /**
   * Private help method for making the result button visible, by removing the choice buttons.
   */
  private void makeResultButtonVisible() {
    playingGameView.getStoryVbox().getChildren().remove(playingGameView.getChoiceButtonsHbox());
    playingGameView.getStoryVbox().getChildren().add(playingGameView.getResultButton());
    VBox.setMargin(playingGameView.getResultButton(),
        new Insets(0, 0, 25, 0));
  }

  /**
   * Private help method for updating the view of the playing game scene.
   *
   * @param passage (Passage)
   */
  private void updateView(Passage passage) {
    playingGameView.getStoryText().setText(passage.getContent());

    playingGameView.getHealthPointsText().setText(String.valueOf(game.getPlayer().getHealth()));
    playingGameView.getGoldPointsText().setText(String.valueOf(game.getPlayer().getGold()));
    playingGameView.getScorePointsText().setText(String.valueOf(game.getPlayer().getScore()));

    //Removing the brackets from the inventory string in the top bar.
    String inventoryWoBracket = game.getPlayer().getInventory().toString()
        .substring(1, game.getPlayer().getInventory().toString().length() - 1);
    playingGameView.getInventoryText().setText(inventoryWoBracket);
  }

  /**
   * Public help method for handling the selection of the quit button. Clears all files.
   * Switches scene to the start page.
   */
  public void homePageButtonHandler() {
    try {
      StartPageController startPageController = new StartPageController(primaryStage);
      startPageController.clearFile("src/main/resources"
          + "/edu.ntnu.idatt2001/gameData/selectedLocation");
      startPageController.clearFile("src/main/resources"
          + "/edu.ntnu.idatt2001/gameData/selectedCharacter");
      startPageController.clearFile("src/main/resources"
          + "/edu.ntnu.idatt2001/gameData/selectedStory");
      SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
      sceneSwitcher.switchSceneTo(START_PAGE);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Private help method for generating the choice buttons.
   * Clears the choice buttons HBox and then adds the buttons to it for each link in the passage.
   * Makes the result button visible if the passage is the last one.
   *
   * @param links (List<Link>)
   */
  private void generateButtons(List<Link> links) {
    playingGameView.getChoiceButtonsHbox().getChildren().clear();

    for (Link link : links) {
      Button choiceButton = new Button();
      choiceButton.setText(link.getText());
      choiceButton.getStyleClass().addAll("storyChoiceButtons", "storyChoiceButtons:hover");
      choiceButton.setWrapText(true);

      //Setting the action for the choice button, by assigning button text to the link text.
      choiceButton.setOnAction(e -> {
        Passage passage = game.go(link);
        link.getActions().forEach(action -> action.execute(player));
        updateView(passage);

        playingGameView.getStoryText().setText(passage.getContent());
        generateButtons(passage.getLinks());
      });

      //Adding the choice button to the choice buttons HBox.
      playingGameView.getChoiceButtonsHbox().getChildren().add(choiceButton);
    }

    if (links.isEmpty()) {
      makeResultButtonVisible();
    }
  }

  /**
   * Method for checking if the story contains actions, making the components in the topBar visible.
   * If not, some components in the topBar is not visible.
   *
   * @param filePath (String)
   * @throws RuntimeException (RuntimeException)
   */
  public void checkStoryForActions(String filePath) {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (line.contains("{") && line.contains(":") && line.endsWith("}")) {
          playingGameView.getScoreIcon().setVisible(true);
          playingGameView.getInventoryIcon().setVisible(true);
          playingGameView.getScorePointsText().setVisible(true);
          playingGameView.getInventoryText().setVisible(true);
          break;
        } else {
          playingGameView.getScoreIcon().setVisible(false);
          playingGameView.getInventoryIcon().setVisible(false);
          playingGameView.getScorePointsText().setVisible(false);
          playingGameView.getInventoryText().setVisible(false);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Private help method for writing to the goalIsFulfilled file.
   * Checks if the goals are fulfilled and writes to the file accordingly.
   * Uses the isFulfilled method from the Goal interface to check whether the
   * goals are fulfilled.
   *
   * @throws IOException (IOException)
   */
  private void isFulfilledToFile() throws IOException {
    try (FileWriter fileWriter = new FileWriter("src/main/resources"
        + "/edu.ntnu.idatt2001/gameData/goalIsFulfilled", false)) {
      boolean goldGoalWritten = false;
      boolean healthGoalWritten = false;
      boolean scoreGoalWritten = false;
      boolean inventoryGoalWritten = false;

      for (Goal goal : goals) {
        if (goal instanceof GoldGoal) {
          fileWriter.write(goal.isFulfilled(player) ? "goldTrue\n" : "goldFalse\n");
          goldGoalWritten = true;
        } else if (goal instanceof HealthGoal) {
          fileWriter.write(goal.isFulfilled(player) ? "healthTrue\n" : "healthFalse\n");
          healthGoalWritten = true;
        } else if (goal instanceof ScoreGoal) {
          fileWriter.write(goal.isFulfilled(player) ? "scoreTrue\n" : "scoreFalse\n");
          scoreGoalWritten = true;
        } else if (goal instanceof InventoryGoal) {
          fileWriter.write(goal.isFulfilled(player) ? "inventoryTrue\n" : "inventoryFalse\n");
          inventoryGoalWritten = true;
        }
      }

      if (!goldGoalWritten) {
        fileWriter.write("goldFalse\n");
      }
      if (!healthGoalWritten) {
        fileWriter.write("healthFalse\n");
      }
      if (!scoreGoalWritten) {
        fileWriter.write("scoreFalse\n");
      }
      if (!inventoryGoalWritten) {
        fileWriter.write("inventoryFalse\n");
      }
      fileWriter.close();
    }
  }
}
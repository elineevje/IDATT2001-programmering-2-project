package edu.ntnu.idatt2001.controller;

import static edu.ntnu.idatt2001.view.components.SceneName.HOW_TO_PLAY;
import static edu.ntnu.idatt2001.view.components.SceneName.UPLOAD_GAME;

import edu.ntnu.idatt2001.view.StartPageView;
import edu.ntnu.idatt2001.view.components.SceneSwitcher;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class for the StartPageView.
 */
public class StartPageController {
  private static StartPageView startPageView;
  private final Stage primaryStage;

  /**
   * Constructor for the class StartPageController. Sets the scene and the stylesheet
   * for the scene. Clears the files that are used to store the selected character,
   * story, location, goals and player, and if the goals are fulfilled. Does this
   * to make sure that the game starts from scratch when running the program.
   * Sets the on action for the start button and the how to play button.
   *
   * @param primaryStage (Stage)
   */
  public StartPageController(Stage primaryStage) {
    this.primaryStage = primaryStage;
    startPageView = new StartPageView();

    clearFile("src/main/resources/edu.ntnu.idatt2001/gameData/selectedCharacter");
    clearFile("src/main/resources/edu.ntnu.idatt2001/gameData/selectedStory");
    clearFile("src/main/resources/edu.ntnu.idatt2001/gameData/selectedLocation");
    clearFile("src/main/resources/edu.ntnu.idatt2001/gameData/selectedGoals");
    clearFile("src/main/resources/edu.ntnu.idatt2001/gameData/selectedPlayer");
    clearFile("src/main/resources/edu.ntnu.idatt2001/gameData/goalIsFulfilled");

    Scene scene = startPageView.getStartScene();
    scene.getStylesheets().add("edu.ntnu.idatt2001/styles.css");

    startPageView.getStartButton().setOnAction(e -> {
      try {
        startButtonHandler();
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
    });

    startPageView.getHowToPlayButton().setOnAction(e -> {
      try {
        howToPlayButtonHandler();
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
    });
  }

  /**
   * Private method used in set on action for the start button. Switches the
   * scene to the upload game scene.
   *
   * @throws Exception (Exception)
   */
  private void startButtonHandler() throws Exception {
    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(UPLOAD_GAME);
  }

  /**
   * Private method used in set on action for the how to play button.
   * Switches the scene to the how to play scene.
   *
   * @throws Exception (Exception)
   */
  private void howToPlayButtonHandler() throws Exception {
    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(HOW_TO_PLAY);
  }

  /**
   * Public method that clears the file that is given as a parameter.
   * Used to make sure the game starts from scratch when running the program,
   * or when the user wants to start a new game.
   *
   * @param filePath (String)
   */
  public void clearFile(String filePath) {
    File file = new File(filePath);
    if (file.length() == 0) {
      return;
    }
    try {
      FileWriter fileWriter = new FileWriter(file, false);
      fileWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Public method that returns the start page scene.
   *
   * @return startPageView.getStartScene() (Scene)
   */
  public static Scene getStartPageScene() {
    return startPageView.getStartScene();
  }
}

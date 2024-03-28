package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.view.CompletedGameView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller class for the CompletedGameView. This class will check if the
 * story has any actions that should be performed when the game is completed.
 */
public class CompletedGameController {

  private static CompletedGameView completedGameView;
  private final PlayingGameController playingGameController;

  /**
   * Constructor for the class CompletedGameController. Sets the scene
   * and the stylesheet for the scene. Sets the on action for the home
   * button and adds the fanfare sound when the game is completed.
   *
   * @param primaryStage (Stage)
   * @throws Exception (Exception)
   */
  public CompletedGameController(Stage primaryStage) throws Exception {
    completedGameView = new CompletedGameView();
    playingGameController = new PlayingGameController(primaryStage);

    Scene scene = CompletedGameView.getCompletedGameScene();
    scene.getStylesheets().add("edu.ntnu.idatt2001/styles.css");

    File file = new File("src/main/resources/edu.ntnu.idatt2001/gameData/selectedStory");
    Scanner scanner = new Scanner(file);
    String storyName = scanner.next();

    checkStoryForActions("src/main/resources/edu.ntnu.idatt2001/storyFiles/" + storyName);

    completedGameView.getFanfarePlayer().play();

    completedGameView.getHomeButton().setOnAction(e -> {
      try {
        completedGameView.getAlert().showAndWait().filter(response -> response
                == ButtonType.OK).ifPresent(response ->
                playingGameController.homePageButtonHandler());
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    });
  }

  /**
   * Method for checking if the story contains actions.
   * If not, some components in the winningStatsHBox is not visible.
   *
   * @param filePath (String)
   */
  public void checkStoryForActions(String filePath) {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (line.contains("{") && line.contains(":") && line.endsWith("}")) {
          completedGameView.getWinningStatsHbox().setVisible(true);
          completedGameView.trollPodiumPlacement();
          break;
        } else {
          completedGameView.getWinningStatsHbox().setVisible(false);
          VBox.setMargin(completedGameView.getCharacterImage(), new Insets(0, 0, 135, -350));
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}


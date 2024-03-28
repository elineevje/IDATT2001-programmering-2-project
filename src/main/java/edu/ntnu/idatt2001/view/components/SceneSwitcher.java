package edu.ntnu.idatt2001.view.components;

import edu.ntnu.idatt2001.controller.*;
import edu.ntnu.idatt2001.view.CompletedGameView;
import edu.ntnu.idatt2001.view.HowToPlayView;
import edu.ntnu.idatt2001.view.PlayingGameView;
import edu.ntnu.idatt2001.view.SelectLocationView;
import edu.ntnu.idatt2001.view.SelectPlayerView;
import edu.ntnu.idatt2001.view.UploadGameView;
import edu.ntnu.idatt2001.view.components.SceneName;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class responsible for switching scenes, by the name of the scene.
 */
public class SceneSwitcher {
  private Stage primaryStage;
  private Image icon;

  /**
   * Constructor for the SceneSwitcher class.
   *
   * @param primaryStage (Stage)
   */
  public SceneSwitcher(Stage primaryStage) {
    this.primaryStage = primaryStage;
    icon = new Image("file:src/main/resources/edu.ntnu.idatt2001/dataFiles/LogoPaths.png");
    primaryStage.getIcons().add(icon);
  }

  /**
   * Switches the scene to the scene with the name given as parameter.
   * Uses a switch statement to determine which scene to switch to.
   *
   * @param sceneName (SceneName)
   * @throws IllegalArgumentException "Scene not found, unknown scene name: " + sceneName
   */
  public void switchSceneTo(SceneName sceneName) throws Exception {
    switch (sceneName) {
      case START_PAGE:
        new StartPageController(primaryStage);
        primaryStage.setScene(StartPageController.getStartPageScene());
        primaryStage.setTitle("Paths - Start Page");
        break;

      case HOW_TO_PLAY:
        new HowToPlayController(primaryStage);
        primaryStage.setScene(HowToPlayView.getHowToPlayScene());
        primaryStage.setTitle("Paths - How To Play");
        break;

      case UPLOAD_GAME:
        new UploadGameController(primaryStage);
        primaryStage.setScene(UploadGameView.getUploadGameScene());
        primaryStage.setTitle("Paths - Upload Game");
        break;

      case SELECT_LOCATION:
        new SelectLocationController(primaryStage);
        primaryStage.setScene(SelectLocationView.getSelectLocationScene());
        primaryStage.setTitle("Paths - Select Location");
        break;

      case SELECT_PLAYER:
        new SelectPlayerController(primaryStage);
        primaryStage.setScene(SelectPlayerView.getSelectPlayerScene());
        primaryStage.setTitle("Paths - Select Player");
        break;

      case PLAYING_GAME:
        new PlayingGameController(primaryStage);
        primaryStage.setScene(PlayingGameView.getStartingGameScene());
        primaryStage.setTitle("Paths - Playing Game");
        break;

      case COMPLETED_GAME:
        new CompletedGameController(primaryStage);
        primaryStage.setScene(CompletedGameView.getCompletedGameScene());
        primaryStage.setTitle("Paths - Game Completed");
        break;

      //TODO: add more scenes here
      default:
        throw new IllegalArgumentException("Scene not found, unknown scene name: " + sceneName);
    }
  }
}

package edu.ntnu.idatt2001.controller;

import static edu.ntnu.idatt2001.view.components.SceneName.START_PAGE;

import edu.ntnu.idatt2001.view.HowToPlayView;
import edu.ntnu.idatt2001.view.components.SceneSwitcher;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class for the HowToPlayView.
 */
public class HowToPlayController {

  private final Stage primaryStage;

  /**
   * Constructor for the class HowToPlayController. Sets the scene and the stylesheet for the scene.
   * Sets the on action for the back button.
   *
   * @param primaryStage (Stage)
   * @throws Exception (Exception)
   */
  public HowToPlayController(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    HowToPlayView howToPlayView = new HowToPlayView();

    Scene scene = HowToPlayView.getHowToPlayScene();
    scene.getStylesheets().add("edu.ntnu.idatt2001/styles.css");

    howToPlayView.getBackButton().setOnAction(e -> {
      try {
        backButtonHandler();
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    });
  }

  /**
   * Method used in set on action for the back button. Switches the scene to the start page.
   *
   * @throws Exception (Exception)
   */
  private void backButtonHandler() throws Exception {
    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(START_PAGE);
  }
}


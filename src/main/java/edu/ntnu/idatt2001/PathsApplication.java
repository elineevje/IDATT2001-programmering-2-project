package edu.ntnu.idatt2001;

import static edu.ntnu.idatt2001.view.components.SceneName.START_PAGE;

import edu.ntnu.idatt2001.view.components.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the Paths application.
 */
public class PathsApplication extends Application {

  /**
   * Starts the application by switching to the start page.
   *
   * @param primaryStage (Stage)
   * @throws Exception (Exception)
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(START_PAGE);


    primaryStage.setTitle("Paths");
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  /**
   * Starts the application by using the method launch from the Application class.
   *
   * @param args args
   */
  public static void main(String[] args) {
    launch(args);
  }
}

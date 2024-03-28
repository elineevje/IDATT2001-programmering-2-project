package edu.ntnu.idatt2001.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Class for the view of the start page scene.
 */
public class StartPageView {

  private Scene startScene;
  private Button startButton;
  private Button howToPlayButton;
  private Image pathsLogo;

  /**
   * Constructor for the class StartPageView. Initializes the components and layouts.
   */
  public StartPageView() {
    initializeComponents();
    layoutComponents();
  }

  /**
   * Method for initializing the components of the start page scene and setting the
   * style class for the components.
   */
  private void initializeComponents() {
    startButton = new Button("Start game");
    startButton.getStyleClass().add("buttons");

    howToPlayButton = new Button("How to play");
    howToPlayButton.getStyleClass().add("buttons");

    pathsLogo = new Image("file:src/main/resources/edu.ntnu.idatt2001/dataFiles/LogoPaths.png");
  }

  /**
   * Method for laying out the components of the start page scene.
   */
  private void layoutComponents() {
    ImageView imageView = new ImageView(pathsLogo);
    imageView.setFitHeight(300);
    imageView.setFitWidth(300);

    VBox startPage = new VBox();
    startPage.getChildren().addAll(imageView, startButton, howToPlayButton);
    startPage.setAlignment(Pos.CENTER);
    startPage.setSpacing(20);
    startPage.getStyleClass().add("blueBackground");

    startScene = new Scene(startPage, 1300, 750);
  }

  /**
   * Method for getting the start page scene.
   *
   * @return start scene (Scene)
   */
  public Scene getStartScene() {
    return startScene;
  }

  /**
   * Method for getting the start button.
   *
   * @return start button (Button)
   */
  public Button getStartButton() {
    return startButton;
  }

  /**
   * Method for getting the how to play button.
   *
   * @return how to play button (Button)
   */
  public Button getHowToPlayButton() {
    return howToPlayButton;
  }
}

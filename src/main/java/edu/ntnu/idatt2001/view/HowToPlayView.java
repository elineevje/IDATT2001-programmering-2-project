package edu.ntnu.idatt2001.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Class for the view of the how to play scene.
 */
public class HowToPlayView {

  private static Scene howToPlayScene;
  private Button backButton;
  private Label title;
  private Text instructions;

  /**
   * Constructor for the class HowToPlayView. Initializes the components and layouts.
   *
   * @throws FileNotFoundException (FileNotFoundException)
   */
  public HowToPlayView() throws FileNotFoundException {
    initializeComponents();
    layoutComponents();
  }

  /**
   * Method for initializing the components of the how to play scene and setting the
   * style class for the components. The instructions are read from the file HowToPlay.paths.
   *
   * @throws FileNotFoundException (FileNotFoundException)
   */
  private void initializeComponents() throws FileNotFoundException {
    title = new Label("HOW TO PLAY PATHS");
    title.getStyleClass().add("titles");

    backButton = new Button("Back");
    backButton.getStyleClass().add("buttons");

    File file = new File("src/main/resources/edu.ntnu.idatt2001/dataFiles/HowToPlay.paths");
    Scanner scanner = new Scanner(file);
    String contents = scanner.useDelimiter("\\A").next();
    scanner.close();

    instructions = new Text(contents);
    instructions.getStyleClass().add("instructions");
  }

  /**
   * Method for laying out the components of the how to play scene.
   */
  private void layoutComponents() {
    Pane instructionBox = new Pane();
    instructionBox.getChildren().add(instructions);
    instructions.setTranslateX(170);
    instructions.setTranslateY(30);
    instructionBox.setPrefSize(800, 800);
    instructionBox.getStyleClass().add("whiteBackground");

    HBox topHboxRight = new HBox();
    topHboxRight.getChildren().addAll(backButton);
    HBox.setMargin(backButton, new Insets(-40, 30, 0, 0));
    topHboxRight.setAlignment(Pos.TOP_RIGHT);

    HBox topHboxLeft = new HBox();
    topHboxLeft.getChildren().addAll(title);
    topHboxLeft.setAlignment(Pos.CENTER);
    title.setTranslateX(10);
    title.setTranslateY(30);

    VBox topBar = new VBox();
    topBar.getChildren().addAll(topHboxLeft, topHboxRight);
    topBar.setAlignment(Pos.TOP_CENTER);
    topBar.setPadding(new Insets(-5, -5, -5, -5));
    topBar.setSpacing(20);

    HBox bottomBar = new HBox();
    bottomBar.setSpacing(20);
    bottomBar.setAlignment(Pos.BOTTOM_RIGHT);

    VBox howToPlayPage = new VBox();
    howToPlayPage.getChildren().addAll(topBar, instructionBox, bottomBar);
    howToPlayPage.setAlignment(Pos.CENTER);
    howToPlayPage.setSpacing(20);
    howToPlayPage.getStyleClass().add("blueBackground");

    howToPlayScene = new Scene(howToPlayPage, 1300, 750);
  }

  /**
   * Method for getting the how to play scene.
   *
   * @return howToPlayScene (Scene)
   */
  public static Scene getHowToPlayScene() {
    return howToPlayScene;
  }

  /**
   * Method for getting the back button.
   *
   * @return backButton (Button)
   */
  public Button getBackButton() {
    return backButton;
  }
}

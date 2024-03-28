package edu.ntnu.idatt2001.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/**
 * Class for the view of the select location scene.
 */
public class SelectLocationView {

  private static Scene selectLocationScene;
  private Label title;
  private Button backButton;
  private Button goButton;
  private Image imageHouse;
  private Image imageWater;
  private Image imageForest;
  private Image imageSpace;
  private GridPane locationImages;
  private Button buttonWater;
  private Button buttonHouse;
  private Button buttonForest;
  private Button buttonSpace;

  /**
   * Constructor for the class SelectLocationView. Initializes the components and layouts.
   * Makes the grid pane used for the location images.
   */
  public SelectLocationView() {
    initializeComponents();
    makeGridPane();
    layoutComponents();
  }

  /**
   * Method for initializing the components of the select location scene and setting the
   * style class for the components.
   */
  private void initializeComponents() {
    title = new Label("SELECT LOCATION");
    title.getStyleClass().add("titles");

    backButton = new Button("Back");
    backButton.getStyleClass().add("buttons");

    goButton = new Button("Go");
    goButton.getStyleClass().add("uploadGameButtons");
    goButton.setDisable(true);

    imageHouse = new Image("file:src/main/resources/edu.ntnu.idatt2001/"
            + "dataFiles/Haunted_house.jpg");
    imageWater = new Image("file:src/main/resources/edu.ntnu.idatt2001/"
            + "dataFiles/Under_the_sea.jpg");
    imageForest = new Image("file:src/main/resources/edu.ntnu.idatt2001/"
            + "dataFiles/in_the_forest.jpg");
    imageSpace = new Image("file:src/main/resources/edu.ntnu.idatt2001/"
            + "dataFiles/galaxy.jpg");
  }

  /**
   * Method for laying out the components of the select location scene.
   */
  private void layoutComponents() {
    HBox topHboxRight = new HBox();
    topHboxRight.getChildren().addAll(backButton);
    HBox.setMargin(backButton, new Insets(33, 43, 0, 0));
    topHboxRight.setAlignment(Pos.TOP_RIGHT);

    HBox topHboxLeft = new HBox();
    topHboxLeft.getChildren().addAll(title);
    topHboxLeft.setAlignment(Pos.CENTER_LEFT);
    title.setTranslateX(10);
    title.setTranslateY(100);

    VBox topBar = new VBox();
    topBar.getChildren().addAll(topHboxLeft, topHboxRight);
    topBar.setAlignment(Pos.TOP_CENTER);
    topBar.setSpacing(20);

    HBox bottomBar = new HBox();
    bottomBar.getChildren().addAll(goButton);
    bottomBar.setSpacing(20);
    HBox.setMargin(goButton, new Insets(0, 50, 100, 0));
    bottomBar.setAlignment(Pos.BOTTOM_RIGHT);

    VBox selectLocationPage = new VBox();
    selectLocationPage.getChildren().addAll(topBar, locationImages, bottomBar);
    selectLocationPage.setAlignment(Pos.CENTER);
    selectLocationPage.setSpacing(20);
    selectLocationPage.getStyleClass().add("blueBackground");

    selectLocationScene = new Scene(selectLocationPage, 1300, 750);
  }

  /**
   * Method for making the grid pane used for the location images.
   * Adds the images to the buttons and sets the id of the buttons.
   * Makes a 2x2 grid pane consisting of the locations the user can
   * choose from on the select location scene.
   */
  private void makeGridPane() {
    ImageView imageViewWater = new ImageView(imageWater);
    imageViewWater.setFitHeight(290);
    imageViewWater.setFitWidth(635);

    ImageView imageViewForest = new ImageView(imageForest);
    imageViewForest.setFitHeight(290);
    imageViewForest.setFitWidth(635);

    ImageView imageViewHouse = new ImageView(imageHouse);
    imageViewHouse.setFitHeight(290);
    imageViewHouse.setFitWidth(635);

    ImageView imageViewSpace = new ImageView(imageSpace);
    imageViewSpace.setFitHeight(290);
    imageViewSpace.setFitWidth(635);

    buttonWater = new Button();
    buttonWater.setGraphic(imageViewWater);
    buttonWater.getStyleClass().add("imageButton");
    buttonWater.setId("Sea");

    buttonForest = new Button();
    buttonForest.setGraphic(imageViewForest);
    buttonForest.getStyleClass().add("imageButton");
    buttonForest.setId("Forest");

    buttonHouse = new Button();
    buttonHouse.setGraphic(imageViewHouse);
    buttonHouse.getStyleClass().add("imageButton");
    buttonHouse.setId("House");

    buttonSpace = new Button();
    buttonSpace.setGraphic(imageViewSpace);
    buttonSpace.getStyleClass().add("imageButton");
    buttonSpace.setId("Space");

    locationImages = new GridPane();
    locationImages.setHgap(1);
    locationImages.setVgap(1);

    ColumnConstraints firstColumn = new ColumnConstraints();
    firstColumn.setPercentWidth(50);
    ColumnConstraints secColumn = new ColumnConstraints();
    secColumn.setPercentWidth(50);
    locationImages.getColumnConstraints().addAll(firstColumn, secColumn);

    RowConstraints firstRow = new RowConstraints();
    firstRow.setPercentHeight(50);
    RowConstraints secRow = new RowConstraints();
    secRow.setPercentHeight(50);
    locationImages.getRowConstraints().addAll(firstRow, secRow);

    locationImages.add(buttonWater, 0, 0);
    locationImages.add(buttonForest, 1, 0);
    locationImages.add(buttonHouse, 0, 1);
    locationImages.add(buttonSpace, 1, 1);
    locationImages.setPrefSize(800, 800);
    locationImages.getStyleClass().add("whiteBackground");
  }


  /**
   * Method for getting the select location scene.
   *
   * @return select location scene (Scene)
   */
  public static Scene getSelectLocationScene() {
    return selectLocationScene;
  }

  /**
   * Method for getting the back button.
   *
   * @return back button (Button)
   */
  public Button getBackButton() {
    return backButton;
  }

  /**
   * Method for getting the go button.
   *
   * @return go button (Button)
   */
  public Button getGoButton() {
    return goButton;
  }

  /**
   * Method for getting the button for the water location.
   *
   * @return water button (Button)
   */
  public Button getButtonWater() {
    return buttonWater;
  }

  /**
   * Method for getting the button for the house location.
   *
   * @return house button (Button)
   */
  public Button getButtonHouse() {
    return buttonHouse;
  }

  /**
   * Method for getting the button for the forest location.
   *
   * @return forest button (Button)
   */
  public Button getButtonForest() {
    return buttonForest;
  }

  /**
   * Method for getting the button for the space location.
   *
   * @return space button (Button)
   */
  public Button getButtonSpace() {
    return buttonSpace;
  }
}

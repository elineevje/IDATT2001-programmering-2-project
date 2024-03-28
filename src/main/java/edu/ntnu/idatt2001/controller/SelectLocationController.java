package edu.ntnu.idatt2001.controller;

import static edu.ntnu.idatt2001.view.components.SceneName.SELECT_PLAYER;
import static edu.ntnu.idatt2001.view.components.SceneName.UPLOAD_GAME;

import edu.ntnu.idatt2001.view.SelectLocationView;
import edu.ntnu.idatt2001.view.components.SceneSwitcher;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class for the SelectLocationView.
 */
public class SelectLocationController {

  private static SelectLocationView selectLocationView;
  private final Stage primaryStage;
  private Button lastSelectedLocationButton;

  /**
   * Constructor for the class SelectLocationController. Sets the scene and the
   * stylesheet for the scene. Sets the on action for the back button, go button,
   * and the buttons for the different locations.
   *
   * @param primaryStage (Stage)
   */
  public SelectLocationController(Stage primaryStage) {
    this.primaryStage = primaryStage;
    selectLocationView = new SelectLocationView();

    Scene selectLoactionScene = SelectLocationView.getSelectLocationScene();
    selectLoactionScene.getStylesheets().add("edu.ntnu.idatt2001/styles.css");

    selectLocationView.getBackButton().setOnAction(e -> {
      try {
        backButtonHandler();
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
    });

    selectLocationView.getGoButton().setOnAction(e -> {
      try {
        goButtonHandler();
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
    });

    selectLocationView.getButtonWater().setOnAction(e -> selectedLocationWater());
    selectLocationView.getButtonHouse().setOnAction(e -> selectedLocationHouse());
    selectLocationView.getButtonSpace().setOnAction(e -> selectedLocationSpace());
    selectLocationView.getButtonForest().setOnAction(e -> selectedLocationForest());
  }

  /**
   * Private method used in set on action for the back button. Will clear the file
   * containing the selected story and the file containing the selected location,
   * so that the user will have to pick a new one if they go back. Switches
   * the scene to the upload game scene.
   *
   * @throws Exception (Exception)
   */
  private void backButtonHandler() throws Exception {
    StartPageController startPageController = new StartPageController(primaryStage);
    startPageController.clearFile("src/main/resources/edu.ntnu.idatt2001/gameData/selectedStory");
    startPageController.clearFile("src/main/resources/edu.ntnu.idatt2001/"
            + "gameData/selectedLocation");
    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(UPLOAD_GAME);
  }

  /**
   * Private method used in set on action for the go button. Will write the selected
   * location to a file. Switches the scene to the select player scene.
   *
   * @throws Exception (Exception)
   */
  private void goButtonHandler() throws Exception {
    writeSelectedLocationToFile();
    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(SELECT_PLAYER);
  }

  /**
   * Private method used for handling the selection of a location. If the location
   * is already selected, it will be deselected. The go button will be disabled if
   * no location is selected.
   *
   * @param updatedSelectedLocation (Button)
   */
  private void handleButtonSelection(Button updatedSelectedLocation) {
    if (lastSelectedLocationButton != null) {
      lastSelectedLocationButton.getStyleClass().remove("selectedImageButton");
    }

    if (updatedSelectedLocation.equals(lastSelectedLocationButton)) {
      lastSelectedLocationButton = null;
      selectLocationView.getGoButton().setDisable(true);
    } else {
      updatedSelectedLocation.getStyleClass().add("selectedImageButton");
      lastSelectedLocationButton = updatedSelectedLocation;
      selectLocationView.getGoButton().setDisable(false);
    }
  }

  /**
   * Private method for handling the selection of the space location.
   */
  private void selectedLocationSpace() {
    handleButtonSelection(selectLocationView.getButtonSpace());
  }

  /**
   * Private method for handling the selection of the forest location.
   */
  private void selectedLocationForest() {
    handleButtonSelection(selectLocationView.getButtonForest());
  }

  /**
   * Private method for handling the selection of the water location.
   */
  private void selectedLocationWater() {
    handleButtonSelection(selectLocationView.getButtonWater());
  }

  /**
   * Private method for handling the selection of the house location.
   */
  private void selectedLocationHouse() {
    handleButtonSelection(selectLocationView.getButtonHouse());
  }

  /**
   * Private method for writing the selected location to a file. Uses the
   * id of the lastSelectedLocation button when writing to the file. Writes
   * to the file selectedLocation stored in the gameData folder.
   */
  private void writeSelectedLocationToFile() {
    Button lastSelectedLocationButton = getLastSelectedLocation();
    if (lastSelectedLocationButton != null) {
      String lastSelectedLocation = lastSelectedLocationButton.getId();

      try (FileWriter fileWriter = new FileWriter("src/main/resources/"
              + "edu.ntnu.idatt2001/gameData/selectedLocation", true)) {
        fileWriter.write(lastSelectedLocation + System.lineSeparator());
        fileWriter.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Public method for getting the last selected location. The last
   * selected location is used for layout of the game.
   *
   * @return lastSelectedLocationButton (Button)
   */
  public Button getLastSelectedLocation() {
    return lastSelectedLocationButton;
  }
}


package edu.ntnu.idatt2001.controller;

import static edu.ntnu.idatt2001.view.components.SceneName.SELECT_LOCATION;
import static edu.ntnu.idatt2001.view.components.SceneName.START_PAGE;

import edu.ntnu.idatt2001.readersandwriters.StoryReader;
import edu.ntnu.idatt2001.view.UploadGameView;
import edu.ntnu.idatt2001.view.components.ErrorAlert;
import edu.ntnu.idatt2001.view.components.SceneSwitcher;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Controller class for the UploadGameScene.
 */
public class UploadGameController {

  private final UploadGameView uploadGameview;
  private final Stage primaryStage;
  private File lastSelectedStory;

  /**
   * Constructor of the class UploadGameController. Sets the scene and the stylesheet for the scene.
   * Sets the on action for the back button, go button, upload button and delete button.
   * Sets the on click for the table view.
   *
   * @param primaryStage (Stage)
   */
  public UploadGameController(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.uploadGameview = new UploadGameView();
    fillTable();

    Scene uploadGameScene = UploadGameView.getUploadGameScene();
    uploadGameScene.getStylesheets().add("edu.ntnu.idatt2001/styles.css");

    uploadGameview.getBackButton().setOnAction(e -> {
      try {
        goBackOneScene();
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    });

    uploadGameview.getGoButton().setOnAction(e -> {
      try {
        goToNextScene();
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    });

    uploadGameview.getUploadButton().setOnAction(e -> fileChooser());

    uploadGameview.getDeleteFileButton().setOnAction(e -> {
      try {
        uploadGameview.getAlert().showAndWait().filter(response -> response
                == ButtonType.OK).ifPresent(response -> deleteFile());
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    });

    uploadGameview.getTableView().setOnMouseClicked(e -> handleTableViewClick());
  }

  /**
   * Private help method for switching scene, when the back button is clicked.
   *
   * @throws Exception (Exception)
   */
  private void goBackOneScene() throws Exception {
    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(START_PAGE);
  }

  /**
   * Private help method for switching scene, when the go button is clicked.
   * Writes the selected story to a file.
   *
   * @throws Exception (Exception)
   */
  private void goToNextScene() throws Exception {
    writeSelectedStoryToFile();

    SceneSwitcher sceneSwitcher = new SceneSwitcher(primaryStage);
    sceneSwitcher.switchSceneTo(SELECT_LOCATION);
  }

  /**
   * Method for choosing a file from the computer and copying it to the storyFiles folder.
   */
  private void fileChooser() {
    try {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Resource File");
      fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
      fileChooser.getExtensionFilters()
              .add(new ExtensionFilter("Paths files", "*.paths"));
      File selectedFile = fileChooser.showOpenDialog(primaryStage);
      if (selectedFile != null) {
        String fileName = selectedFile.getName();
        String filePath = "src/main/resources/edu.ntnu.idatt2001/storyFiles/";
        try {
          StoryReader storyReader = new StoryReader();
          storyReader.readStory(selectedFile);
          File destinationDirectory = new File(filePath);
          Path destinationPath = destinationDirectory.toPath().resolve(fileName);
          Files.copy(selectedFile.toPath(), destinationPath,
              StandardCopyOption.REPLACE_EXISTING);
        } catch (IllegalArgumentException e) {
          ErrorAlert.show("Error", "Could not upload the .paths file."
                  + e.getMessage());
        }
      }
      fillTable();
    } catch (IOException | IllegalArgumentException e) {
      ErrorAlert.show("Error", "Could not upload the .paths file."
              + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Private help method for handling deleting a selected file from the tableview,
   * if the file is not empty.
   */
  private void deleteFile() {
    File selectedFile = uploadGameview.getTableView().getSelectionModel().getSelectedItem();
    if (selectedFile != null) {
      uploadGameview.getTableView().getItems().remove(selectedFile);
      try {
        Files.delete(selectedFile.toPath());
      } catch (IOException e) {
        ErrorAlert.show("Error", "Could not delete the .paths file."
                + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  /**
   * Private help method for handling the click on an object in the tableview.
   * Enables the buttons for opening and deleting a file, and the go button.
   * Hides the text that says "No file selected".
   */
  private void handleTableViewClick() {
    lastSelectedStory = uploadGameview.getTableView().getSelectionModel().getSelectedItem();
    if (lastSelectedStory != null) {
      uploadGameview.getDeleteFileButton().setDisable(false);
      uploadGameview.getGoButton().setDisable(false);
      uploadGameview.getNoSelectedFileText().setVisible(false);
    }
  }

  /**
   * Method for filling the table with the uploaded story files.
   * Checks if the storyFiles folder is empty, else adds the existing story files to the table.
   *
   * @throws RuntimeException "No files found in the storyFiles folder."
   */
  public void fillTable() {
    uploadGameview.getTableView().getItems().clear();

    String filePath = "src/main/resources/edu.ntnu.idatt2001/storyFiles";
    File storyFolder = new File(filePath);
    File[] storyFiles = storyFolder.listFiles();
    if (storyFiles == null) {
      throw new RuntimeException("No files found in the storyFiles folder.");
    }
    for (File file : storyFiles) {
      if (file.getName().endsWith(".paths")) {
        uploadGameview.getTableView().getItems().add(file);
      }
    }
  }

  /**
   * Private help method for writing the selected story to a file.
   * The file is stored in the gameData folder.
   * The last selected story is written to file. This will be the
   * story that is played when the game is started.
   */
  private void writeSelectedStoryToFile() {
    File lastSelectedStory = getLastSelectedStory();
    String fileName = lastSelectedStory.getName();
    try (FileWriter fileWriter = new FileWriter("src/main"
            + "/resources/edu.ntnu.idatt2001/gameData/selectedStory", true)) {
      fileWriter.write(fileName + System.lineSeparator());
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for getting the last selected story.
   *
   * @return lastSelectedStory (File)
   */
  public File getLastSelectedStory() {
    return lastSelectedStory;
  }
}



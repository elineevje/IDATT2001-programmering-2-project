package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Story;
import edu.ntnu.idatt2001.readersandwriters.StoryReader;
import edu.ntnu.idatt2001.view.components.ErrorAlert;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class is responsible for the layout of the upload game scene.
 */
public class UploadGameView {

  private final StoryReader storyReader;
  private static Scene uploadGameScene;
  private TableView<File> tableView;
  private Label textNoSelectedFile;
  private Label title;
  private Button backButton;
  private Button uploadButton;
  private Button deleteFileButton;
  private Button goButton;
  private TableColumn<File, Integer> brokenLinksColumn;
  private Alert alert;

  /**
   * Constructor for the class UploadGameView. Initializes the components and layouts.
   */
  public UploadGameView() {
    storyReader = new StoryReader();
    initializeComponents();
    layoutComponents();
  }

  /**
   * Private help method responsible for initializing the components of the scene.
   * The buttons, labels and tableview. Also sets the style class for the components.
   */
  private void initializeComponents() {
    backButton = new Button("Back");
    backButton.getStyleClass().addAll("buttons", "buttons:hover");

    uploadButton = new Button("Upload story");
    uploadButton.getStyleClass().addAll("uploadGameButtons", "buttons:hover");

    deleteFileButton = new Button("Delete");
    deleteFileButton.getStyleClass().addAll("uploadGameButtons", "buttons:hover");
    deleteFileButton.setDisable(true);

    alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm that you want to delete this file");
    alert.setHeaderText("Are you sure you want to delete this file?");
    alert.setContentText("This action can not be undone.");

    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add("edu.ntnu.idatt2001/styles.css");
    dialogPane.getStyleClass().add("alert-dialog");

    goButton = new Button("Go");
    goButton.getStyleClass().addAll("uploadGameButtons", "buttons:hover");
    goButton.setDisable(true);

    textNoSelectedFile = new Label("To continue, please select a file.");
    textNoSelectedFile.getStyleClass().add("selectAFileText");
    textNoSelectedFile.setVisible(true);

    title = new Label("CHOOSE A STORY TO PLAY");
    title.getStyleClass().add("titles");

    tableView = new TableView<>();
    tableView.getStyleClass().addAll("whiteBackground", "fieldInputs");
    tableView.setEditable(false);
    createColumns();
  }

  /**
   * Private help method responsible for creating the layout of the scene.
   */
  private void layoutComponents() {
    HBox topHboxRight = new HBox();
    topHboxRight.getChildren().addAll(backButton);
    topHboxRight.setAlignment(Pos.TOP_RIGHT);
    HBox.setMargin(backButton, new Insets(-52, 45, 0, 0));

    HBox topHboxLeft = new HBox();
    title.setTranslateX(10);
    title.setTranslateY(40);
    topHboxLeft.getChildren().addAll(title);
    topHboxLeft.setAlignment(Pos.CENTER_LEFT);

    HBox uploadButtonBox = new HBox(uploadButton);
    uploadButtonBox.setAlignment(Pos.BOTTOM_LEFT);
    uploadButtonBox.setPadding(new Insets(0, 480, 0, 0));

    HBox bottomBar = new HBox();
    bottomBar.getChildren().addAll(uploadButtonBox, deleteFileButton, goButton);
    bottomBar.setSpacing(20);
    bottomBar.setAlignment(Pos.BOTTOM_RIGHT);
    HBox.setMargin(goButton, new Insets(0, 20, 0, 0));
    HBox.setMargin(deleteFileButton, new Insets(0, 0, 0, 200));

    VBox messageNoFileSelectedBox = new VBox();
    messageNoFileSelectedBox.setAlignment(Pos.CENTER);
    messageNoFileSelectedBox.setSpacing(20);
    messageNoFileSelectedBox.getChildren().addAll(bottomBar, textNoSelectedFile);

    VBox topBar = new VBox();
    topBar.getChildren().addAll(topHboxLeft, topHboxRight, tableView, messageNoFileSelectedBox);
    topBar.setAlignment(Pos.TOP_CENTER);
    topBar.setSpacing(20);
    topBar.getStyleClass().add("blueBackground");

    uploadGameScene = new Scene(topBar, 1300, 750);
  }

  /**
   * Method for creating the tableview with the uploaded story files.
   * The tableview contains three columns:
   * 1. File name - the name of the file as a string
   * 2. File location - the path location of the file as a string
   * 3. Broken links - the number of broken links in  the story that the file holds
   * as an integer.
   * The tableview is not editable, sortable or resizable.
   */
  public void createColumns() {
    TableColumn<File, String> fileNameColumn = new TableColumn<>("File name");
    fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    fileNameColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getName()));
    fileNameColumn.setResizable(false);
    fileNameColumn.setSortable(false);
    fileNameColumn.setReorderable(false);
    fileNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));

    TableColumn<File, String> fileLocationColumn = new TableColumn<>("File location");
    fileLocationColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getPath()));
    fileLocationColumn.setResizable(false);
    fileLocationColumn.setSortable(false);
    fileLocationColumn.setReorderable(false);
    fileLocationColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.6));

    TableColumn<File, Integer> brokenLinksColumn = new TableColumn<>("Broken links");
    brokenLinksColumn.setCellValueFactory(cellData -> {
      File file = cellData.getValue();
      Story story = null;
      try {
        story = storyReader.readStory(file);
      } catch (IOException | IllegalArgumentException e) {
        ErrorAlert.show("Error", "Could not read story, Please try again");
      }
      List<Link> brokenLinksList = story.getBrokenLinks();
      int numberOfBrokenLinks = brokenLinksList.size();
      return new SimpleIntegerProperty(numberOfBrokenLinks).asObject();
    });

    brokenLinksColumn.setResizable(false);
    brokenLinksColumn.setSortable(false);
    brokenLinksColumn.setReorderable(false);
    brokenLinksColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));

    tableView.getColumns().addAll(fileNameColumn, fileLocationColumn, brokenLinksColumn);
  }

  /**
   * Method for accessing the upload button.
   *
   * @return uploadButton (Button)
   */
  public Button getUploadButton() {
    return uploadButton;
  }

  /**
   * Method for accessing the delete file button.
   *
   * @return deleteFileButton (Button)
   */
  public Button getDeleteFileButton() {
    return deleteFileButton;
  }

  /**
   * Method for accessing the go button.
   *
   * @return goButton (Button)
   */
  public Button getGoButton() {
    return goButton;
  }

  /**
   * Method for accessing the back button.
   *
   * @return backButton (Button)
   */
  public Button getBackButton() {
    return backButton;
  }

  /**
   * Method for accessing the tableview.
   *
   * @return tableView (TableView)
   */
  public TableView<File> getTableView() {
    return tableView;
  }

  /**
   * Method for accessing the text that is shown when no file is selected.
   *
   * @return textNoSelectedFile (Label)
   */
  public Label getNoSelectedFileText() {
    return textNoSelectedFile;
  }

  /**
   * Method for accessing the file name column.
   *
   * @return fileNameColumn (TableColumn)
   */
  public TableColumn<File, Integer> getBrokenLinksColumn() {
    return brokenLinksColumn;
  }

  /**
   * Method for accessing the alert.
   *
   * @return alert (Alert)
   */
  public Alert getAlert() {
    return alert;
  }

  /**
   * Method for accessing the upload game scene.
   *
   * @return uploadGameScene (Scene)
   */
  public static Scene getUploadGameScene() {
    return uploadGameScene;
  }
}

package edu.ntnu.idatt2001.view.components;

import javafx.scene.control.Alert;

/**
 * Class responsible for showing error alerts.
 */
public class ErrorAlert {

  /**
   * Method to show an error alert.
   *
   * @param header (String)
   * @param content (String)
   */
  public static void show(String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Paths - Error");
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.show();
  }
}

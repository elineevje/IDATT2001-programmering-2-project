package edu.ntnu.idatt2001.model.factory;

import edu.ntnu.idatt2001.model.action.Action;
import edu.ntnu.idatt2001.model.action.GoldAction;
import edu.ntnu.idatt2001.model.action.HealthAction;
import edu.ntnu.idatt2001.model.action.InventoryAction;
import edu.ntnu.idatt2001.model.action.ScoreAction;

/**
 * ActionFactory class.
 */
public class ActionFactory {

  /**
   * Get an action based on the action type and value of the action.
   * Using the factory pattern and a switch statement to decide which action to return.
   *
   * @param actionType (String)
   * @param value (String)
   * @return Action (Action)
   */
  public Action get(String actionType, String value) {
    return switch (actionType) {
      case "Health" -> new HealthAction(Integer.parseInt(value));
      case "Gold" -> new GoldAction(Integer.parseInt(value));
      case "Score" -> new ScoreAction(Integer.parseInt(value));
      case "Inventory" -> new InventoryAction(value);
      default -> throw new IllegalArgumentException("Invalid action type: " + actionType);
    };
  }
}

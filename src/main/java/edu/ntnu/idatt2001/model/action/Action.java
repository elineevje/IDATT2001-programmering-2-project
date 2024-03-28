package edu.ntnu.idatt2001.model.action;

import edu.ntnu.idatt2001.model.Player;

/**
 * Action interface.
 * The interface represents an upcoming change in the state of a player.
 * These changes are related to the player's health, score, gold reserves and inventory.
 */
public interface Action {

  /**
   * Executes a change of state for the player.
   *
   * @param player (Player)
   */
  void execute(Player player);

  /**
   * Returns the value of the action.
   *
   * @return value (String)
   */
  String getValue();

  /**
   * Returns a string representation of the action.
   *
   * @return String (String)
   */
  String toString();
}



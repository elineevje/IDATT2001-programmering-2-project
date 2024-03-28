package edu.ntnu.idatt2001.model.action;

import edu.ntnu.idatt2001.model.Player;

/**
 * InventoryAction class, which inherits from the interface Action.
 *
 * @author Birthe and Eline
 * @version 16.02.23
 */
public class InventoryAction implements Action {
  private final String item;

  /**
   * Constructor of the InventoryAction class.
   *
   * @param item (String)
   */
  public InventoryAction(String item) {
    this.item = item;
  }

  /**
   * Execute the adding of an item to the players inventory.
   *
   * @param player (Player)
   */
  @Override
  public void execute(Player player) {
    player.addToInventory(item);
  }

  /**
   * Accessing the inventory item, as a string.
   *
   * @return item (String)
   */
  public String getInventory() {
    return this.item;
  }

  /**
   * Returns the value of the inventory action.
   * This return is used in the ActionFactory class.
   *
   * @return value (String)
   */
  public String getValue() {
    return this.item;
  }

  /**
   * Returns a string representation of the inventory action.
   *
   * @return String (String)
   */
  public String toString() {
    return "InventoryAction{" + "item='" + item + '\'' + '}';
  }
}
package edu.ntnu.idatt2001.model.goal;

import edu.ntnu.idatt2001.model.Player;
import java.util.List;

/**
 * Class representing inventory goal, which inherits from the interface Goal.
 */
public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  /**
   * Constructor for mandatory items.
   */
  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  /**
   * Checks if the inventory fulfilled the mandatory items goal.
   *
   *@param player Player.
   */
  public boolean isFulfilled(Player player) {
    for (String mandatoryItem : mandatoryItems) {
      if (!player.getInventory().contains(mandatoryItem)) {
        return false;
      }
    }
    return true;
  }
}


package edu.ntnu.idatt2001.model.action;

import edu.ntnu.idatt2001.model.Player;

/**
 * GoldAction class, which inherits from the interface Action.
 *
 * @version 16.02.23
 */
public class GoldAction implements Action {
  private final int gold;

  /**
   * Constructor of the GoldAction class.
   *
   * @param gold (int)
   */
  public GoldAction(int gold) {
    this.gold = gold;
  }

  /**
   * Execute the change of the players gold reserves.
   *
   * @param player (Player)
   */
  @Override
  public void execute(Player player) {
    player.addGold(this.gold);
  }

  /**
   * Accessing the gold reserves of the player.
   *
   * @return this.gold (int)
   */
  public int getGold() {
    return this.gold;
  }

  /**
   * Returns the value of the gold action.
   *
   * @return value (String)
   */
  public String getValue() {
    return String.valueOf(this.gold);
  }

  /**
   * Returns a string representation of the gold action.
   *
   * @return String (String)
   */
  public String toString() {
    return "GoldAction{" + "gold=" + gold + '}';
  }
}

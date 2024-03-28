package edu.ntnu.idatt2001.model.goal;

import edu.ntnu.idatt2001.model.Player;

/**
 * Class representing gold goal, which inherits from the interface Goal.
 */
public class GoldGoal implements Goal {
  private final int minimumGold;

  /**
   * Constructor for minimum gold.
   *
   * @param minimumGold Minimum gold.
   */
  public GoldGoal(int minimumGold) {
    this.minimumGold = minimumGold;
  }

  /**
   * Checks if the gold goal is fulfilled.
   *
   * @param player Player.
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getGold() >= minimumGold;
  }
}

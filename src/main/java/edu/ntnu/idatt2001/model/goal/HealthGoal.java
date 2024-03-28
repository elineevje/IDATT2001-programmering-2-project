package edu.ntnu.idatt2001.model.goal;

import edu.ntnu.idatt2001.model.Player;

/**
 * Class representing health goal, which inherits from the interface Goal.
 */
public class HealthGoal implements Goal {
  private final int minimumHealth;

  /**
   * Constructor for health goal.
   *
   * @param minimumHealth Minimum health.
   */
  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  /**
   * Checks if the health goal is fulfilled.
   *
   * @param player Player.
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getHealth() >= minimumHealth;
  }
}

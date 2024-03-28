package edu.ntnu.idatt2001.model.goal;

import edu.ntnu.idatt2001.model.Player;

/**
 * Class representing score goal, which inherits from the interface Goal.
 */
public class ScoreGoal implements Goal {
  private final int minimumPoints;

  /**
  * Constructor for score goal.
  *
  * @param minimumPoints Minimum points.
  */
  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  /**
  * Checks if the score goal is fulfilled.
  *
  * @param player Player.
  */
  public boolean isFulfilled(Player player) {
    return player.getScore() >= minimumPoints;
  }
}

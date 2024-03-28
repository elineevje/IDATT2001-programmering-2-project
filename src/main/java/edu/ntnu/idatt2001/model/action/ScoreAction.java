package edu.ntnu.idatt2001.model.action;

import edu.ntnu.idatt2001.model.Player;

/**
 * ScoreAction class, which inherits from the interface Action.
 */

public class ScoreAction implements Action {
  private final int points;

  /**
   * Constructor of the ScoreAction class.
   *
   * @param points (int)
   */
  public ScoreAction(int points) {
    this.points = points;
  }

  /**
   * Execute the change of the player's score.
   *
   * @param player (Player)
   */
  @Override
  public void execute(Player player) {
    player.addScore(this.points);
  }

  /**
   * Accessing the score of the player.
   *
   * @return this.points (int)
   */
  public int getScore() {
    return this.points;
  }

  /**
   * Returns the value of the score action.
   *
   * @return value (String)
   */
  public String getValue() {
    return String.valueOf(this.points);
  }

  /**
   * Returns a string representation of the score action.
   *
   * @return String (String)
   */
  public String toString() {
    return "ScoreAction{" + "points=" + points + '}';
  }
}

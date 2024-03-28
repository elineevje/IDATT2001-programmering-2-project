package edu.ntnu.idatt2001.model.action;

import edu.ntnu.idatt2001.model.Player;

/**
 * HealthAction class, which inherits from the interface Action.
 *
 * @author Birthe and Eline
 * @version 16.02.23
 */
public class HealthAction implements Action {
  private final int health;

  /**
   * Constructor of the HealthAction class.
   *
   * @param health (int)
   */
  public HealthAction(int health) {
    this.health = health;
  }

  /**
   * Execute the change of the player's health.
   *
   * @param player (Player)
   */
  @Override
  public void execute(Player player) {
    player.addHealth(this.health);
  }

  /**
   * Accessing the health of the player.
   *
   * @return this.health (int)
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Returns the value of the health action.
   *
   * @return value (String)
   */
  public String getValue() {
    return String.valueOf(this.health);
  }

  /**
   * Returns a string representation of the health action.
   *
   * @return String (String)
   */
  public String toString() {
    return "HealthAction{" + "health=" + health + '}';
  }
}
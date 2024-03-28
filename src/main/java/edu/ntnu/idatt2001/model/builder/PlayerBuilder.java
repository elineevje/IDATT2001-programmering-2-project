package edu.ntnu.idatt2001.model.builder;

import edu.ntnu.idatt2001.model.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Builder class for the class Player.
 * Building a player with the required parameters name, health and gold.
 * The optional parameters are score and inventory.
 */
public class PlayerBuilder {
  // Required parameters
  private final String name;
  private final int health;
  private final int gold;
  // Optional parameters
  private int score = 0;
  private List<String> inventory = new ArrayList<>();

  /**
   * Constructor of the class Builder, creating a new builder with the required parameters.
   *
   * @param name   (String)
   * @param health (int)
   * @param gold   (int)
   */
  public PlayerBuilder(String name, int health, int gold) {
    this.name = name;
    this.health = health;
    this.gold = gold;
  }

  /**
   * Accessing the score of the player inside the builder.
   *
   * @return score (int)
   */
  public PlayerBuilder score(int score) {
    this.score = score;
    return this;
  }

  /**
   * Accessing the player's inventory list inside the builder.
   *
   * @return inventory (List<>)
   */
  public PlayerBuilder inventory(List<String> inventory) {
    this.inventory = inventory;
    return this;
  }

  /**
   * Building the player.
   *
   * @return player (Player)
   */
  public Player build() {
    return new Player(this);
  }

  /**
   * Accessing the name of the player inside the builder.
   *
   * @return name (String)
   */
  public String getName() {
    return name;
  }

  /**
   * Accessing the health of the player inside the builder.
   *
   * @return health (int)
   */
  public int getHealth() {
    return health;
  }

  /**
   * Accessing the gold of the player inside the builder.
   *
   * @return gold (int)
   */
  public int getGold() {
    return gold;
  }

  /**
   * Accessing the score of the player inside the builder.
   *
   * @return score (int)
   */
  public int getScore() {
    return score;
  }

  /**
   * Accessing the inventory of the player inside the builder.
   *
   * @return inventory (List<>)
   */
  public List<String> getInventory() {
    return inventory;
  }

  /**
   * Equals method for the class PlayerBuilder.
   *
   * @param o (Object)
   * @return boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlayerBuilder that)) {
      return false;
    }
    return health == that.health && gold == that.gold && score == that.score
        && Objects.equals(name, that.name) && Objects.equals(inventory, that.inventory);
  }

  /**
   * Hashcode method for the class PlayerBuilder.
   *
   * @return int
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, health, gold, score, inventory);
  }
}

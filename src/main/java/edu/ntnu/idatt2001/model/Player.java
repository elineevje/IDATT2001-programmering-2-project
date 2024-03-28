package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.model.builder.PlayerBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Player class.
 * Represents a player in the game Paths, with its characteristic influencing the story.
 * These characteristics include the player's health, score, gold reserves and inventory.
 *
 */


public class Player {
  private final String name;
  private int health;
  private int score;
  private int gold;
  private final List<String> inventory;


  /**
   * Constructor of the class Player, creating a new player.
   * The player is given a name, health, score and gold reserves.
   * Checking for illegal arguments.
   *
   * @param name   The name of the player (String)
   * @param health The health of the player as a positive integer(int)
   * @param score  The player's score as a positive integer(int)
   * @param gold   The player's amount of gold as positive integer(int)
   * @throws IllegalArgumentException "The player can not have an empty name."
   * @throws IllegalArgumentException "The health of the player can not be less than zero."
   * @throws IllegalArgumentException "The player's score can not be less than zero."
   * @throws IllegalArgumentException "The player's amount of gold can not be less than zero."
   */
  public Player(String name, int health, int score, int gold) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("The player can not have an empty name.");
    }
    if (health < 0) {
      throw new IllegalArgumentException("The health of the player can not be less than zero.");
    }
    if (score < 0) {
      throw new IllegalArgumentException("The player's score can not be less than zero.");
    }
    if (gold < 0) {
      throw new IllegalArgumentException("The player's amount of gold can not be less than zero.");
    }
    this.name = name;
    this.health = health;
    this.score = score;
    this.gold = gold;
    this.inventory = new ArrayList<>();
  }

  /**
   * Alternative constructor of the class Player, creating a new player with a builder.
   * Checking for illegal arguments.
   *
   * @param builder (Builder)
   * @throws IllegalArgumentException "The player can not have an empty name."
   * @throws IllegalArgumentException "The health of the player can not be less than zero."
   * @throws IllegalArgumentException "The player's score can not be less than zero."
   * @throws IllegalArgumentException "The player's amount of gold can not be less than zero."
   */
  public Player(PlayerBuilder builder) {
    if (builder.getName() == null || builder.getName().isBlank()) {
      throw new IllegalArgumentException("The player can not have an empty name.");
    }
    if (builder.getHealth() < 0) {
      throw new IllegalArgumentException("The health of the player can not be less than zero.");
    }
    if (builder.getScore() < 0) {
      throw new IllegalArgumentException("The player's score can not be less than zero.");
    }
    if (builder.getGold() < 0) {
      throw new IllegalArgumentException("The player's amount of gold can not be less than zero.");
    }
    this.name = builder.getName();
    this.health = builder.getHealth();
    this.score = builder.getScore();
    this.gold = builder.getGold();
    this.inventory = builder.getInventory();
  }

  /**
   * Accessing the name of the player.
   *
   * @return name (String)
   */
  public String getName() {
    return this.name;
  }

  /**
   * Accessing the health of the player.
   *
   * @return health (int)
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Accessing the score of the player.
   *
   * @return score (int)
   */
  public int getScore() {
    return score;
  }

  /**
   * Accessing the number of gold the player has collected.
   *
   * @return gold (int)
   */
  public int getGold() {
    return gold;
  }

  /**
   * Accessing the players collected inventory.
   *
   * @return inventory (List<>)
   */
  public List<String> getInventory() {
    return inventory;
  }

  /**
   * Adding to the players' health.
   * Checking for the total sum of health is less than zero.
   *
   * @param health (int)
   * @throws IllegalArgumentException "The players total health can not be less than zero."
   */
  public void addHealth(int health) {
    if (this.health + health < 0) {
      throw new IllegalArgumentException("The players total health can not be less than zero.");
    }
    this.health += health;
  }

  /**
   * Adding to the players' health.
   * Checking if the health is less than zero.
   *
   * @param score (int)
   * @throws IllegalArgumentException "The sum of the player's score can not be less than zero."
   */
  public void addScore(int score) {
    if (this.score + score < 0) {
      throw new IllegalArgumentException("The sum of the player's score can not be less "
          + "than zero.");
    }
    this.score += score;
  }


  /**
   * Adding to the players' amount of gold.
   * Checking if the amount of gould is less than zero.
   *
   * @param gold (int)
   * @throws IllegalArgumentException "The total amount of gold can not be less than zero."
   */
  public void addGold(int gold) {
    if (this.gold + gold < 0) {
      throw new IllegalArgumentException("The total amount of gold can not be less than zero.");
    }
    this.gold += gold;
  }

  /**
   * Adding items to the inventory.
   * Checking if the item is null or not.
   * Checking if the item already exist in the inventory.
   *
   * @param item (String)
   * @throws IllegalArgumentException "It is not possible to add an empty item to the inventory!"
   * @throws IllegalArgumentException "The item already exist in the inventory."
   */
  public void addToInventory(String item) {
    if (item == null) {
      throw new IllegalArgumentException("It is not possible to add an empty item "
          + "to the inventory.");
    }
    if (itemAlreadyExist(item)) {
      throw new IllegalArgumentException("The item already exist in the inventory.");
    }
    this.inventory.add(item);
  }

  /**
   * Private help method to check if the item already exist in the inventory.
   *
   * @param item (String)
   * @return true if the item already exist in the inventory or false if not. (boolean)
   */
  private boolean itemAlreadyExist(String item) {
    for (String i : inventory) {
      if (i.equalsIgnoreCase(item)) {
        return true;
      }
    }
    return false;
  }
}



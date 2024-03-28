package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.model.goal.Goal;
import java.util.List;

/**
 * Game class. Represents the game.
 */
public class Game {

  /**
   * Defining all the object variables for game.
   */
  private final Player player;
  private final Story story;
  private final List<Goal> goals;

  /**
   * Constructor for player.

   * @param player Player. (Player)
   * @param story Story. (Story)
   * @param goals Goals. (List<Goal>)
   * @throws IllegalArgumentException "Player can not be empty."
   * @throws IllegalArgumentException "Story can not be empty."
   * @throws IllegalArgumentException "Goals can not be empty."
   */
  public Game(Player player, Story story, List<Goal> goals) {
    if (player == null) {
      throw new IllegalArgumentException("Player can not be empty.");
    }
    if (story == null) {
      throw new IllegalArgumentException("Story can not be empty.");
    }
    if (goals == null) {
      throw new IllegalArgumentException("Goals can not be empty.");
    }
    this.player = player;
    this.story = story;
    this.goals = goals;
  }

  /**
   * Get method for player.

   * @return Player.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Get method for story.

   * @return Story.
   */
  public Story getStory() {
    return story;
  }

  /**
   * Get method for goals.

   * @return Goals.
   */
  public List<Goal> getGoals() {
    return goals;
  }


  /**
   * Method that returns the opening passage of the history.

   * @return Opening passage.
   */
  public Passage begin() {
    return story.getOpeningPassage();
  }

  /**
   * Method that returns the passage that matches the given link.

   * @param link Link.
   * @return The passage that matches the given link.
   * @throws IllegalArgumentException "The link is empty and can therefor not be added."
   */
  public Passage go(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("The link is empty and can therefor not be added.");
    }
    return story.getPassage(link);
  }
}

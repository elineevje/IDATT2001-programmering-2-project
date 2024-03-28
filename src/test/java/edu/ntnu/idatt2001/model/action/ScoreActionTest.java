package edu.ntnu.idatt2001.model.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test class for the ScoreAction class.
 */
class ScoreActionTest {
  static Player player;

  @BeforeEach
  void setup() {
    String testName = "Hannah";
    int testHealth = 89;
    int testScore = 54;
    int testGold = 43;
    player = new Player(testName, testHealth, testScore, testGold);
  }

  @Nested
  @DisplayName("ScoreAction class test.")
  class ScoreActionTests {

    @Test
    @DisplayName("Testing executing the action of adding a score to the player's current score.")
    void testing_scoreActionExecute_by_adding_score() {
      int scoreToAdd = 10;
      ScoreAction scoreAction = new ScoreAction(scoreToAdd);
      scoreAction.execute(player);
      assertEquals(64, player.getScore());
    }

    @Test
    @DisplayName("Testing executing the action of adding "
        + "a negative score to the player's current score.")
    void testing_scoreActionExecute_by_removing_score() {
      int scoreToRemove = -10;
      ScoreAction scoreAction = new ScoreAction(scoreToRemove);
      scoreAction.execute(player);
      assertEquals(44, player.getScore());
    }
  }
}
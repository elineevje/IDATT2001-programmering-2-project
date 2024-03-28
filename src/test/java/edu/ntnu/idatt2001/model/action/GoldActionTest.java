package edu.ntnu.idatt2001.model.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test class for the GoldAction class.
 */
class GoldActionTest {
  static Player player;
  static GoldAction goldAction;

  @BeforeEach
  void setup() {
    String testName = "Hannah";
    int testHealth = 89;
    int testScore = 54;
    int testGold = 43;
    player = new Player(testName, testHealth, testScore, testGold);
  }
  @Nested
  @DisplayName("GoldActionT class tests.")
  class assertEqualsTests {

    @Test
    @DisplayName("Testing executing the action of adding gold to the player's gold reservoir.")
    void testing_the_goldActionExecuteT_by_adding_gold() {
      int goldToAdd = 10;
      goldAction = new GoldAction(goldToAdd);
      goldAction.execute(player);
      assertEquals(53, player.getGold());
    }

    @Test
    @DisplayName("Testing executing the action of adding a negative amount of gold"
        + " to the player's gold reservoir.")
    void testing_the_goldActionExecute_by_removing_gold() {
      int goldToRemove = -10;
      goldAction = new GoldAction(goldToRemove);
      goldAction.execute(player);
      assertEquals(33, player.getGold());
    }
  }
}
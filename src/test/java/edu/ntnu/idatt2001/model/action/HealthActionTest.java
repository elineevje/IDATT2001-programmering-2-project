package edu.ntnu.idatt2001.model.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test class for the HealthAction class.
 */
class HealthActionTest {
  static Player player;
  static HealthAction healthAction;

  @BeforeEach
  void setup() {
    String testName = "Hannah";
    int testHealth = 89;
    int testScore = 54;
    int testGold = 43;
    player = new Player(testName, testHealth, testScore, testGold);
  }

  @Nested
  @DisplayName("HealthAction class test.")
  class HealthActionTests {

    @Test
    @DisplayName("Testing executing the action of adding health to the player's health.")
    void testing_healthActionExecute_by_adding_health() {
      int healthToAdd = 10;
      healthAction = new HealthAction(healthToAdd);
      healthAction.execute(player);
      assertEquals(99, player.getHealth());
    }

    @Test
    @DisplayName("Testing executing the action of adding "
        + "a negative health score to the player's health.")
    void testing_HealthActionExecute_by_removing_health() {
      int healthToRemove = -10;
      healthAction = new HealthAction(healthToRemove);
      healthAction.execute(player);
      assertEquals(79, player.getHealth());
    }
  }

  @Test
  @DisplayName("Testing accessing the value of a health action.")
  void testing_getValue() {
    int healthToAdd = 10;
    healthAction = new HealthAction(healthToAdd);
    assertEquals("10", healthAction.getValue());
  }
}
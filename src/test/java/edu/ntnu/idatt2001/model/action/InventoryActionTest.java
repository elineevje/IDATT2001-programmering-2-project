package edu.ntnu.idatt2001.model.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test class for the InventoryAction class.
 */
class InventoryActionTest {
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
  @DisplayName("InventoryAction class test")
  class InventoryActionTests {

    @Test
    @DisplayName("Testing executing the action of adding an item to the player's inventory.")
    void testing_the_inventoryActionExecute_by_adding_an_item() {
      String itemToAdd = "Stone";
      InventoryAction inventoryAction = new InventoryAction(itemToAdd);
      inventoryAction.execute(player);
      assertEquals("Stone", player.getInventory().get(0));
    }
  }
}
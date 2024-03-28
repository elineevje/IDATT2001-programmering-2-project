package edu.ntnu.idatt2001.model.goal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import edu.ntnu.idatt2001.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for the InventoryGoal class.
 */
class InventoryGoalTest {

  static InventoryGoal inventoryGoal;
  static Player player;

  @BeforeEach
  void setUp() {
    player = new Player("Martin", 10, 10, 10);
    player.addToInventory("Hammer");
    player.addToInventory("Flower");
    player.addToInventory("Coin");
    player.addToInventory("Plant");
    player.addToInventory("iPhone");
  }

  @Nested
  @DisplayName("Testing the is fulfilled method with the inventory goal.")
  class IsInventoryGoalFulfilledTests {
    @Test
    @DisplayName("Testing if the method returns true when the mandatory items are fulfilled.")
    void isFulfilledTest() {
      List<String> mandatoryItems = Arrays.asList("Flower", "Coin", "Hammer");
      inventoryGoal = new InventoryGoal(mandatoryItems);
      assertTrue(inventoryGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Testing if the method returns false when the mandatory items are not fulfilled.")
    void isNotFulfilledTest() {
      List<String> mandatoryItems = Arrays.asList("Flower", "Coin", "Hammer", "PC");
      inventoryGoal = new InventoryGoal(mandatoryItems);
      assertFalse(inventoryGoal.isFulfilled(player));
    }
  }
}
package edu.ntnu.idatt2001.model.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.model.action.Action;
import edu.ntnu.idatt2001.model.action.GoldAction;
import edu.ntnu.idatt2001.model.action.HealthAction;
import edu.ntnu.idatt2001.model.action.InventoryAction;
import edu.ntnu.idatt2001.model.action.ScoreAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the ActionFactory class.
 */
public class ActionFactoryTest {

  @Test
  @DisplayName("Testing that get method returns a HealthAction object for action type 'Health'")
  void testing_the_GetHealthAction() {
    ActionFactory factory = new ActionFactory();
    Action action = factory.get("Health", "10");
    assertTrue(action instanceof HealthAction);
  }

  @Test
  @DisplayName("Testing that get method returns a GoldAction object for action type 'Gold'")
  void testGetGoldAction() {
    ActionFactory factory = new ActionFactory();
    Action action = factory.get("Gold", "50");
    assertTrue(action instanceof GoldAction);
  }

  @Test
  @DisplayName("Testing that get method returns a ScoreAction object for action type 'Score'")
  void testGetScoreAction() {
    ActionFactory factory = new ActionFactory();
    Action action = factory.get("Score", "100");
    assertTrue(action instanceof ScoreAction);
  }

  @Test
  @DisplayName("Testing that get method returns an InventoryAction object for"
      + " action type 'Inventory'")
  void testGetInventoryAction() {
    ActionFactory factory = new ActionFactory();
    Action action = factory.get("Inventory", "sword");
    assertTrue(action instanceof InventoryAction);
  }

  @Test
  @DisplayName("Testing that get method throws an exception for an invalid action type")
  void testInvalidActionType() {
    ActionFactory factory = new ActionFactory();
    assertThrows(IllegalArgumentException.class, () -> factory.get("InvalidType",
        "value"));
  }

  @Test
  @DisplayName("Testing accessing the value of a health action.")
  void testing_accessing_the_value_of_an_action() {
    ActionFactory factory = new ActionFactory();
    Action action = factory.get("Gold", "50");
    System.out.println(action.getValue());
    assertEquals("50", action.getValue());
  }
}

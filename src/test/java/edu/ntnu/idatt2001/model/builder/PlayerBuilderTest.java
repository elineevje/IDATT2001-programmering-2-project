package edu.ntnu.idatt2001.model.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idatt2001.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the PlayerBuilder class.
 */
public class PlayerBuilderTest {
  @Nested
  @DisplayName("Testing accessing the parameters of PlayerBuilder class.")
  class AccessorPlayerBuilderTests {
    @Test
    @DisplayName("Testing accessing the name of a player with a name, health, score and gold.")
    void accessingNameOfPlayerBuilderTest() {
      String testName = "Hannah";
      int testHealth = 89;
      int testScore = 54;
      int testGold = 43;
      Player player = new PlayerBuilder(testName, testHealth, testGold)
              .score(testScore)
              .build();
      assertEquals(testName, player.getName());
    }

    @Test
    @DisplayName("Testing accessing the health of a player with a name, health, score and gold.")
    void accessingHealthOfPlayerBuilderTest() {
      String testName = "Hannah";
      int testHealth = 89;
      int testScore = 54;
      int testGold = 43;
      Player player = new PlayerBuilder(testName, testHealth, testGold)
              .score(testScore)
              .build();
      assertEquals(testHealth, player.getHealth());
    }

    @Test
    @DisplayName("Testing accessing the gold of a player with a name, health, score and gold.")
    void accessingGoldOfPlayerBuilderTest() {
      String testName = "Hannah";
      int testHealth = 89;
      int testScore = 54;
      int testGold = 43;
      Player player = new PlayerBuilder(testName, testHealth, testGold)
              .score(testScore)
              .build();
      assertEquals(testGold, player.getGold());
    }

    @Test
    @DisplayName("Testing accessing the score of a player with a name, health, score and gold.")
    void accessingScoreOfPlayerBuilderTest() {
      String testName = "Hannah";
      int testHealth = 89;
      int testScore = 54;
      int testGold = 43;
      Player player = new PlayerBuilder(testName, testHealth, testGold)
              .score(testScore)
              .build();
      assertEquals(testScore, player.getScore());
    }

    @Test
    @DisplayName("Testing accessing the inventory of a player with a name, health and gold" +
            ", by also adding an item to the inventory.")
    void addingAndAccessingInventoryOfPlayerBuilderTest() {
      String testName = "Hannah";
      int testHealth = 89;
      int testGold = 43;
      String testItem = "test item";
      List<String> testInventory = new ArrayList<>();
      testInventory.add(testItem);
      Player player = new PlayerBuilder(testName, testHealth, testGold)
              .inventory(testInventory)
              .build();
      assertEquals(testItem, player.getInventory().get(0));
    }

    @Test
    @DisplayName("Testing accessing the inventory of a player with a name, health, score and gold" +
            ", by also adding an item to the inventory.")
    void accessingInventoryOfAPlayerBuilderWithAllAttributesTest() {
      String testName = "Hannah";
      int testHealth = 89;
      int testScore = 54;
      int testGold = 43;
      String testItem = "test item";
      List<String> testInventory = new ArrayList<>();
      testInventory.add(testItem);
      Player player = new PlayerBuilder(testName, testHealth, testGold)
              .score(testScore)
              .inventory(testInventory)
              .build();
      assertEquals(testItem, player.getInventory().get(0));
    }

    @Test
    @DisplayName("Testing adding items to inventory of a player with a name, health, score and gold" +
            "by checking the size of the list.")
    void testingAddingAnInventoryByTheSizeOfTheList() {
      String testName = "Hannah";
      int testHealth = 89;
      int testGold = 43;
      int testScore = 54;
      String testItem = "test item";
      List<String> testInventory = new ArrayList<>();
      testInventory.add(testItem);
      Player player = new PlayerBuilder(testName, testHealth, testGold)
              .score(testScore)
              .inventory(testInventory)
              .build();
      assertEquals(1, player.getInventory().size());
    }
  }

  @Nested
  @DisplayName("Testing exception handling in the PlayerBuilder class.")
  class ExceptionHandlingPlayerBuilderTests {
    @Test
    @DisplayName("Testing the exception handling when trying to add an already existing item to the inventory.")
    void exceptionHandlingItemAlreadyExistsTest() {
      String testName = "Hannah";
      int testHealth = -89;
      int testGold = 43;
      String testItem = "test item";
      List<String> testInventory = new ArrayList<>();
      testInventory.add(testItem);
      assertThrows(IllegalArgumentException.class, () -> new PlayerBuilder(testName, testHealth, testGold)
              .inventory(testInventory)
              .build());
    }
  }
}

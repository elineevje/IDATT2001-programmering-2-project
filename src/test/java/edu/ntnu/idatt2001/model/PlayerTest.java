package edu.ntnu.idatt2001.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Player class.
 */
class PlayerTest {

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
  @DisplayName("Testing the methods of the Player class with valid arguments.")
  class PlayerMethodTests {

    @Test
    @DisplayName("Testing creating a player with valid arguments.")
    void creating_a_player_with_valid_arguments() {
      String testName = "Hannah";
      int testHealth = 89;
      int testScore = 54;
      int testGold = 43;
      try {
        player = new Player(testName, testHealth, testScore, testGold);
      } catch (Exception e) {
        fail("Player was not created.");
      }
    }

    @Test
    @DisplayName("Testing accessing player's name.")
    void testing_accessing_the_name_of_the_player_expecting_the_testName() {
      assertEquals("Hannah", player.getName());
    }

    @Test
    @DisplayName("Testing accessing the health of the player.")
    void testing_accessing_the_health_of_the_player_expecting_the_testHealth() {
      assertEquals(89, player.getHealth());
    }


    @Test
    @DisplayName("Testing accessing the player's score.")
    void testing_accessing_the_score_of_the_player_expecting_the_testScore() {
      assertEquals(54, player.getScore());
    }

    @Test
    @DisplayName("Testing accessing the player's gold reservoir.")
    void testing_accessing_the_players_gold_expecting_the_testGold() {
      assertEquals(43, player.getGold());
    }

    @Test
    @DisplayName("Testing accessing the empty inventory of the player.")
    void testing_accessing_the_inventory_of_the_player_expecting_an_empty_inventory() {
      assertTrue(player.getInventory().isEmpty());
    }

    @Test
    @DisplayName("Testing adding to the player's health.")
    void testing_adding_3_to_the_players_health_score_expecting_92() {
      int addHealth = 3;
      player.addHealth(addHealth);
      assertEquals(92, player.getHealth());
    }

    @Test
    @DisplayName("Testing adding the player's score.")
    void testing_adding_5_to_the_players_score_expecting_59() {
      int addScore = 5;
      player.addScore(addScore);
      assertEquals(59, player.getScore());
    }

    @Test
    @DisplayName("Testing adding the player's amount of gold.")
    void testing_adding_70_to_the_players_gold_score_expecting113() {
      int addGold = 70;
      player.addGold(addGold);
      assertEquals(113, player.getGold());
    }

    @Test
    @DisplayName("Testing adding items to the inventory, by the size of the list.")
    void testing_adding_2_items_to_the_inventory_expecting_2_items() {
      String testItem1 = "test item 1";
      String testItem2 = "test item 2";
      player.addToInventory(testItem1);
      player.addToInventory(testItem2);
      assertEquals(2, player.getInventory().size());
    }

    @Test
    @DisplayName("Testing adding items to the inventory, by index of the list.")
    void testing_adding_2_items_to_the_inventory_expecting_testItem2() {
      String testItem1 = "test item 1";
      String testItem2 = "test item 2";
      player.addToInventory(testItem1);
      player.addToInventory(testItem2);
      assertEquals(testItem2, player.getInventory().get(1));
    }
  }

  @Nested
  @DisplayName("Testing exception handling in the Player class.")
  class ExceptionHandlingTests {

    @Test
    @DisplayName("Testing the exceptions thrown in the constructor of the Player class,"
        + " for a player without a name.")
    void throws_IEA_with_a_nameless_player() {
      String emptyNameTest = null;
      int testHealth = 89;
      int testScore = 54;
      int testGold = 43;
      assertThrows(IllegalArgumentException.class, () ->
          new Player(emptyNameTest, testHealth, testScore, testGold));
    }

    @Test
    @DisplayName("Testing the exceptions thrown in the constructor of the Player class,"
        + " for an empty name of the player.")
    void throws_IEA_with_an_empty_name_of_the_player() {
      String emptyNameTest = "";
      int testHealth = 89;
      int testScore = 54;
      int testGold = 43;
      assertThrows(IllegalArgumentException.class, () ->
          new Player(emptyNameTest, testHealth, testScore, testGold));
    }

    @Test
    @DisplayName("Testing the exceptions thrown in the constructor of the Player class,"
        + " for a player with a negative health score.")
    void throws_IEA_with_a_negative_health_score_for_the_player() {
      String testName = "Hannah";
      int negativeTestHealth = -3;
      int testScore = 54;
      int testGold = 43;
      assertThrows(IllegalArgumentException.class, () ->
          new Player(testName, negativeTestHealth, testScore, testGold));
    }

    @Test
    @DisplayName("Testing the exceptions thrown in the constructor of the Player class,"
        + " for a negative player score.")
    void throws_IEA_with_a_negative_score_for_the_player() {
      String testName = "Hannah";
      int testHealth = 92;
      int negativeTestScore = -54;
      int testGold = 43;
      assertThrows(IllegalArgumentException.class, () ->
          new Player(testName, testHealth, negativeTestScore, testGold));
    }

    @Test
    @DisplayName("Testing the exceptions thrown in the constructor of the Player class,"
        + " for a player with a negative amount of gold.")
    void throws_IEA_with_a_negative_gold_score_for_the_player() {
      String testName = "Hannah";
      int testHealth = 92;
      int testScore = 54;
      int negativeTestGold = -43;
      assertThrows(IllegalArgumentException.class, () ->
          new Player(testName, testHealth, testScore, negativeTestGold));
    }

    @Test
    @DisplayName("Testing adding a negative health score,"
        + " such as the player's health gets negative.")
    void throws_IEA_with_adding_a_negative_number_for_accessing_a_negative_health_score() {
      int addingNegativeHealth = -100;
      assertThrows(IllegalArgumentException.class, () -> player.addHealth(addingNegativeHealth));
    }

    @Test
    @DisplayName("Testing adding a negative score, such as the player's score gets negative.")
    void throws_IEA_with_adding_a_negative_number_for_accessing_a_negative_scoreadd() {
      int addingNegativeScore = -100;
      assertThrows(IllegalArgumentException.class, () -> player.addScore(addingNegativeScore));
    }

    @Test
    @DisplayName("Testing adding a negative amount of gold,"
        + " such as the gold reservoir gets negative.")
    void throws_IEA_with_adding_a_negative_number_for_accessing_a_negative_gold_score() {
      int addingNegativeGold = -100;
      assertThrows(IllegalArgumentException.class, () -> player.addGold(addingNegativeGold));
    }

    @Test
    @DisplayName("Testing adding nothing to the inventory, by adding an empty item.")
    void throws_IEA_with_adding_nothing_to_the_inventory() {
      String emptyItem = null;
      assertThrows(IllegalArgumentException.class, () -> player.addToInventory(emptyItem));
    }

    @Test
    @DisplayName("Testing adding an already existing item to the inventory.")
    void throws_IEA_with_adding_an_already_existing_item_to_the_inventory() {
      String testItem = "test item";
      player.addToInventory(testItem);
      assertThrows(IllegalArgumentException.class, () -> player.addToInventory(testItem));
    }
  }
}
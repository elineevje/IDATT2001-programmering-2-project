package edu.ntnu.idatt2001.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import edu.ntnu.idatt2001.model.goal.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the Game class.
 */
class GameTest {

  static Game testGame;
  static Link testLink;
  static List<Goal> testGoals;

  @BeforeEach
  void setUp() {
    Player testPlayer = new Player("Player name", 10, 12, 13);
    Passage testOpeningPassage = new Passage("Passage title", "Passage content");

    Story testStory = new Story("Story title", testOpeningPassage);

    testGoals = new ArrayList<>();

    testGame = new Game(testPlayer, testStory, testGoals);

    String testText = "Link text";
    String testReference = "Link reference";
    testLink = new Link(testText, testReference);
  }

  @Nested
  @DisplayName("Testing the methods of the Game class with valid arguments.")
  class GameMethodTests {
    @Test
    @DisplayName("Testing creating a game with valid arguments.")
    void creatingAGameWitValidArguments() {
      testGoals = new ArrayList<>();
      Player testPlayer = new Player("Player name", 10, 12, 13);
      Passage testOpeningPassage = new Passage("Passage title", "Passage content");
      Story testStory = new Story("Story title", testOpeningPassage);

      try {
        testGame = new Game(testPlayer, testStory, testGoals);
      } catch (Exception e) {
        fail("Game was not created.");
      }
    }

    @Test
    @DisplayName("Testing accessing the game's player's name.")
    void getPlayersNameTest() {
      assertEquals("Player name", testGame.getPlayer().getName());
    }

    @Test
    @DisplayName("Testing accessing the game's player's health.")
    void getPlayersHealthTest() {
      assertEquals(10, testGame.getPlayer().getHealth());
    }

    @Test
    @DisplayName("Testing accessing the game's player's score.")
    void getPlayersScoreTest() {
      assertEquals(12, testGame.getPlayer().getScore());
    }

    @Test
    @DisplayName("Testing accessing the game's player's gold.")
    void getPlayerGoldTest() {
      assertEquals(13, testGame.getPlayer().getGold());
    }

    @Test
    @DisplayName("Testing accessing the game's story's title.")
    void getStoryTitleTest() {
      assertEquals("Story title", testGame.getStory().getTitle());
    }

    @Test
    @DisplayName("Testing accessing the game's story's passage's title.")
    void getStoryPassagesTitleTest() {
      assertEquals("Passage title", testGame.getStory().getOpeningPassage().getTitle());
    }

    @Test
    @DisplayName("Testing accessing the game's story's passage's content.")
    void getStoryPassagesContentTest() {
      assertEquals("Passage content", testGame.getStory().getOpeningPassage().getContent());
    }

    @Test
    @DisplayName("Testing accessing the game's goals. ")
    void getGoalsTest() {
      assertTrue(testGame.getGoals().isEmpty());
    }

    @Test
    @DisplayName("Testing method for begin.")
    void beginTest() {
      assertEquals(testGame.getStory().getOpeningPassage(), testGame.begin());
    }

    @Test
    @DisplayName("Testing method for go.")
    void goTest() {
      assertEquals(testGame.getStory().getPassage(testLink), testGame.go(testLink));
    }
  }

  @Nested
  @DisplayName("Testing exception handling in the Game class.")
  class ExceptionHandlingTests {
    @Test
    @DisplayName("Testing the exception thrown for player in the constructor of the Game class.")
    void exceptionHandlingPlayerTest() {
      Passage testPassage = new Passage("Passage title", "Passage content");
      Story testStory = new Story("Story title", testPassage);
      assertThrows(IllegalArgumentException.class, () -> new Game(null, testStory, testGoals));
    }

    @Test
    @DisplayName("Testing the exception thrown for story in the constructor of the Game class.")
    void exceptionHandlingStoryTest() {
      Player testPlayer = new Player("Player name", 10, 12, 20);
      assertThrows(IllegalArgumentException.class, () -> new Game(testPlayer, null, testGoals));
    }

    @Test
    @DisplayName("Testing the exception thrown for goals in the constructor of the Game class.")
    void exceptionHandlingGoalsTest() {
      Passage testPassage = new Passage("Passage title", "Passage content");
      Story testStory = new Story("Story title", testPassage);
      Player testPlayer = new Player("Player name", 10, 12, 20);
      assertThrows(IllegalArgumentException.class, () -> new Game(testPlayer, testStory, null));
    }

    @Test
    @DisplayName("Testing the exception thrown when inserting an invalid parameter in the go method.")
    void exceptionHandlingGoTest() {
      assertThrows(IllegalArgumentException.class, () -> testGame.go(null));
    }
  }
}
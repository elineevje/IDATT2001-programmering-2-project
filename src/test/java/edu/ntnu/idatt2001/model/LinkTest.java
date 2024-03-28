package edu.ntnu.idatt2001.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import edu.ntnu.idatt2001.model.action.GoldAction;
import edu.ntnu.idatt2001.model.action.HealthAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Link class.
 */
class LinkTest {

  static Link testLink;
  static GoldAction testGoldAction;
  static HealthAction testHealthAction;

  @BeforeEach
  void setUp() {
    String testText = "Link text";
    String testReference = "Link reference";
    testLink = new Link(testText, testReference);

    int testGoldActionNumber = 10;
    testGoldAction = new GoldAction(testGoldActionNumber);

    int testHealthActionNumber = 20;
    testHealthAction = new HealthAction(testHealthActionNumber);
  }

  @Nested
  @DisplayName("Testing methods of the Link class with valid arguments.")
  class LinkMethodTests {
    @Test
    @DisplayName("Testing creating a link with valid arguments.")
    void creatingALinkWitValidArguments() {
      String testText = "Link text";
      String testReference = "Link reference";
      try {
        testLink = new Link(testText,testReference);
      } catch (Exception e) {
        fail("Link was not created.");
      }
    }
    @Test
    @DisplayName("Testing accessing the link's text.")
    void getTextTest() {
      assertEquals("Link text", testLink.getText());
    }

    @Test
    @DisplayName("Testing accessing the link's reference.")
    void getReferenceTest() {
      assertEquals("Link reference", testLink.getReference());
    }

    @Test
    @DisplayName("Testing accessing the link's actions.")
    void getActionsTest() {
      assertTrue(testLink.getActions().isEmpty());
    }

    @Test
    @DisplayName("Testing method for add action, by the size of the list.")
    void checkIfItemsAreAddedTest() {
      testLink.addAction(testGoldAction);
      testLink.addAction(testHealthAction);
      assertEquals(2, testLink.getActions().size());
    }

    @Test
    @DisplayName("Testing method for add action, by the index of the list.")
    void checkIfCorrectItemsAreAdded() {
      testLink.addAction(testGoldAction);
      testLink.addAction(testHealthAction);
      assertEquals(testHealthAction, testLink.getActions().get(1));
    }

    @Test
    @DisplayName("Negative test of the add actions method. Adding items to the list, then using assert false to see" +
            "if it is empty.")
    void addActionFalse() {
      testLink.addAction(testGoldAction);
      testLink.addAction(testHealthAction);
      assertFalse(testLink.getActions().isEmpty());
    }
  }

  @Nested
  @DisplayName("Testing exception handling in the Link class.")
  class ExceptionHandlingTests {
    @Test
    @DisplayName("Testing the exception thrown for text in the constructor of the Link class.")
    void exceptionHandlingTextTest() {
      String testText = "";
      String testReference = "Link reference";
      assertThrows(IllegalArgumentException.class, () -> new Link(testText, testReference));
    }

    @Test
    @DisplayName("Testing the exception thrown for reference in the constructor of the Link class.")
    void exceptionHandlingReferenceTest() {
      String testText = "Link text";
      String testReference = "";
      assertThrows(IllegalArgumentException.class, () -> new Link(testText, testReference));
    }

    @Test
    @DisplayName("Testing the exception thrown when inserting an invalid parameter in the add action method.")
    void exceptionHandlingAddActionTest() {
      assertThrows(IllegalArgumentException.class, () -> testLink.addAction(null));
    }
  }
}
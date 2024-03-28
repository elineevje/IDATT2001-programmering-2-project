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
 * Test class for the Passage class.
 */
class PassageTest {

  static Passage passage;

  @BeforeEach
  void setup() {
    String testTitle = "Forest";
    String testContent = "In the forest";
    passage = new Passage(testTitle, testContent);
  }

  @Nested
  @DisplayName("Testing the methods of the Passage Class")
  class PassageMethodTests {

    @Test
    @DisplayName("Testing creating a passage with valid arguments.")
    void creatingAPassageWithValidArguments() {
      String testTitle = "Forest";
      String testContent = "In the forest";
      try {
        passage = new Passage(testTitle, testContent);
      } catch (Exception e) {
        fail("Passage was not created.");
      }
    }

    @Test
    @DisplayName("Testing accessing the title of the passage.")
    void testing_accessing_the_title_of_the_passage_expecting_testTitle() {
      assertEquals("Forest", passage.getTitle());
    }

    @Test
    @DisplayName("Testing accessing the content of the passage.")
    void testing_accessing_the_content_of_the_passage_expecting_the_testContent() {
      assertEquals("In the forest", passage.getContent());
    }

    @Test
    @DisplayName("Testing accessing the empty list of links of the passage.")
    void testing_accessing_the_links_of_the_passage_expecting_an_empty_link() {
      assertTrue(passage.getLinks().isEmpty());
    }

    @Test
    @DisplayName("Testing adding links to the passage "
        + "and checking that the list of links is not empty.")
    void testing_adding_a_link_to_the_passage_expecting_true() {
      String testTitle = "Sky";
      String testContent = "In to the sky";
      Link testLink = new Link(testTitle,testContent);
      assertTrue(passage.addLink(testLink));
    }

    @Test
    @DisplayName("Testing adding links to the passage, by the size of the list.")
    void addLinksBySizeTest() {
      Link link1 = new Link("Sky", "In to the sky");
      passage.addLink(link1);
      assertEquals(1, passage.getLinks().size());
    }

    @Test
    @DisplayName("Testing if the passage has links, by adding two links.")
    void testing_if_the_passage_has_links() {
      String testTitle1 = "Sky";
      String testContent1 = "In to the sky";
      String testTitle2 = "Moon";
      String testContent2 = "On to the moon";
      Link testLink1 = new Link(testTitle1,testContent1);
      Link testLink2 = new Link(testTitle2,testContent2);
      passage.addLink(testLink1);
      passage.addLink(testLink2);
      assertTrue(passage.hasLink());
    }
  }

  @Nested
  @DisplayName("Testing exception handling in the Passage class.")
  class ExceptionHandlingTests {

    @Test
    @DisplayName("Testing the exceptions thrown with an empty title.")
    void throws_IAE_with_an_empty_title_of_the_passage() {
      String emptyTestTitle = "";
      String testContent = "In the forest";
      assertThrows(IllegalArgumentException.class, () -> new Passage(emptyTestTitle, testContent));
    }

    @Test
    @DisplayName("Testing the exceptions thrown with an empty content.")
    void throws_IAE_with_an_empty_content_of_the_passage() {
      String testTitle = "Forest";
      String emptyTestContent = "";
      assertThrows(IllegalArgumentException.class, () -> new Passage(testTitle, emptyTestContent));
    }

    @Test
    @DisplayName("Testing adding an empty link to the passage.")
    void throws_IAE_with_adding_an_empty_link_to_the_passage() {
      Link emptyTestLink = null;
      assertThrows(IllegalArgumentException.class, () -> passage.addLink(emptyTestLink));
    }
  }
}

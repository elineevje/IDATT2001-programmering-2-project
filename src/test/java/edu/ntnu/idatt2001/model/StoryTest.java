package edu.ntnu.idatt2001.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Story class.
 */
class StoryTest {

    static Story testStory;
    static Passage testPassage1;
    static Passage testPassage2;
    static Link testLink1;
    static Link testLink2;

    @BeforeEach
    void setUp() {
        String title = "Story title";
        Passage passage = new Passage("Passage title", "Passage content");
        testStory = new Story(title, passage);

        String titlePassage1 = "Title passage 1/Reference link 1";
        String contentPassage1 = "Passage content 1";
        testPassage1 = new Passage(titlePassage1, contentPassage1);

        String titlePassage2 = "Passage title";
        String contentPassage2 = "Passage content 2";
        testPassage2 = new Passage(titlePassage2, contentPassage2);

        String textLink1 = "Link text";
        String referenceLink1 = "Title passage 1/Reference link 1";
        testLink1 = new Link(textLink1, referenceLink1);

        String textLink2 = "Link text";
        String referenceLink2 = "Link reference";
        testLink2 = new Link(textLink2, referenceLink2);
    }

    @Nested
    @DisplayName("Testing the methods of the Story class with valid arguments")
    class StoryMethodTests {

        @Test
        @DisplayName("Testing creating a game with valid arguments.")
        void creatingALinkWitValidArguments() {
            String title = "Scary trolls";
            Passage passage = new Passage("Mountain", "In the mountain");

            try {
                testStory = new Story(title, passage);
            } catch (Exception e) {
                fail("Story was not created.");
            }
        }
        @Test
        @DisplayName("Testing accessing the story's title.")
        void getTitleTest() {
            assertEquals("Story title", testStory.getTitle());
        }

        @Test
        @DisplayName("Testing accessing the story's opening passages title.")
        void getOpeningPassagesTitleTest() {
            assertEquals("Passage title", testStory.getOpeningPassage().getTitle());
        }

        @Test
        @DisplayName("Testing accessing the story's opening passages content.")
        void getOpeningPassagesContentTest() {
            assertEquals("Passage content", testStory.getOpeningPassage().getContent());
        }

        @Test
        @DisplayName("Testing accessing the story's passages.")
        void getPassagesTest() {
            assertTrue(testStory.getPassages().isEmpty());
        }

        @Test
        @DisplayName("Testing method for get passage as well as add passage, by the size of the list.")
        void getPassageAndAddPassageTest() {
            testStory.addPassage(testPassage1);
            testStory.addPassage(testPassage2);
            assertEquals(2, testStory.getPassages().size());
        }

        @Test
        @DisplayName("Testing method for add passage, by the index of the list.")
        void checkIfCorrectItemsAreAddedTest() {
            testStory.addPassage(testPassage1);
            Link link1 = new Link(testPassage1.getTitle(), testPassage1.getTitle());
            assertEquals(testPassage1, testStory.getPassage(link1));
        }

        @Test
        @DisplayName("Test of the add passage method. Adding items to the list, then using assert false to see " +
                "if it is empty.")
        void addPassageFalse() {
            testStory.addPassage(testPassage1);
            testStory.addPassage(testPassage2);
            assertFalse(testStory.getPassages().isEmpty());
        }

        @Test
        @DisplayName("Testing method for remove passage.")
        void removePassageTest() {
            testStory.addPassage(testPassage1);
            testStory.addPassage(testPassage2);
            Link link1 = new Link(testPassage1.getTitle(), testPassage1.getTitle());
            testStory.removePassage(link1);
            assertEquals(1, testStory.getPassages().size());
        }

        @Test
        @DisplayName("Testing method for remove passage, by passing in a link that should make the method not remove" +
                "the passage.")
        void removePassageInvalidPassageTest() {
            testPassage1.addLink(testLink1);
            testPassage1.addLink(testLink2);

            testStory.addPassage(testPassage1);
            testStory.removePassage(testLink1);

            assertEquals(2, testPassage1.getLinks().size());
        }

        @Test
        @DisplayName("Testing method for get broken links.")
        void getBrokenLinksTest() {
            testPassage1.addLink(testLink2);
            testStory.addPassage(testPassage1);

            assertEquals(1, testStory.getBrokenLinks().size());
        }
    }

    @Nested
    @DisplayName("Testing exception handling in the Story class.")
    class ExceptionHandlingTests {
        @Test
        @DisplayName("Testing the exception thrown for title in the constructor of the Story class.")
        void exceptionHandlingTitleTest() {
            String title = "";
            Passage passage = new Passage("Sky","Big");
            assertThrows(IllegalArgumentException.class, () -> new Story(title, passage));
        }

        @Test
        @DisplayName("Testing the exception thrown for opening passage in the constructor of the Story class.")
        void exceptionHandlingOpeningPassageTest() {
            String title = "Ground";
            assertThrows(IllegalArgumentException.class, () -> new Story(title, null));
        }
        @Test
        @DisplayName("Testing the exception thrown when inserting an invalid parameter in the get passage method.")
        void exceptionHandlingGetPassageTest() {
            assertThrows(IllegalArgumentException.class, () -> testStory.getPassage(null));
        }

        @Test
        @DisplayName("Testing the exception thrown when inserting an invalid parameter in the add passage method.")
        void exceptionHandlingAddPassageTest() {
            assertThrows(IllegalArgumentException.class, () -> testStory.addPassage(null));
        }

        @Test
        @DisplayName("Testing the exception thrown when inserting an invalid parameter in the remove passage method.")
        void exceptionHandlingRemovePassageTest() {
            assertThrows(IllegalArgumentException.class, () -> testStory.removePassage(null));
        }
    }
}


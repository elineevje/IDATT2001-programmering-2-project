package edu.ntnu.idatt2001.readersandwriters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Passage;
import edu.ntnu.idatt2001.model.Story;
import edu.ntnu.idatt2001.model.action.GoldAction;
import edu.ntnu.idatt2001.model.action.HealthAction;
import edu.ntnu.idatt2001.model.action.InventoryAction;
import edu.ntnu.idatt2001.model.action.ScoreAction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * StoryReaderTest class.
 */
class StoryReaderTest {

  static Story testStory;

  static String testPath;
  static StoryReader storyReader;
  static File testFile;

  @BeforeEach
  void setUp() {
    String testStoryTitle = "Haunted House";
    String testOpeningPassageTitle = "Beginnings";
    String testOpeningPassageContent = "You are in a dark room. There is a door to your left.";
    Passage testOpeningPassage = new Passage(testOpeningPassageTitle, testOpeningPassageContent);
    String testLinkText = "Try to open the door";
    String testLinkReference = "Another room";
    Link testLink = new Link(testLinkText, testLinkReference);
    testLink.addAction(new GoldAction(-1));
    testOpeningPassage.addLink(testLink);

    String testPassageTitle1 = "Another room";
    String testPassageContent1 = "The door opens to another room."
        + " You see a desk with a large, dusty book on it.";
    Passage testPassage1 = new Passage(testPassageTitle1, testPassageContent1);
    String testLinkText1 = "Read the book";
    String testLinkReference1 = "The book";
    Link testLink1 = new Link(testLinkText1, testLinkReference1);
    testLink1.addAction(new InventoryAction("Book"));
    testPassage1.addLink(testLink1);
    String testLinkText2 = "Leave the room";
    String testLinkReference2 = "Kitchen";
    Link testLink2 = new Link(testLinkText2, testLinkReference2);
    testLink2.addAction(new ScoreAction(2));
    testPassage1.addLink(testLink2);

    String testPassageTitle2 = "Kitchen";
    String testPassageContent2 = "You Lost 1 gold coin."
        + " You leave the room and go back to the beginning.";
    Passage testPassage2 = new Passage(testPassageTitle2, testPassageContent2);
    String testLinkText3 = "Go back to the beginning";
    String testLinkReference3 = "Bathroom";
    Link testLink3 = new Link(testLinkText3, testLinkReference3);
    testLink3.addAction(new HealthAction(21));
    testPassage2.addLink(testLink3);

    testStory = new Story(testStoryTitle, testOpeningPassage);
    testStory.addPassage(testPassage1);
    testStory.addPassage(testPassage2);

    storyReader = new StoryReader();
    testPath = "src/test/resources/edu.ntnu.idatt2001/ReadingAStory.paths";
    testFile = new File(testPath);
  }

  @Nested
  @DisplayName("Testing storyReader method with valid arguments.")
  class ValidArgumentsTests {


    @Test
    @DisplayName("Test that the StoryReader reads a story from a file.")
    void readStoryWithValidArgumentsFromFileTest() {
      try {
        String expected = testStory.toString();
        String actual = storyReader.readStory(testFile).toString();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Test that the storyReader reads a story without actions.")
    void readStoryWithoutActionsTest() {
      try {

        String testStoryTitle = "Test Story without actions";
        String testOpeningPassageTitle = "Opening passage";
        String testOpeningPassageContent = "This is the opening passage.";
        Passage testOpeningPassage =
            new Passage(testOpeningPassageTitle, testOpeningPassageContent);
        String testLinkText = "test link text";
        String testLinkReference = "test link reference";
        Link testLink = new Link(testLinkText, testLinkReference);
        testOpeningPassage.addLink(testLink);

        String testPassageTitle = "Test Passage";
        String testPassageContent = "This is a test passage.";
        Passage testPassage = new Passage(testPassageTitle, testPassageContent);

        String testLinkText1 = "test link text1";
        String testLinkReference1 = "test link reference1";
        Link testLink1 = new Link(testLinkText1, testLinkReference1);
        testPassage.addLink(testLink1);

        String testLinkText2 = "test link text2";
        String testLinkReference2 = "test link reference2";
        Link testLink2 = new Link(testLinkText2, testLinkReference2);
        testPassage.addLink(testLink2);

        Story testStoryWithoutActions = new Story(testStoryTitle, testOpeningPassage);
        testStoryWithoutActions.addPassage(testPassage);

        String testPath = "src/test/resources/edu.ntnu.idatt2001/StoryWithoutActions.paths";
        File testFile = new File(testPath);

        String expected = testStoryWithoutActions.toString();
        String actual = storyReader.readStory(testFile).toString();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing that the testFile has passages, 2 passages expected.")
    void readStoryTestFileForPassagesTest() {
      try {
        int expected = 2;
        Story testPassagesInStory = storyReader.readStory(testFile);
        int actual = testPassagesInStory.getPassages().size();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing accessing the "
        + "title of the story, expected 'Haunted House'.")
    void readStoryStoryTitleTest() {
      try {
        String expected = "Haunted House";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getTitle();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing accessing the "
        + "title of the opening passage, expected 'Beginnings'.")
    void readStoryOpeningPassageTitleTest() {
      try {
        String expected = "Beginnings";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getOpeningPassage().getTitle();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing accessing the content of the opening passage.")
    void readStoryOpeningPassageContentTest() {
      try {
        String expected = "You are in a dark room. There is a door to your left.";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getOpeningPassage().getContent();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing accessing the title of the first link in the opening passage.")
    void readStoryFirstTitleLinkOfOpeningPassageTest() {
      try {
        String expected = "Try to open the door";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getOpeningPassage().getLinks()
            .stream().toList().get(0).getText();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing accessing the reference of the first link in the opening passage.")
    void readStoryFirstLinkReferenceOfOpeningPassageTest() {
      try {
        String expected = "Another room";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getOpeningPassage().getLinks()
            .stream().toList().get(0).getReference();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing accessing the type of "
        + "the action of the first link in the opening passage.")
    void readStoryActionTypeFirstLinkOfOpeningPassageTest() {
      try {
        String expected = "GoldAction";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getOpeningPassage().
            getLinks().stream().toList().get(0).getActions()
            .stream().toList().get(0).getClass().getSimpleName();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }


    @Test
    @DisplayName("Testing accessing the value of "
        + "the action of the first link in the opening passage.")
    void readStoryActionValueFirstLinkOfOpeningPassageTest() {
      try {
        String expected = "-1";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getOpeningPassage()
            .getLinks().stream().toList().get(0).getActions()
            .stream().toList().get(0).getValue();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing that the first passage has two links.")
    void readStoryFirstPassagesForLinksTest() {
      try {
        int expected = 2;
        Story testPassagesInStory = storyReader.readStory(testFile);
        int actual = testPassagesInStory.getPassages()
            .stream().toList().get(1).getLinks().size();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing accessing the title of the second passage.")
    void readStoryTitleOfSecondPassageTest() {
      try {
        String expected = "Another room";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getPassages()
            .stream().toList().get(1).getTitle();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing accessing the content of the second passage.")
    void readStoryContentOfSecondPassageTest() {
      try {
        String expected = "The door opens to another room."
            + " You see a desk with a large, dusty book on it.";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getPassages()
            .stream().toList().get(1).getContent();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing accessing the value of "
        + "the action of the first link in the first passage.")
    void readStoryActionValueFirstLinkInFirstPassageTest() {
      try {
        String expected = "Book";
        Story testPassagesInStory = storyReader.readStory(testFile);
        String actual = testPassagesInStory.getPassages()
            .stream().toList().get(1).getLinks().stream().toList()
            .get(0).getActions().stream().toList().get(0).getValue();

        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Testing that readStory handles reading a file with Passages without links.")
    void readStoryReadingAFilePassagesWithoutLinksTest() {
      try {
        int expected = 0;
        String storyWithoutLinksFileName = "src/test/resources/edu.ntnu.idatt2001/StoryWithoutLinks.paths";
        File storyWithoutLinksFile = new File(storyWithoutLinksFileName);
        Story storyWithoutLinks = storyReader.readStory(storyWithoutLinksFile);
        int actual = storyWithoutLinks.getPassages().stream().toList().get(0).getLinks().size();
        assertEquals(expected, actual);
      } catch (FileNotFoundException e) {
        fail("File not found exception: " + e.getMessage());
      } catch (IOException e) {
        fail("IO exception: " + e.getMessage());
      } catch (IllegalArgumentException e) {
        fail("Illegal argument exception: " + e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName("Testing exception handling in the StoryReader class with invalid arguments")
  class ExceptionHandlingTests {

    @Test
    @DisplayName("Testing that the readStory method throws an IEA if the file is null.")
    void readStoryNullFileTest() {

      File nullFile = null;
      assertThrows(IllegalArgumentException.class, () -> storyReader.readStory(nullFile));
    }

    @Test
    @DisplayName("Testing that the readStory method "
        + "throws an IOException if the file is empty.")
    void readStoryEmptyFileTest() {

      String emptyFileName = "scr/test/resources/edu.ntnu.idatt2001/EmptyStory.paths";
      File emptyFile = new File(emptyFileName);
      assertThrows(IOException.class, () -> storyReader.readStory(emptyFile));
    }

    @Test
    @DisplayName("Testing that the readStory method "
        + "throws an IOException if the file does not end with .paths.")
    void readStoryWrondEndingFileTest() {

      String wrongEndingFileName = "scr/test/resources/edu.ntnu.idatt2001/ReadingAStory.txt";
      File wrongEndingFile = new File(wrongEndingFileName);
      assertThrows(IOException.class, () -> storyReader.readStory(wrongEndingFile));
    }

    @Test
    @DisplayName("Testing that the readStory method "
        + "throws an IOException if the file does not exist.")
    void readStoryNonExistentFileTest() {
      String nonExistentFileName = "scr/test/resources/edu.ntnu.idatt2001/NonExistingStory.paths";
      File nonExistentFile = new File(nonExistentFileName);
      assertThrows(IOException.class, () -> storyReader.readStory(nonExistentFile));
    }

    @Test
    @DisplayName("Testing that the readStory method throws an IOException"
        + " if the action is not formatted correctly, by missing the colon.")
    void readStoryActionMissingColonTest() {
      String missingColonFileName = "scr/test/resources/edu.ntnu.idatt2001/ReadingAStoryMissingColon.paths";
      File missingColonFile = new File(missingColonFileName);
      assertThrows(IOException.class, () -> storyReader.readStory(missingColonFile));
    }

    @Test
    @DisplayName("Testing that the readStory method"
        + " throws an IOException if the action is unsupported.")
    void readStoryUnsupportedActionTest() {
      String unsupportedActionFileName = "scr/test/resources/edu.ntnu.idatt2001/ReadingAStoryUnsupportedAction.paths";
      File unsupportedActionFile = new File(unsupportedActionFileName);
      assertThrows(IOException.class, () -> storyReader.readStory(unsupportedActionFile));
    }

    @Nested
    @DisplayName("Integration tests.")
    class IntegrationTests {

      @Test
      @DisplayName("Integration test of writeStory and readStory,"
          + " by comparing the toString of the story written to the story read.")
      void writeStoryAndReadStoryTest() {
        try {
          //Arrange - create a story, a file, and a story writer
          String testFileName = "src/test/resources/edu.ntnu.idatt2001/WriteAndReadStory.paths";
          File testFile = new File(testFileName);
          String testTitle = "testTitle";
          String testContent = "TestContent";
          Passage testPassage = new Passage(testTitle, testContent);
          String testStoryTitle = "WriteAndReadStory";
          Story testStory1 = new Story(testStoryTitle, testPassage);
          StoryWriter storyWriter = new StoryWriter();
          String expected = testStory1.toString();

          //Act - write the story to a file, then read the story from the file
          storyWriter.writeStory(testStory1, testFile);
          Story testStory2 = storyReader.readStory(testFile);
          String actual = testStory2.toString();

          //Assert - compare the toString of the two stories
          assertEquals(expected, actual);
          testFile.delete();
        } catch (FileNotFoundException e) {
          fail("File not found exception: " + e.getMessage());
        } catch (IOException e) {
          fail("IO exception: " + e.getMessage());
        } catch (IllegalArgumentException e) {
          fail("Illegal argument exception: " + e.getMessage());
        }
      }
    }
  }
}

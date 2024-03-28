package edu.ntnu.idatt2001.readersandwriters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Passage;
import edu.ntnu.idatt2001.model.Story;
import edu.ntnu.idatt2001.model.action.HealthAction;
import edu.ntnu.idatt2001.model.action.InventoryAction;
import edu.ntnu.idatt2001.model.action.ScoreAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Test class for the StoryWriter class.
 */
class StoryWriterTest {

  static StoryWriter storyWriter;
  static StoryReader storyReader;
  static Passage testPassage;
  static Passage testPassage2;
  static String testStoryTitle;
  static String testStoryTitle2;
  static Story testStory;
  static Story testStory2;
  static String testLinkText;
  static String testLinkReference;
  static Link testLink;
  static String testLinkText2;
  static String testLinkReference2;
  static Link testLink2;
  static String testLinkText3;
  static String testLinkReference3;
  static Link testLink3;
  static File testFile;


  @BeforeEach
  void setUp() {
    storyWriter = new StoryWriter();
    storyReader = new StoryReader();

    String testPassageTitle = "TestPassageTitle";
    String testPassageContent = "TestPassageContent";
    testPassage = new Passage(testPassageTitle, testPassageContent);

    String testPassageTitle2 = "TestLinkReference";
    String testPassageContent2 = "TestPassageContent2";
    testPassage2 = new Passage(testPassageTitle2, testPassageContent2);

    testStoryTitle = "TestStoryTitle";
    testStory = new Story(testStoryTitle, testPassage);

    testStoryTitle2 = "TestStoryTitle2";
    testStory2 = new Story(testStoryTitle2, testPassage2);

    testLinkText = "TestLinkText";
    testLinkReference = "TestLinkReference";
    testLink = new Link(testLinkText, testLinkReference);

    testLinkText2 = "TestLinkText2";
    testLinkReference2 = "TestLinkReference2";
    testLink2 = new Link(testLinkText2, testLinkReference2);

    testLinkText3 = "TestLinkText3";
    testLinkReference3 = "TestLinkReference3";
    testLink3 = new Link(testLinkText3, testLinkReference3);

    testFile = new File("src/test/resources/testStoryWriterFile.paths");
  }

  @Nested
  @DisplayName("Testing the methods of the StoryWriter class with valid arguments.")
  class ValidArgumentsTest {
    @Test
    @DisplayName("Testing the writeStory method by checking that the story is being correctly written.")
    void testWriteStory() throws IOException {
      File file = File.createTempFile("test", ".paths");

      testLink.addAction(new InventoryAction("Book"));
      testPassage.addLink(testLink);

      testLink2.addAction(new ScoreAction(2));
      testPassage2.addLink(testLink2);

      testLink3.addAction(new HealthAction(21));
      testPassage2.addLink(testLink3);

      testStory.addPassage(testPassage);
      testStory.addPassage(testPassage2);

      storyWriter.writeStory(testStory, file);

      String fileContents = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

      String expectedOutput = "TestStoryTitle\n" +
              "\n" +
              "::TestPassageTitle\n" +
              "TestPassageContent\n" +
              "[TestLinkText](TestLinkReference){Inventory:Book}\n" +
              "\n" +
              "::TestLinkReference\n" +
              "TestPassageContent2\n" +
              "[TestLinkText2](TestLinkReference2){Score:2}\n" +
              "[TestLinkText3](TestLinkReference3){Health:21}\n";
      assertEquals(expectedOutput, fileContents);
    }

    @Test
    @DisplayName("Testing the writeStory method when the link has no action, by checking that the story" +
            " is being correctly written.")
    void testWriteStoryWOAction() throws IOException {
      File file = File.createTempFile("test", ".paths");

      testPassage.addLink(testLink);

      testPassage2.addLink(testLink2);

      testPassage2.addLink(testLink3);

      testStory.addPassage(testPassage);
      testStory.addPassage(testPassage2);

      storyWriter.writeStory(testStory, file);

      String fileContents = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

      String expectedOutput = "TestStoryTitle\n" +
              "\n" +
              "::TestPassageTitle\n" +
              "TestPassageContent\n" +
              "[TestLinkText](TestLinkReference)\n" +
              "\n" +
              "::TestLinkReference\n" +
              "TestPassageContent2\n" +
              "[TestLinkText2](TestLinkReference2)\n" +
              "[TestLinkText3](TestLinkReference3)\n";
      assertEquals(expectedOutput, fileContents);
    }
  }

  @Nested
  @DisplayName("Testing the methods of the StoryWriter class with invalid arguments.")
  class ExceptionHandlingTests {
    @Test
    @DisplayName("Testing that the writeStory method throws an exception if the file is null.")
    void writeStoryFileIsNullTest() {
      IOException e = assertThrows(IOException.class, () -> storyWriter.writeStory(testStory, null));
      assertEquals("The file is empty.", e.getMessage());
    }

    @Test
    @DisplayName("Testing that the writeStory method throws an exception if the story is null.")
    void writeStoryStoryIsNull() {
      IOException e = assertThrows(IOException.class, () -> storyWriter.writeStory(null, testFile));
      assertEquals("The story is empty.", e.getMessage());
    }

    @Test
    @DisplayName("Testing that the writeStory method throws an exception if the file is not a .paths file.")
    void writeStoryFileHasWrongPathTest() {
      File unsupportedFile = new File("src/test/resources/testUnsupportedFile.txt");

      IOException e = assertThrows(IOException.class, () -> storyWriter.writeStory(testStory, unsupportedFile));
      assertEquals("Unsupported file format. Only .paths files are supported.", e.getMessage());
    }
  }
}


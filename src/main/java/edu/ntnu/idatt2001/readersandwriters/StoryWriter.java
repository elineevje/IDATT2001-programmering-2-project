package edu.ntnu.idatt2001.readersandwriters;

import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Passage;
import edu.ntnu.idatt2001.model.Story;
import edu.ntnu.idatt2001.model.action.Action;
import edu.ntnu.idatt2001.model.action.GoldAction;
import edu.ntnu.idatt2001.model.action.HealthAction;
import edu.ntnu.idatt2001.model.action.InventoryAction;
import edu.ntnu.idatt2001.model.action.ScoreAction;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Class for writing stories to a file.
 */
public class StoryWriter {
  private final String newLine = "\n";


  /**
   * Constructor for StoryWriter.
   */
  public StoryWriter() {
  }

  /**
   * Writes a story to a file.
   *
   * <p>Checks if the story is null, avoiding writing an empty story.
   *
   * <p>Writes the story file on the following format:
   * The name of the story, followed by a new blank line.
   * Then a double colon indicates a new passage. The first double colon is followed by
   * the title of the opening passage.
   * On the next line is the content of the opening passage.
   * Followed by the links of the opening passage, marked with brackets ([]),
   * the reference to the passage in parentheses (()) and then the action of
   * the link marked with curly brackets ({}). Each link and action is on a new line.
   * The remaining passages are written in the same way as the opening passage.
   * Just indicated by a new line and double colon in front of the title of the passage.
   *
   * @param story (String)
   * @param file (File)
   * @throws IOException "The file is empty."
   * @throws IOException "The story is empty."
   * @throws IOException "Unsupported file format. Only .paths files are supported."
   */
  public void writeStory(Story story, File file) throws IOException {
    if (file == null) {
      throw new IOException("The file is empty.");
    }
    if (story == null) {
      throw new IOException("The story is empty.");
    }
    if (!file.getName().endsWith(".paths")) {
      throw new IOException("Unsupported file format. Only .paths files are supported.");
    } else {
      file.createNewFile();
    }

    try {
      FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8, true);
      fileWriter.write(story.getTitle());
      fileWriter.write(newLine);

      String doubleColon = "::";
      fileWriter.write(newLine + doubleColon + story.getOpeningPassage().getTitle() + newLine);
      fileWriter.write(story.getOpeningPassage().getContent() + newLine);
      for (Link link : story.getOpeningPassage().getLinks()) {
        String string = "[" + link.getText() + "]"
            + "(" + link.getReference() + ")";
        writeAction(string, link, fileWriter);
      }

      for (Passage passage : story.getPassages()) {
        if (!passage.equals(story.getOpeningPassage())) {
          fileWriter.write(newLine + doubleColon + passage.getTitle() + newLine);
          fileWriter.write(passage.getContent() + newLine);
          for (Link link : passage.getLinks()) {
            String string = "[" + link.getText() + "]"
                    + "(" + link.getReference() + ")";
            writeAction(string, link, fileWriter);
          }
        }
      }
      fileWriter.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private void writeAction(String string, Link link, FileWriter fileWriter) throws IOException {
    if (link.getActions() == null) {
      fileWriter.write(string + newLine);
      return;
    }
    StringBuilder stringBuilder = new StringBuilder(string);
    List<Action> actions = link.getActions();
    for (Action action : actions) {
      if (action instanceof GoldAction) {
        stringBuilder.append("{Gold:").append(((GoldAction) action).getGold()).append("}");
      }
      if (action instanceof ScoreAction) {
        stringBuilder.append("{Score:").append(((ScoreAction) action).getScore()).append("}");
      }
      if (action instanceof InventoryAction) {
        stringBuilder.append("{Inventory:").append(((InventoryAction) action).getInventory())
            .append("}");
      }
      if (action instanceof HealthAction) {
        stringBuilder.append("{Health:").append(((HealthAction) action).getHealth())
            .append("}");
      }
    }
    fileWriter.write(stringBuilder + newLine);
  }
}

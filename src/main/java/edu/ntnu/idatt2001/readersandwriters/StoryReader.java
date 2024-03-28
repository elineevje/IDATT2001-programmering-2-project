package edu.ntnu.idatt2001.readersandwriters;

import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Passage;
import edu.ntnu.idatt2001.model.Story;
import edu.ntnu.idatt2001.model.action.Action;
import edu.ntnu.idatt2001.model.factory.ActionFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


/**
 * Class for reading stories from a file.
 */
public class StoryReader {
  private final List<Passage> passages;
  private final List<Link> links;

  /**
   * Constructor for StoryReader.
   */
  public StoryReader() {
    links = new ArrayList<>();
    passages = new ArrayList<>();
  }

  /**
   * Reads a story from a file.
   *
   * <p>Checks if the file name is null.
   * Checks if the file exists.
   * Checks if the file name ends with .paths
   * Checks if the file is empty,
   * by checking if the reader can not find anything in to read in the file.
   *
   * <p>Reads the story file on the following format:
   * The name of the story, followed by a new blank line.
   * Then a double colon indicates a new passage. The first double colon is followed by
   * the title of the opening passage.
   * On the next line is the content of the opening passage.
   * Followed by the links of the opening passage, marked with brackets ([]),
   * the reference to the passage in parentheses (()) and then the action of
   * the link marked with curling brackets ({}) Each link and action is on a new line.
   * The remaining passages are written in the same way as the opening passage.
   * Just indicated by a new line and double colon in front of the title of the passage.
   *
   * @param file file (File)
   * @return story (Story)
   * @throws IllegalArgumentException "File name can not be null."
   * @throws FileNotFoundException "File does not exist. Unable to read from file
   *                                that does not exist."
   * @throws IllegalArgumentException "Unsupported file format. Only .paths files are supported."
   * @throws IOException "The file is empty, therefore it is nothing to read from this file."
   * @throws IOException "Unable to read stories from file ."
   * @throws IllegalArgumentException "Invalid file format. Unable to read from ."
   */
  public Story readStory(File file) throws IOException, FileNotFoundException,
          IllegalArgumentException {
    if (file == null) {
      throw new IllegalArgumentException("File name can not be null.");
    }
    if (!file.exists()) {
      throw new FileNotFoundException("File does not exist. Unable to read from "
              + "file that does not exist.");
    }
    if (!file.getName().endsWith(".paths")) {
      throw new IllegalArgumentException("Unsupported file format. Only .paths files "
              + "are supported.");
    }

    Story story = null;

    try {
      Scanner storyFileReader = new Scanner(file, StandardCharsets.UTF_8);
      if (!storyFileReader.hasNextLine()) {
        throw new IOException("The file is empty, therefore it is nothing to read from this file.");
      }
      // Read the story title, if the file is not empty.
      final String storyTitle = storyFileReader.nextLine();
      storyFileReader.nextLine();

      // Read the opening passage,
      // with a double colon in front of the title and the content on the next line.
      String openingPassageTitle = storyFileReader.nextLine().substring(2);
      String openingPassageContent = storyFileReader.nextLine();

      // Read the links of the opening passage, if the next line starts with a bracket([).
      if (storyFileReader.hasNext()) {
        try {
          String line = storyFileReader.nextLine();
          while (!line.isEmpty()) {
            if (line.startsWith("[")) {
              String linkText = line.substring(1, line.indexOf("]"));
              String linkPassage = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
              String actionType = null;
              String actionValue = null;

              if (line.contains("{") && line.contains(":") && line.endsWith("}")) {
                actionType = line.substring(line.indexOf("{") + 1, line.indexOf(":"));
                actionValue = line.substring(line.indexOf(":") + 1, line.indexOf("}"));
              }

              Action action = null;
              if (actionType != null) {
                ActionFactory actionFactory = new ActionFactory();
                action = actionFactory.get(actionType, actionValue);
              }

              line = storyFileReader.nextLine();

              Link link = new Link(linkText, linkPassage);
              if (action != null) {
                link.addAction(action);
              }
              links.add(link);
            } else {
              break;
            }
          }
        } catch (Exception e) {
          throw new IOException("Unable to read stories from file " + file.getName() + ".");
        }

      }

      Passage openingPassage = new Passage(openingPassageTitle, openingPassageContent);
      links.forEach(openingPassage::addLink);
      story = new Story(storyTitle, openingPassage);

      while (storyFileReader.hasNext()) {
        String passageTitle = storyFileReader.nextLine().substring(2);
        String passageContent = storyFileReader.nextLine();
        Passage passage = new Passage(passageTitle, passageContent);
        if (storyFileReader.hasNext()) {
          String line = storyFileReader.nextLine();

          try {
            while (!line.isEmpty()) {
              if (line.startsWith("[")) {
                String linkText = line.substring(1, line.indexOf("]"));
                String linkPassage = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                String actionType = null;
                String actionValue = null;

                if (line.contains("{") && line.contains(":") && line.endsWith("}")) {
                  actionType = line.substring(line.indexOf("{") + 1, line.indexOf(":"));
                  actionValue = line.substring(line.indexOf(":") + 1, line.indexOf("}"));
                }

                Action action = null;
                if (actionType != null) {
                  ActionFactory actionFactory = new ActionFactory();
                  action = actionFactory.get(actionType, actionValue);
                }

                Link link = new Link(linkText, linkPassage);
                if (action != null) {
                  link.addAction(action);
                }
                passage.addLink(link);
                links.add(link);
              }
              if (storyFileReader.hasNextLine()) {
                line = storyFileReader.nextLine();
              } else {
                break;
              }
            }
          } catch (Exception e) {
            throw e;
          }
        }
        if (!passage.getTitle().equals(openingPassageTitle)) {
          passages.add(passage);
        }
      }
      storyFileReader.close();
    } catch (IOException e) {
      throw e;
    }
    assert story != null;
    passages.forEach(story::addPassage);
    return story;
  }
}

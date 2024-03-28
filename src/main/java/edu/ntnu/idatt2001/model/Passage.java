package edu.ntnu.idatt2001.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Passage class.
 * Represents a smaller part of the story. The links make it possible to move
 * from one passage to another. The list of links makes the story nonlinear,
 * by leading to different passages.
 *
 */
public class Passage {
  private final String title;
  private final String content;
  private final List<Link> links;

  /**
   * Constructor of the Passage class, which creates a new passage.
   * Checks for illegal arguments.
   *
   * @param title Identifying description of the passage (String)
   * @param content Text description or dialog in the passage (String)
   * @throws IllegalArgumentException "The title can not be empty."
   * @throws IllegalArgumentException "The content can not be empty."
   */
  public Passage(String title, String content) {
    if (title.isBlank()) {
      throw new IllegalArgumentException("The title can not be empty.");
    }
    if (content.isBlank()) {
      throw new IllegalArgumentException("The content can not be empty.");
    }
    this.title = title;
    this.content = content;
    this.links = new ArrayList<>();
  }

  /**
   * Accessing the title of the passage.
   *
   * @return title (String)
   */
  public String getTitle() {
    return title;
  }

  /**
   * Accessing the content of the passage.
   *
   * @return content (String)
   */
  public String getContent() {
    return content;
  }

  /**
   * Accessing the list of links in the passage.
   *
   * @return links (List<Link>)
   */
  public List<Link> getLinks() {
    return links;
  }

  /**
   * Checking if the passage adds a link.
   * Throwing an illegal argument if the link is empty.
   *
   * @param link (Link)
   * @return true or false (boolean)
   * @throws IllegalArgumentException "The link is empty,
   and can therefore not be added to the passage."
   */
  public boolean addLink(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("The link is empty,"
          + " and can therefore not be added to the passage.");
    } else {
      return this.links.add(link);
    }
  }

  /**
   * Checking if the passage has a link.
   * Returning true if the list of links is not empty, else false.
   *
   * @return true or false (boolean)
   */
  public boolean hasLink() {
    return !links.isEmpty();
  }

  /**
   * ToString for the passage, containing the related title, content and links.
   *
   * @return (String)
   */
  @Override
  public String toString() {
    return "Passage" + "\n"
       + "Title: " + title + "\n"
       + "Content " + content + "\n"
       + "Links " + links + "\n";
  }

  /**
   * Equals method for the Passage class,
   * checking if the title, content and links are equal.
   * Returning true if they are, else false.
   *
   * @param object (Object)
   * @return true or false (boolean)
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Passage passage)) {
      return false;
    }
    return title.equals(passage.title) && content.equals(passage.content) && links.equals(
        passage.links);
  }

  /**
   * Hashcode for the Passage class,
   * containing the title, content and links.
   *
   * @return (int)
   */
  @Override
  public int hashCode() {
    return Objects.hash(title, content, links);
  }
}

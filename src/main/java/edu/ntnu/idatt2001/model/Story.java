package edu.ntnu.idatt2001.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Story class. Represents the story, including title and the passages.
 */
public class Story {

  /**
   * Defining all the object variables for story.
   */
  private final String title;
  private final Map<Link, Passage> passages;

  private final Passage openingPassage;

  /**
   * Constructor for story.
   *
   * @param title          The title of the story. (String)
   * @param openingPassage The first passage in the story. (Passage)
   * @throws IllegalArgumentException "Title can not be empty."
   * @throws IllegalArgumentException "Opening passage can not be empty."
   */
  public Story(String title, Passage openingPassage) {
    if (title.isBlank()) {
      throw new IllegalArgumentException("Title can not be empty.");
    }
    if (openingPassage == null) {
      throw new IllegalArgumentException("Opening passage can not be empty.");
    }
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();
  }

  /**
   * Get method for title.
   *
   * @return Title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Get method for opening passage.
   *
   * @return Opening passage.
   */
  public Passage getOpeningPassage() {
    return openingPassage;
  }

  /**
   * Get method for passage.
   *
   * @param link Link.
   * @return Passage.
   */
  public Passage getPassage(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("The link is empty and can therefor not be added.");
    }
    return passages.get(link);
  }

  /**
   * Get method for passages.
   *
   * @return Values of passage.
   */
  public Collection<Passage> getPassages() {
    return passages.values();
  }


  /**
   * Method to add passage to map.
   *
   * @param passage Passage.
   */
  public void addPassage(Passage passage) {
    if (passage == null) {
      throw new IllegalArgumentException("The passage is empty, and can therefore not be added.");
    }
    Link link = new Link(passage.getTitle(), passage.getTitle());
    this.passages.put(link, passage);
  }

  /**
   * Method to remove passage from map.
   *
   * @param link Link that you want to remove.
   */
  public void removePassage(Link link) {
    ArrayList<Link> keySet = new ArrayList<>(passages.keySet());
    Passage passage = getPassage(link);

    if (keySet.stream().filter(key -> !key.getReference().equals(link.getReference()))
            .flatMap(key -> passages.get(key).getLinks().stream())
            .anyMatch(link1 -> link1.getReference().equals(passage.getTitle()))) {
      return;
    }
    passages.remove(new Link(link.getReference(), link.getReference()));
  }

  /**
   * Method to get all broken links.
   *
   * @return A list of broken links (a link that refers to a passage
   *      that does not exist in passages);
   */
  public List<Link> getBrokenLinks() {
    Stream<Link> allLinks = getPassages().stream().flatMap(p -> p.getLinks().stream());
    return allLinks.filter(l -> getPassages().stream()
            .noneMatch(p -> p.getTitle().equals(l.getReference()))).toList();
  }

  /**
   * ToString method for story.
   * Includes title, passages and opening passage.
   *
   * @return String representation of the story. (String)
   */
  @Override
  public String toString() {
    return "Story " + "\n"
        + "Title: " + title + "\n"
        + "Passages: " + passages + "\n"
        + "Opening passage: " + openingPassage + "\n";
  }
}


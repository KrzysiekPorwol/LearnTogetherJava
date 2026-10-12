package pd5.model;

import java.time.LocalDateTime;

public class ConsultantNote {

  private static int count = 1;

  private final int identifier;
  private String contents;
  private final LocalDateTime creationDate;
  private final String author;
  private boolean archived = false;

  public ConsultantNote(String contents, String author) {
    ContentValidator.requireNotBlank(contents);
    this.contents = contents;
    this.creationDate = LocalDateTime.now();
    this.identifier = count++;
    this.author = author;
  }

  public void changeContents(String contents) {
    if (archived) {
      System.out.println("Notatka jest zarchiwizowana, nie można już zmienić jej treści");
    } else {
      ContentValidator.requireNotBlank(contents);
      this.contents = contents;
      System.out.println("Zmieniono treść notatki");
    }
  }

  public void archive() {
    if (archived) {
      System.out.println("Notatka była już zarchiwizowana");
    } else {
      archived = true;
      System.out.println("Zarchiwizowano notatkę. Od teraz nie można jej edytować");
    }
  }

  @Override
  public String toString() {
    return "ConsultantNote{" +
          "identifier=" + identifier +
          ", contents='" + contents + '\'' +
          ", creationDate=" + creationDate +
          ", author='" + author + '\'' +
          ", archived=" + archived +
          '}';
  }
}

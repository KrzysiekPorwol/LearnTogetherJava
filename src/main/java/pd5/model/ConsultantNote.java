package pd5.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class ConsultantNote {

  private static int count = 1;

  private final int identifier;
  private String contents;
  private final LocalDateTime creationDate;
  private final String author;
  private boolean archived;

  public static ConsultantNote create(String contents, String author) {
    ContentValidator.requireNotBlank(contents);

    return new ConsultantNote(count++, contents, LocalDateTime.now(), author, false);
  }

  public void changeContents(String contents) {
    if (archived) {
      throw new IllegalStateException("Notatka jest już zarchiwizowana, nie można już jej zmienić treści");
    }
      ContentValidator.requireNotBlank(contents);
      this.contents = contents;
      System.out.println("Zmieniono treść notatki");
  }

  public void archive() {
    if (archived) {
      throw new IllegalStateException("Notatka była już zarchiwizowana");
    }
      archived = true;
      System.out.println("Zarchiwizowano notatkę. Od teraz nie można jej edytować");
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

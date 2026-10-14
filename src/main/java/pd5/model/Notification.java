package pd5.model;

import lombok.Getter;

import java.time.LocalDateTime;

public abstract class Notification implements Sendable {
  private static int count = 1;

  private final int identifier;
  @Getter
  private final String contents;
  private final LocalDateTime creationDate;
  @Getter
  private boolean sent = false;

  protected Notification(String contents) {
    ContentValidator.requireNotBlank(contents);
    this.contents = contents;
    this.creationDate = LocalDateTime.now();
    this.identifier = count++;
  }

  @Override
  public final void send() {
    if (sent) {
      System.out.println("Powiadomienie nr " + identifier + " już było wysłane!");
    } else if (validateSend()) {
      deliver();
      sent = true;
    }
  }

  protected abstract boolean validateSend();

  protected abstract void deliver();
}

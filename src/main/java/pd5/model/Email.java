package pd5.model;

public class Email extends Notification {

  private final String recipientAddress;
  private final String topic;

  public Email(String contents, String topic, String recipientAddress) {
    super(contents);
    this.topic = topic;
    this.recipientAddress = recipientAddress;
  }

  @Override
  protected boolean validateSend() {
    return true;
  }

  @Override
  protected void deliver() {
    System.out.println("Dostarczono e-mail o temacie: " + topic + "\nO treści: " + getContents() + "\nNa adres: " + recipientAddress);
  }
}

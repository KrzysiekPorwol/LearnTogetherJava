package pd5.model;

public class Sms extends Notification {

  private static final int MAX_LENGTH = 160;

  private final String telephoneNumber;

  public Sms(String contents, String telephoneNumber) {
    super(contents);
    this.telephoneNumber = telephoneNumber;
  }

  @Override
  protected boolean validateSend() {
    if (getContents().length() > MAX_LENGTH) {
      System.out.println("Nie wysłano SMS-a na numer " + telephoneNumber + ": treść ma więcej niż " + MAX_LENGTH + " znaków");
      return false;
    }
    return true;
  }

  @Override
  protected void deliver() {
    System.out.println("Dostarczono SMS o treści: " + getContents() + "\nNa numer: " + telephoneNumber);
  }
}

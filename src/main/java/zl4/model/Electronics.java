package zl4.model;

public class Electronics extends Product {

  private final int warrantInMonths;

  public Electronics(String name, String category, double price, int warrantInMonth) {
    super(name, category, price);
    this.warrantInMonths = warrantInMonth;
  }

  @Override
  public String getDescription() {
    return super.getDescription() + " | Gwarancja w miesiącach: " + warrantInMonths;
  }
}

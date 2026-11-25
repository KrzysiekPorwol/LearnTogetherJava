package pd12.model;

import java.util.List;

public record ProcessingReport(List<OrderResult> results, int soldQuantity, int remainingQuantity) {

  public ProcessingReport {
    results = List.copyOf(results);
  }

  public boolean isEmpty() {
    return results.isEmpty();
  }
}

package pd11.export;

import pd11.model.Report;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class CSVReportExporter {

  private static final String HEADER = "category,revenue,average_unit_price";
  private static final String SEPARATOR = ",";

  public void export(Report report, String filePath) throws IOException {
    Map<String, BigDecimal> revenueByCategory = report.revenueByCategory();
    Map<String, BigDecimal> averageUnitPriceByCategory = report.averageUnitPriceByCategory();

    try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath))) {
      writer.write(HEADER);
      writer.newLine();

      for (Map.Entry<String, BigDecimal> entry : revenueByCategory.entrySet()) {
        String category = entry.getKey();
        BigDecimal revenue = entry.getValue();
        BigDecimal averageUnitPrice = averageUnitPriceByCategory.get(category);

        writer.write(toCsvLine(category, revenue, averageUnitPrice));
        writer.newLine();
      }
    }
  }

  private String toCsvLine(String category, BigDecimal revenue, BigDecimal averageUnitPrice) {
    return String.join(SEPARATOR,
        category,
        revenue.toPlainString(),
        averageUnitPrice.toPlainString());
  }
}

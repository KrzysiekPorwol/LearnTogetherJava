package pd11.parser;

import pd11.model.Sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SaleParser {

  private static final int COLUMN_COUNT = 6;
  private static final int HEADER_LINE_COUNT = 1;
  private static final String COLUMN_SEPARATOR = ",";

  public List<Sale> parseSales(Stream<String> lines) {
    return lines
        .skip(HEADER_LINE_COUNT)
        .map(this::parseLine)
        .flatMap(Optional::stream)
        .toList();
  }

  private Optional<Sale> parseLine(String line) {
    String[] columns = line.split(COLUMN_SEPARATOR, -1);

    if (columns.length != COLUMN_COUNT) {
      System.err.println("Skipping line: " + line
          + " (expected " + COLUMN_COUNT + " columns, got " + columns.length + ")");
      return Optional.empty();
    }

    try {
      LocalDate date = LocalDate.parse(columns[0]);
      String productId = columns[1];
      String productName = columns[2];
      String category = columns[3];
      int quantity = Integer.parseInt(columns[4]);
      BigDecimal unitPrice = new BigDecimal(columns[5]);
      return Optional.of(new Sale(date, productId, productName, category, quantity, unitPrice));
    } catch (DateTimeParseException | IllegalArgumentException e) {
      System.err.println("Skipping line: " + line + " (invalid data: " + e + ")");
      return Optional.empty();
    }
  }
}

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


  public List<Sale> parseSales(Stream<String> lines) {
    return lines
        .skip(1)
        .map(this::parseLine)
        .flatMap(optionalSale -> optionalSale.stream())
        .toList();
  }

  private Optional<Sale> parseLine(String line) {
    String[] parts = line.split(",", -1);

    if (parts.length != COLUMN_COUNT) {
      System.err.println("Wiersz: " + line + " Oczekiwano " + COLUMN_COUNT + " kolumn, a jest " + parts.length );
      return Optional.empty();
    }

    try {
      LocalDate date = LocalDate.parse(parts[0]);
      String productId = parts[1];
      String productName = parts[2];
      String category = parts[3];
      int quantity = Integer.parseInt(parts[4]);
      BigDecimal unitPrice = new BigDecimal(parts[5]);
      return Optional.of(new Sale(date, productId, productName, category, quantity, unitPrice));
    } catch (DateTimeParseException | IllegalArgumentException e) {
      System.err.println("Wiersz: " + line + " ma błędne dane " + e);
      return Optional.empty();
    }
  }
}

//Wczytaj CSV przez Files.lines() + Stream (leniwe)
//Pomiń błędne wiersze z logowaniem na stderr (nie crash!)
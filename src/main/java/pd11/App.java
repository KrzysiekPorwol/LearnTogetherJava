package pd11;

import pd11.model.Sale;
import pd11.parser.SaleParser;
import pd11.reader.SalesFileReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class App {

  public static void main(String[] args) {

    SalesFileReader salesFileReader = new SalesFileReader();
    SaleParser saleParser = new SaleParser();

    List<Sale> sales;
    try (Stream<String> lines = salesFileReader.readFile()) {
      sales = saleParser.parseSales(lines);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    sales.forEach(System.out::println);

  }
}


//System wczytuje dane sprzedaży z CSV, przetwarza i eksportuje w dwóch formatach.
//
//WEJŚCIE (sales.csv, min. 30 wierszy, 5 kategorii):
//date,product_id,product_name,category,quantity,unit_price
//
//WYMAGANIA:
//Wczytaj CSV przez Files.lines() + Stream (leniwe)
//Pomiń błędne wiersze z logowaniem na stderr (nie crash!)
//Oblicz przez Stream API:
//a) Całkowity przychód (quantity * unit_price)
//b) Top 3 produkty według przychodu
//c) Przychód per kategoria (posortowany malejąco)
//d) Średnia cena per kategoria
//e) Dzień z najwyższą sprzedażą
//Wyeksportuj wyniki do:
//a) report.json — przez Jackson (wcięcia, daty ISO)
//b) summary.csv — tabela kategorii z przychodem i średnią ceną
//
//Klasa Report z polami odpowiadającymi wynikom (do serializacji Jackson).
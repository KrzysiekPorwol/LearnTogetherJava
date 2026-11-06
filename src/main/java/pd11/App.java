package pd11;

import pd11.export.CSVReportExporter;
import pd11.export.JsonReportExporter;
import pd11.model.Report;
import pd11.model.Sale;
import pd11.parser.SaleParser;
import pd11.reader.SalesFileReader;
import pd11.repository.SaleRepository;
import pd11.service.ReportService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class App {

  // Ścieżki plików wynikowych (względem katalogu, z którego uruchamiasz program)
  private static final String JSON_REPORT_PATH = "report.json";
  private static final String CSV_SUMMARY_PATH = "summary.csv";

  public static void main(String[] args) {

    // Tworzymy klocki do wczytania danych
    SalesFileReader salesFileReader = new SalesFileReader();
    SaleParser saleParser = new SaleParser();

    try {
      // Wczytujemy CSV i parsujemy linie na obiekty Sale.
      // Strumień linii musi zostać zużyty w środku try-with-resources,
      // bo po wyjściu z bloku plik jest zamykany.
      List<Sale> sales;
      try (Stream<String> lines = salesFileReader.readFile()) {
        sales = saleParser.parseSales(lines);
      }

      // Wkładamy sprzedaże do repozytorium (robi niemodyfikowalną kopię)
      SaleRepository saleRepository = new SaleRepository(sales);

      // Serwis liczy statystyki na danych z repozytorium
      ReportService reportService = new ReportService(saleRepository);

      // Liczymy raport RAZ i ten sam obiekt dajemy obu exporterom
      Report report = reportService.generateReport();

      // Zapis całego raportu do JSON (wcięcia, daty ISO)
      new JsonReportExporter().export(report, JSON_REPORT_PATH);

      // Zapis tabeli kategorii (przychód + średnia cena) do CSV
      new CSVReportExporter().export(report, CSV_SUMMARY_PATH);

      // Krótkie potwierdzenie dla użytkownika
      System.out.println("Wczytano poprawnych sprzedaży: " + sales.size());
      System.out.println("Zapisano: " + JSON_REPORT_PATH + ", " + CSV_SUMMARY_PATH);

    } catch (IOException e) {
      // Błąd odczytu lub zapisu pliku: komunikat na stderr zamiast stack trace'a
      System.err.println("Błąd operacji na pliku: " + e.getMessage());
    }
  }
}

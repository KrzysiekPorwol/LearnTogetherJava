package pd11;

import pd11.export.CsvReportExporter;
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

  private static final String JSON_REPORT_PATH = "report.json";
  private static final String CSV_SUMMARY_PATH = "summary.csv";

  public static void main(String[] args) {

    SalesFileReader salesFileReader = new SalesFileReader();
    SaleParser saleParser = new SaleParser();

    try {
      List<Sale> sales;
      try (Stream<String> lines = salesFileReader.readLines()) {
        sales = saleParser.parseSales(lines);
      }

      SaleRepository saleRepository = new SaleRepository(sales);
      ReportService reportService = new ReportService(saleRepository);

      Report report = reportService.generateReport();

      new JsonReportExporter().export(report, JSON_REPORT_PATH);

      new CsvReportExporter().export(report, CSV_SUMMARY_PATH);

      System.out.println("Valid sales loaded: " + sales.size());
      System.out.println("Saved: " + JSON_REPORT_PATH + ", " + CSV_SUMMARY_PATH);

    } catch (IOException e) {
      System.err.println("File operation failed: " + e.getMessage());
    }
  }
}

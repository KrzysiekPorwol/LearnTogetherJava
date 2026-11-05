package pd11.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class SalesFileReader {

  private static final Path SALES_FILE_PATH = Path.of("src/main/java/pd11/data/sales.csv");

  public Stream<String> readFile() throws IOException {
    return Files.lines(SALES_FILE_PATH);
  }
}
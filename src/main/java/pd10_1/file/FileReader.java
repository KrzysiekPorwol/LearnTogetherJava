package pd10_1.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileReader {

  public List<String> readFile() throws IOException {
    String filename = "/orders.csv";
    try (
        InputStream in = FileReader.class.getResourceAsStream(filename);
    ) {
      if (in == null) {
        throw new FileNotFoundException("File " + filename + " not found");
      }

      InputStreamReader charReader = new InputStreamReader(in, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(charReader);

      return reader.lines().toList();
    }
  }
}

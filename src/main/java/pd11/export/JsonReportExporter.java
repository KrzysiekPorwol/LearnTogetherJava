package pd11.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pd11.model.Report;
import pd11.model.ReportExporter;

import java.io.File;
import java.io.IOException;

public class JsonReportExporter implements ReportExporter {

  private final ObjectMapper mapper;

  public JsonReportExporter() {
    mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  @Override
  public void export(Report report, String filePath) throws IOException {
    mapper.writeValue(new File(filePath), report);
  }
}

package pd11.model;

import java.io.IOException;

public interface ReportExporter {

  public void exportToFile(Report report, String filePath) throws IOException;

}

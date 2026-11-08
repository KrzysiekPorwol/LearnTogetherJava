package pd11.model;

import java.io.IOException;

public interface ReportExporter {

  public void export(Report report, String filePath) throws IOException;

}

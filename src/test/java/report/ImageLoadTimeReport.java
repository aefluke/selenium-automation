package report;

import domain.LoadTimeStatus;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ImageLoadTimeReport {

    private static final String DELIMITER = ";";
    private static final String REPORT_FILE = "ImageLoadTime.csv";
    private static final String CUSTOM_REPORT_DIR = "/target/";

    public void write(List<LoadTimeStatus> loadTimeStatusList) throws IOException {
        try (PrintWriter pw = new PrintWriter(new File(getOutputPath() + REPORT_FILE))) {
            pw.println("LINK".concat(DELIMITER).concat("STATUS").concat(DELIMITER).concat("LOAD_TIME"));
            loadTimeStatusList.stream()
                    .map(loadTimeStatus -> loadTimeStatus.getLink()
                            .concat(DELIMITER)
                            .concat(String.valueOf(loadTimeStatus.getStatusCode()))
                            .concat(DELIMITER)
                            .concat(String.valueOf(loadTimeStatus.getLoadTimeAsMilis()))
                    ).forEach(pw::println);
        }
    }
    private String getOutputPath() {
        return new File(System.getProperty("user.dir")).getAbsolutePath() + CUSTOM_REPORT_DIR;
    }
}

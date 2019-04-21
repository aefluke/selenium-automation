package report;

import domain.ResponseStatus;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BoutiqueStatusReport {

    private static final String DELIMITER = ";";
    private static final String REPORT_FILE = "BoutiqueStatus.csv";
    private static final String CUSTOM_REPORT_DIR = "/target/";

    public void write(List<ResponseStatus> responseStatusList) throws IOException {
        try (PrintWriter pw = new PrintWriter(new File(getOutputPath() + REPORT_FILE))) {
            pw.println("LINK".concat(DELIMITER).concat("STATUS"));
            responseStatusList.stream()
                    .map(responseStatus -> responseStatus.getLink()
                            .concat(DELIMITER)
                            .concat(String.valueOf(responseStatus.getStatusCode())))
                    .forEach(pw::println);
        }
    }

    private String getOutputPath() {
        return new File(System.getProperty("user.dir")).getAbsolutePath() + CUSTOM_REPORT_DIR;
    }


}

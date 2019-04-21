package domain;

import lombok.Getter;

@Getter
public class LoadTimeStatus extends ResponseStatus {

    private final Long loadTimeAsMilis;

    public LoadTimeStatus(Integer statusCode, String link, Long loadTimeAsMilis) {
        super(statusCode, link);
        this.loadTimeAsMilis = loadTimeAsMilis;
    }
}

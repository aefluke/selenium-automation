package domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResponseStatus {
    private final Integer statusCode;
    private final String link;
}

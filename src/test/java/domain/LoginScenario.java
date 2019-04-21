package domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginScenario {
    private final String email;
    private final String password;
    private final String fieldValidationErrorMessage;
    private final String scenarioName;
}

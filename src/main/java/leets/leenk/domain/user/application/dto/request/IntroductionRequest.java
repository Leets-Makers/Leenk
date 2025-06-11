package leets.leenk.domain.user.application.dto.request;

import jakarta.validation.constraints.Size;

public record IntroductionRequest(
        @Size(max = 200)
        String introduction
) {
}

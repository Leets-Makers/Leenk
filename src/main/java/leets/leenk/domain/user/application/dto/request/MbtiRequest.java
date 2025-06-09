package leets.leenk.domain.user.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MbtiRequest(
        @Size(max = 4) @NotBlank
        String mbti
) {
}

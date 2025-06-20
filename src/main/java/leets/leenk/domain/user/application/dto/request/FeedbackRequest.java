package leets.leenk.domain.user.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FeedbackRequest(
        @NotBlank
        @Size(max = 200)
        @Schema(description = "피드백", example = "~~ 기능 추가해주세요!")
        String feedback
) {
}

package leets.leenk.domain.user.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record FeedbackRequest(
        @Schema(description = "피드백", example = "~~ 기능 추가해주세요!")
        @NotBlank
        String feedback
) {
}

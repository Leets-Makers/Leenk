package leets.leenk.domain.feed.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LinkedUserRequest(
        @NotNull @Positive
        @Schema(description = "태그할 사용자 ID")
        Long userId
) {
}

package leets.leenk.domain.feed.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ReactionRequest(
        @NotNull
        @Schema(description = "리액션 횟수")
        long reactionCount
) {
}

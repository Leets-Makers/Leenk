package leets.leenk.domain.feed.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ReactionRequest(
        @NotBlank
        long reactionCount
) {
}

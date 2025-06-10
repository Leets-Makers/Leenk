package leets.leenk.domain.feed.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record ReactionRequest(
        @NotNull
        long reactionCount
) {
}

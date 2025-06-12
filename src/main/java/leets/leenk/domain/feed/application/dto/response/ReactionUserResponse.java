package leets.leenk.domain.feed.application.dto.response;

public record ReactionUserResponse(
        long userId,
        String profileImage,
        String name,
        long reactionCount
) {
}

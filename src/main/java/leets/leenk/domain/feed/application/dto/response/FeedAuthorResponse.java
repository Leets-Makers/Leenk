package leets.leenk.domain.feed.application.dto.response;

public record FeedAuthorResponse(
        long userId,
        String profileImage,
        String name
) {
}

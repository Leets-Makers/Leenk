package leets.leenk.domain.feed.application.dto.response;

public record FeedResponse(
        long feedId,
        FeedAuthorResponse author,
        String thumbNeil,
        long totalReactionCount
) {
}

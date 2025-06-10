package leets.leenk.domain.feed.application.dto.response;

public record FeedResponse(
        long feedId,
        String profileImage,
        String thumbNeil,
        long totalReactionCount
) {
}

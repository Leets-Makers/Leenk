package leets.leenk.domain.feed.application.dto.response;

import leets.leenk.domain.media.domain.application.dto.response.FeedMediaResponse;

import java.time.LocalDateTime;
import java.util.List;

public record FeedDetailResponse(
        long feedId,
        FeedAuthorResponse author,
        String description,
        LocalDateTime createdAt,
        List<FeedMediaResponse> media,
        List<LinkedUserResponse> linkedUser
) {
}

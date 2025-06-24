package leets.leenk.domain.notification.application.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.content.FeedReactionCount;

@Component
public class FeedReactionCountMapper {
    public FeedReactionCount toFeedReactionCount(Long reactionCount) {
        return FeedReactionCount.builder()
                .reactionCount(reactionCount)
                .title(NotificationType.FEED_REACTION_COUNT.getTitle())
                .body(NotificationType.FEED_REACTION_COUNT.getFormattedContent(reactionCount))
                .build();
    }
}

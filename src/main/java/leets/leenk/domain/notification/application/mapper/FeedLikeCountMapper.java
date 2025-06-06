package leets.leenk.domain.notification.application.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.details.FeedLikeCount;

@Component
public class FeedLikeCountMapper {
	public FeedLikeCount toFeedLikeCount(Long reactionCount) {
		return FeedLikeCount.builder()
			.likeCount(reactionCount)
			.title(NotificationType.LIKE_COUNT.getTitle())
			.body(NotificationType.LIKE_COUNT.getFormattedContent(reactionCount))
			.isRead(Boolean.FALSE)
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.build();
	}
}

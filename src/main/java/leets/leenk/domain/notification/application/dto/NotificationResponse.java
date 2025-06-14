package leets.leenk.domain.notification.application.dto;

import java.time.LocalDateTime;

import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstReactionDetail;
import leets.leenk.domain.notification.domain.entity.details.FeedReactionCountDetail;
import leets.leenk.domain.notification.domain.entity.details.FeedTagDetail;
import leets.leenk.domain.notification.domain.entity.details.NewFeedDetail;
import lombok.Builder;

@Builder
public record NotificationResponse(
	String id,
	UserInfo userInfo,
	NotificationType notificationType,
	FeedTagDetail feedTagDetail,
	FeedReactionCountDetail feedReactionCountDetail,
	FeedFirstReactionDetail feedFirstReactionDetail,
	NewFeedDetail newFeedDetail,
	LocalDateTime createDate,
	LocalDateTime updateDate

) {
	@Builder
	public record UserInfo(
		Long userId
	) {}
}

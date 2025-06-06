package leets.leenk.domain.notification.application.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.domain.entity.LinkedUser;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.details.FeedTagDetail;

@Component
public class NotificationMapper {

	// Todo : Feed id와 Feed 글쓴이 이름 파라미터로 가져오기
	public List<Notification> toFeedTagNotification(List<LinkedUser> linkedUsers) {
		return linkedUsers.stream()
			.map(linkedUser -> Notification.builder()
				.userId(linkedUser.getUser().getId())
				.deviceToken(linkedUser.getUser().getFcmToken())
				.notificationType(NotificationType.FEED_TAG)
				.feedTagDetail(FeedTagDetail.builder()
					.feedId(1L)
					.authorName("글쓴이")
					.title(NotificationType.FEED_TAG.getTitle())
					.body(NotificationType.FEED_TAG.getFormattedContent("글쓴이"))
					.isRead(Boolean.FALSE)
					.build())
				.build()
			)
			.collect(Collectors.toList());
	}
}

package leets.leenk.domain.notification.application.mapper;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.application.dto.SqsMessageEvent;
import leets.leenk.domain.notification.application.exception.InvalidNotificationTypeException;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLike;
import leets.leenk.domain.notification.domain.entity.event.FeedLikeCountEvent;

@Component
public class SqsMessageEventMapper {

	public SqsMessageEvent toSqsMessageEvent(Notification notification) {

		return switch (notification.getNotificationType()) {
			case FEED_TAG -> SqsMessageEvent.builder()
				.title(notification.getFeedTagDetail().getTitle())
				.content(notification.getNotificationType()
					.getFormattedContent(notification.getFeedTagDetail().getAuthorName()))
				.deviceToken(notification.getDeviceToken())
				.build();
			case NEW_FEED -> SqsMessageEvent.builder()
				.title(notification.getNewFeedDetail().getTitle())
				.content(notification.getNotificationType()
					.getFormattedContent(notification.getNewFeedDetail().getAuthorName()))
				.deviceToken(notification.getDeviceToken())
				.build();
			default -> throw new InvalidNotificationTypeException();
		};
	}

	public SqsMessageEvent fromFeedFirstLike(FeedFirstLike feedFirstLike, String deviceToken) {
		return SqsMessageEvent.builder()
			.title(feedFirstLike.getTitle())
			.content(feedFirstLike.getBody())
			.deviceToken(deviceToken)
			.build();
	}

	public SqsMessageEvent fromFeedLikeCountEvent(FeedLikeCountEvent feedLikeCountEvent) {
		return SqsMessageEvent.builder()
			.title(feedLikeCountEvent.getFeedLikeCount().getTitle())
			.content(feedLikeCountEvent.getFeedLikeCount().getBody())
			.deviceToken(feedLikeCountEvent.getDeviceToken())
			.build();
	}
}

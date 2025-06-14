package leets.leenk.global.sqs.application.mapper;

import org.springframework.stereotype.Component;

import leets.leenk.global.sqs.application.dto.SqsMessageEvent;
import leets.leenk.domain.notification.application.exception.InvalidNotificationTypeException;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.event.FeedFirstReactionEvent;
import leets.leenk.domain.notification.domain.entity.event.FeedReactionCountEvent;

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

	public SqsMessageEvent fromFeedFirstReaction(FeedFirstReactionEvent feedFirstReactionEvent) {
		return SqsMessageEvent.builder()
			.title(feedFirstReactionEvent.getFeedFirstReaction().getTitle())
			.content(feedFirstReactionEvent.getFeedFirstReaction().getBody())
			.deviceToken(feedFirstReactionEvent.getDeviceToken())
			.build();
	}

	public SqsMessageEvent fromFeedReactionCountEvent(FeedReactionCountEvent feedReactionCountEvent) {
		return SqsMessageEvent.builder()
			.title(feedReactionCountEvent.getFeedReactionCount().getTitle())
			.content(feedReactionCountEvent.getFeedReactionCount().getBody())
			.deviceToken(feedReactionCountEvent.getDeviceToken())
			.build();
	}
}

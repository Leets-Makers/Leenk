package leets.leenk.global.sqs.application.mapper;

import org.springframework.stereotype.Component;

import leets.leenk.global.sqs.application.dto.SqsMessageEvent;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.event.FeedFirstReactionEvent;
import leets.leenk.domain.notification.domain.entity.event.FeedReactionCountEvent;

@Component
public class SqsMessageEventMapper {

	public SqsMessageEvent toSqsMessageEvent(Notification notification) {

		return SqsMessageEvent.builder()
			.title(notification.getContent().getTitle())
			.content(notification.getContent().getBody())
			.deviceToken(notification.getDeviceToken())
			.build();
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

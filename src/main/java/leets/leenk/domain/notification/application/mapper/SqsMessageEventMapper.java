package leets.leenk.domain.notification.application.mapper;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.application.dto.SqsMessageEvent;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLike;

@Component
public class SqsMessageEventMapper {

	public SqsMessageEvent toSqsMessageEvent(Notification notification){

		return SqsMessageEvent.builder()
			.title(notification.getFeedTagDetail().getTitle())
			.content(notification.getNotificationType().getFormattedContent(notification.getFeedTagDetail().getAuthorName()))
			.deviceToken(notification.getDeviceToken())
			.build();
	};

	public SqsMessageEvent fromFeedFirstLike(FeedFirstLike feedFirstLike, String deviceToken) {
		return SqsMessageEvent.builder()
			.title(feedFirstLike.getTitle())
			.content(feedFirstLike.getBody())
			.deviceToken(deviceToken)
			.build();
	}
}

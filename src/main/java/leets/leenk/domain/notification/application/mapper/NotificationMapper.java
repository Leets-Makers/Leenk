package leets.leenk.domain.notification.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.content.FeedFirstReactionNotificationContent;
import leets.leenk.domain.notification.domain.entity.content.FeedReactionCountNotificationContent;
import leets.leenk.domain.notification.domain.entity.content.FeedTagNotificationContent;
import leets.leenk.domain.notification.domain.entity.content.NewFeedNotificationContent;
import leets.leenk.domain.user.domain.entity.User;

@Component
public class NotificationMapper {

	public List<Notification> toFeedTagNotification(Feed feed, List<LinkedUser> linkedUsers) {
		return linkedUsers.stream()
			.map(linkedUser -> Notification.builder()
				.userId(linkedUser.getUser().getId())
				.deviceToken(linkedUser.getUser().getFcmToken())
				.notificationType(NotificationType.FEED_TAG)
				.isRead(Boolean.FALSE)
				.content(toFeedTagNotificationContent(feed))
				.build()
			)
			.collect(Collectors.toList());
	}

	public Notification toFirstReactionNotification(Feed feed) {
		return Notification.builder()
			.userId(feed.getUser().getId())
			.deviceToken(feed.getUser().getFcmToken())
			.notificationType(NotificationType.FEED_FIRST_REACTION)
			.isRead(Boolean.FALSE)
			.content(toFeedFirstReactionNotificationContent(feed))
			.build();
	}

	public Notification toNewFeedNotification(Feed feed, User user){
		return Notification.builder()
			.userId(user.getId())
			.deviceToken(user.getFcmToken())
			.notificationType(NotificationType.NEW_FEED)
			.isRead(Boolean.FALSE)
			.content(toNewFeedNotificationContent(feed))
			.build();
	}

	public Notification toReactionCountNotification(Feed feed) {
		return Notification.builder()
			.userId(feed.getUser().getId())
			.deviceToken(feed.getUser().getFcmToken())
			.notificationType(NotificationType.FEED_REACTION_COUNT)
			.content(toFeedReactionCountNotificationContent(feed))
			.build();
	}

	private FeedTagNotificationContent toFeedTagNotificationContent(Feed feed){
		return FeedTagNotificationContent.builder()
			.feedId(feed.getId())
			.authorName(feed.getUser().getName())
			.title(NotificationType.FEED_TAG.getTitle())
			.body(NotificationType.FEED_TAG.getFormattedContent(feed.getUser().getName()))
			.build();
	}

	private FeedFirstReactionNotificationContent toFeedFirstReactionNotificationContent(Feed feed){
		return FeedFirstReactionNotificationContent.builder()
			.feedId(feed.getId())
			.build();
	}

	private NewFeedNotificationContent toNewFeedNotificationContent(Feed feed){
		return NewFeedNotificationContent.builder()
			.feedId(feed.getId())
			.authorUserId(feed.getUser().getId())
			.authorName(feed.getUser().getName())
			.title(NotificationType.NEW_FEED.getTitle())
			.body(NotificationType.NEW_FEED.getContent())
			.build();
	}

	private FeedReactionCountNotificationContent toFeedReactionCountNotificationContent(Feed feed){
		return FeedReactionCountNotificationContent.builder()
			.feedId(feed.getId())
			.build();
	}
}

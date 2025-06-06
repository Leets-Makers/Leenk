package leets.leenk.domain.notification.application.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.domain.entity.LinkedUser;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLike;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLikeDetail;
import leets.leenk.domain.notification.domain.entity.details.FeedLikeCountDetail;
import leets.leenk.domain.notification.domain.entity.details.FeedTagDetail;
import leets.leenk.domain.notification.domain.entity.details.NewFeedDetail;
import leets.leenk.domain.user.domain.entity.User;

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

	public Notification toFirstReactionNotification(User author, Long feedId, FeedFirstLike feedFirstLike) {
		return Notification.builder()
			.userId(author.getId())
			.deviceToken(author.getFcmToken())
			.notificationType(NotificationType.FIRST_FEED_LIKE)
			.feedFirstLikeDetail(
				FeedFirstLikeDetail.builder()
					.feedId(feedId)
					.feedFirstLikes(
						List.of(
							feedFirstLike
						)
					)
					.build()
			)
			.build();
	}

	public Notification toNewFeedNotification(Long feedId, User user){	// Todo : Feed 객체 받아오도록 수정
		return Notification.builder()
			.userId(user.getId())
			.deviceToken(user.getFcmToken())
			.notificationType(NotificationType.NEW_FEED)
			.newFeedDetail(
				NewFeedDetail.builder()
					.feedId(feedId)
					.authorUserId(user.getId())
					.authorName(user.getName())
					.title(NotificationType.FEED_TAG.getTitle())
					.body(NotificationType.NEW_FEED.getFormattedContent("글쓴이1"))
					.isRead(Boolean.FALSE)
					.build()
			)
			.build();
	}

	public Notification toReactionCountNotification(Long feedId, User user) {
		return Notification.builder()
			.userId(user.getId())	// Todo : feed.getId()로 수정
			.deviceToken(user.getFcmToken())
			.notificationType(NotificationType.LIKE_COUNT)
			.feedLikeCountDetail(
				FeedLikeCountDetail.builder()
					.feedId(feedId)
					.feedLikeCounts(new ArrayList<>())
					.build()
			)
			.build();
	}
}

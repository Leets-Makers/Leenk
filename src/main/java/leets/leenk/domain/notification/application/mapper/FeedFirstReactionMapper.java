package leets.leenk.domain.notification.application.mapper;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLike;
import leets.leenk.domain.user.domain.entity.User;

@Component
public class FeedFirstReactionMapper {

	public FeedFirstLike toFeedFirstReaction(User user){
		return FeedFirstLike.builder()
			.userId(user.getId())
			.name(user.getName())
			.title(NotificationType.FIRST_FEED_LIKE.getTitle())
			.body(NotificationType.FIRST_FEED_LIKE.getFormattedContent(user.getName()))
			.isRead(Boolean.FALSE)
			.build();
	}
}

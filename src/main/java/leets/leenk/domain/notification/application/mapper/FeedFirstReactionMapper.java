package leets.leenk.domain.notification.application.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.content.FeedFirstReaction;
import leets.leenk.domain.user.domain.entity.User;

@Component
public class FeedFirstReactionMapper {

	public FeedFirstReaction toFeedFirstReaction(User user){
		return FeedFirstReaction.builder()
			.userId(user.getId())
			.name(user.getName())
			.title(NotificationType.FEED_FIRST_REACTION.getTitle())
			.body(NotificationType.FEED_FIRST_REACTION.getContent())
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.build();
	}
}

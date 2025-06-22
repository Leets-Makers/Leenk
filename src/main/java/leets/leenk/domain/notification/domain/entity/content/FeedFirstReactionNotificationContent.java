package leets.leenk.domain.notification.domain.entity.content;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import leets.leenk.domain.notification.domain.entity.NotificationContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
public class FeedFirstReactionNotificationContent extends NotificationContent {

	@Field("feedId")
	private Long feedId;

	private List<FeedFirstReaction> feedFirstReactions = new ArrayList<>();

}

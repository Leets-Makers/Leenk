package leets.leenk.domain.notification.domain.entity.event;

import leets.leenk.domain.notification.domain.entity.details.FeedFirstReaction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FeedFirstReactionEvent {
	private final FeedFirstReaction feedFirstReaction;
	private final String deviceToken;
}

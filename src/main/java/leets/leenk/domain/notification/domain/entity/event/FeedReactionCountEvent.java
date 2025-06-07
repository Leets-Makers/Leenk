package leets.leenk.domain.notification.domain.entity.event;

import leets.leenk.domain.notification.domain.entity.details.FeedReactionCount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FeedReactionCountEvent {
	private final FeedReactionCount feedReactionCount;
	private final String deviceToken;
}

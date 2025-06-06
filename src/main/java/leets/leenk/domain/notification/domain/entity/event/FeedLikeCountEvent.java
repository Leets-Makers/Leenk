package leets.leenk.domain.notification.domain.entity.event;

import leets.leenk.domain.notification.domain.entity.details.FeedLikeCount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FeedLikeCountEvent {
	private final FeedLikeCount feedLikeCount;
	private final String deviceToken;
}

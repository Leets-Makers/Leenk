package leets.leenk.domain.notification.domain.entity.event;

import leets.leenk.domain.notification.domain.entity.details.FeedFirstLike;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FeedFirstLikeEvent {
	private final FeedFirstLike feedFirstLike;
	private final String deviceToken;
}

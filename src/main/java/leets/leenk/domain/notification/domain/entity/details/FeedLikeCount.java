package leets.leenk.domain.notification.domain.entity.details;

import leets.leenk.domain.notification.domain.entity.NotificationContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder
public class FeedLikeCount extends NotificationContent {

	private Long likeCount;

}

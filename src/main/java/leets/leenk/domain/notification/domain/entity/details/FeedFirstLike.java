package leets.leenk.domain.notification.domain.entity.details;

import leets.leenk.domain.notification.domain.entity.NotificationContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class FeedFirstLike extends NotificationContent {

	private Long userId;

	private String name;

}

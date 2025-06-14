package leets.leenk.domain.notification.domain.entity.details;

import leets.leenk.domain.notification.domain.entity.NotificationContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder
public class NewFeedDetail extends NotificationContent {

	private Long feedId;
	private Long authorUserId;
	private String authorName;

}

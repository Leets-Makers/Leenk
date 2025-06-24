package leets.leenk.domain.notification.domain.entity.content;

import leets.leenk.domain.notification.domain.entity.NotificationContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder
public class FeedReactionCount extends NotificationContent {

    private Long reactionCount;

}

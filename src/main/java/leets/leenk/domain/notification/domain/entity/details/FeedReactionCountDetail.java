package leets.leenk.domain.notification.domain.entity.details;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
public class FeedReactionCountDetail {

	private Long feedId;

	List<FeedReactionCount> feedReactionCounts = new ArrayList<>();

}

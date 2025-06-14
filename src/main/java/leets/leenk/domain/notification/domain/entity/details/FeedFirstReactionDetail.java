package leets.leenk.domain.notification.domain.entity.details;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
public class FeedFirstReactionDetail {

	@Field("feedId")
	private Long feedId;

	private List<FeedFirstReaction> feedFirstReactions = new ArrayList<>();

}

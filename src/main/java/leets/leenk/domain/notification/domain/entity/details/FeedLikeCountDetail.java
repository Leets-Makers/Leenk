package leets.leenk.domain.notification.domain.entity.details;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
public class FeedLikeCountDetail {

	private Long feedId;

	List<FeedLikeCount> feedLikeCounts = new ArrayList<>();

}

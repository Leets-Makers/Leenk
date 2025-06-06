package leets.leenk.domain.notification.domain.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLikeDetail;
import leets.leenk.domain.notification.domain.entity.details.FeedLikeCountDetail;
import leets.leenk.domain.notification.domain.entity.details.FeedTagDetail;
import leets.leenk.domain.notification.domain.entity.details.NewFeedDetail;
import leets.leenk.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "notifications")
public class Notification extends BaseEntity {

	@Id
	private String id;

	private Long userId;

	private String deviceToken;

	@Enumerated(EnumType.STRING)
	private NotificationType notificationType;

	private FeedTagDetail feedTagDetail;

	private FeedLikeCountDetail feedLikeCountDetail;

	private FeedFirstLikeDetail feedFirstLikeDetail;

	private NewFeedDetail newFeedDetail;

}

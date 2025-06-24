package leets.leenk.domain.notification.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.NotificationType;

public interface NotificationRepository extends MongoRepository<Notification, String> {

	@Query("{ 'notificationType' : ?0, 'content.feedId': ?1 }")
	Optional<Notification> findFeedFirstReactionByFeedId(NotificationType type, Long feedId);

	@Query("{ 'notificationType' : ?0, 'content.feedId': ?1, 'content.feedFirstReactions.userId': ?2 }")
	Optional<Notification> findByFeedIdAndUserIdInFirstReactions(NotificationType type, Long feedId, Long userId);

	@Query("{ 'notificationType' : ?0, 'content.feedId': ?1}")
	Optional<Notification> findByFeedId(NotificationType type, Long feedId);

	@Query("{ 'notificationType' : ?0, 'content.feedId': ?1, 'content.feedReactionCounts.reactionCount': ?2}")
	Optional<Notification> findByFeedIdAndReactionCount(NotificationType type, Long feedId, Long reactionCount);

	Slice<Notification> findPageByUserId(Pageable pageable, Long userId);

	long countByUserIdAndIsReadFalse(Long id);
}

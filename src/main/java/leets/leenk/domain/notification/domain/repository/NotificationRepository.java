package leets.leenk.domain.notification.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import leets.leenk.domain.notification.domain.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

	Optional<Notification> findByFeedFirstReactionDetailFeedId(Long feedId);

	@Query("{ 'feedFirstReactionDetail.feedId': ?0, 'feedFirstReactionDetail.feedFirstReactions.userId': ?1 }")
	Optional<Notification> findByFeedIdAndUserIdInFirstReactions(Long feedId, Long id);

	@Query("{ 'feedReactionCountDetail.feedId': ?0, 'feedReactionCountDetail.feedReactionCounts.reactionCount': ?1}")
	Optional<Notification> findByFeedIdAndReactionCount(Long feedId, Long reactionCount);
}

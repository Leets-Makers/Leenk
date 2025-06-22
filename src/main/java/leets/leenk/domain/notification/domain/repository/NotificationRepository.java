package leets.leenk.domain.notification.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import leets.leenk.domain.notification.domain.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

	@Query("{ 'content.feedId': ?0 }")
	Optional<Notification> findFeedFirstReactionByFeedId(Long feedId);

	@Query("{ 'content.feedId': ?0, 'content.feedFirstReactions.userId': ?1 }")
	Optional<Notification> findByFeedIdAndUserIdInFirstReactions(Long feedId, Long userId);

	@Query("{ 'content.feedId': ?0, 'content.feedReactionCounts.reactionCount': ?1}")
	Optional<Notification> findByFeedIdAndReactionCount(Long feedId, long reactionCount);

	Slice<Notification> findPageByUserId(Pageable pageable, Long userId);

	Long countByUserIdAndIsReadFalse(Long id);
}

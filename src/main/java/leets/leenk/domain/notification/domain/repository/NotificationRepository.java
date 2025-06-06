package leets.leenk.domain.notification.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import leets.leenk.domain.notification.domain.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

	Optional<Notification> findByFeedFirstLikeDetailFeedId(Long feedId);

	@Query("{ 'feedFirstLikeDetail.feedId': ?0, 'feedFirstLikeDetail.feedFirstLikes.userId': ?1 }")
	Optional<Notification> findByFeedIdAndUserIdInFirstLikes(Long feedId, Long id);

	@Query("{ 'feedLikeCountDetail.feedId': ?0, 'feedLikeCountDetail.feedLikeCounts.likeCount': ?1}")
	Optional<Notification> findByFeedIdAndLikeCount(Long feedId, Long likeCount);
}

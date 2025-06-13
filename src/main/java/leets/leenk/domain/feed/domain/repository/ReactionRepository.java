package leets.leenk.domain.feed.domain.repository;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.Reaction;
import leets.leenk.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findByFeedAndUser(Feed feed, User user);

    @Query("SELECT r FROM Reaction r JOIN FETCH r.user WHERE r.feed = :feed")
    List<Reaction> findAllByFeed(Feed feed);
}

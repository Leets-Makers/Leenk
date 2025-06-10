package leets.leenk.domain.feed.domain.repository;

import leets.leenk.domain.feed.domain.enitty.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}

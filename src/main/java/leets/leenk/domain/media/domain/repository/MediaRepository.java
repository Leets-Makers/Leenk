package leets.leenk.domain.media.domain.repository;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.media.domain.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAllByFeedInOrderByPosition(List<Feed> feeds);

    List<Media> findAllByFeedOrderByPosition(Feed feed);
}

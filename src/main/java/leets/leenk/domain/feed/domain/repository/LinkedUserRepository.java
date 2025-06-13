package leets.leenk.domain.feed.domain.repository;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinkedUserRepository extends JpaRepository<LinkedUser, Long> {

    @Query("SELECT lu FROM LinkedUser lu JOIN FETCH lu.user WHERE lu.feed = :feed")
    List<LinkedUser> findAllByFeed(Feed feed);
}

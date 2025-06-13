package leets.leenk.domain.feed.domain.repository;

import java.util.List;

import leets.leenk.domain.feed.domain.entity.LinkedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkedUserRepository extends JpaRepository<LinkedUser, Long> {
	List<LinkedUser> findAllByFeedId(long feedId);
}

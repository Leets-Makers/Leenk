package leets.leenk.domain.feed.domain.repository;

import leets.leenk.domain.feed.domain.enitty.LinkedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkedUserRepository extends JpaRepository<LinkedUser, Long> {
}

package leets.leenk.domain.feed.domain.repository;

import leets.leenk.domain.feed.domain.enitty.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}

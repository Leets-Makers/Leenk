package leets.leenk.domain.user.domain.repository;

import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockedUserRepository extends JpaRepository<UserBlock, Long> {
    List<UserBlock> findAllByBlocker(User blocker);
}

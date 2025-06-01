package leets.leenk.domain.user.domain.repository;

import leets.leenk.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

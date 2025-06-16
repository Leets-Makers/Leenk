package leets.leenk.domain.user.domain.repository;

import leets.leenk.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByIdIn(List<Long> userIds);

    List<User> findAllByOrderByName();
}

package leets.leenk.domain.user.domain.repository;

import leets.leenk.domain.user.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndDeleteDateIsNull(long userId);

    List<User> findAllByIdInAndDeleteDateIsNull(List<Long> userIds);

    List<User> findAllByDeleteDateIsNullOrderByName();

    Slice<User> findAllByDeleteDateIsNullOrderByName(Pageable pageable);

    List<User> findByDeleteDateIsNotNullAndDeleteDateBefore(LocalDateTime threshold);
}

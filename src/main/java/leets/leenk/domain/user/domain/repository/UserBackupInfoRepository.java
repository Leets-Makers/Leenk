package leets.leenk.domain.user.domain.repository;

import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserBackupInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBackupInfoRepository extends JpaRepository<UserBackupInfo, Long> {
    Optional<UserBackupInfo> findByUser(User user);
}

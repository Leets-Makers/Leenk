package leets.leenk.domain.user.domain.repository;

import java.util.List;
import java.util.Optional;

import leets.leenk.domain.user.domain.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {
	List<UserSetting> findAllByIsNewFeedNotifyTrue();

	Optional<UserSetting> findByUserId(Long userId);
}

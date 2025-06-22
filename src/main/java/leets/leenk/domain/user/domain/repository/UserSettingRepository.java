package leets.leenk.domain.user.domain.repository;

import java.util.List;
import java.util.Optional;

import leets.leenk.domain.user.domain.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {
	@Query("SELECT us FROM UserSetting us JOIN FETCH us.user WHERE us.isNewFeedNotify = true")
	List<UserSetting> findAllByIsNewFeedNotifyTrue();

	Optional<UserSetting> findByUserId(Long userId);
}

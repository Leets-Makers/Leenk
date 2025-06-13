package leets.leenk.domain.user.domain.repository;

import java.util.List;

import leets.leenk.domain.user.domain.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {
	List<UserSetting> findAllByIsNewFeedNotifyTrue();
}

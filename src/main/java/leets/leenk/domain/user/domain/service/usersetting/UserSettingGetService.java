package leets.leenk.domain.user.domain.service.usersetting;

import leets.leenk.domain.user.application.exception.UserSettingNotFoundException;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserSetting;
import leets.leenk.domain.user.domain.repository.UserSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingGetService {

    private final UserSettingRepository userSettingRepository;

    public UserSetting findByUser(User user) {
        return userSettingRepository.findByUser(user)
                .orElseThrow(UserSettingNotFoundException::new);
    }
}

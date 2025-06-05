package leets.leenk.domain.user.domain.service;

import leets.leenk.domain.user.domain.entity.UserSetting;
import leets.leenk.domain.user.domain.repository.UserSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingSaveService {

    private final UserSettingRepository userSettingRepository;

    public void save(UserSetting userSetting) {
        userSettingRepository.save(userSetting);
    }
}

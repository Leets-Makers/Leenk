package leets.leenk.domain.user.application.mapper;

import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserSetting;
import org.springframework.stereotype.Component;

@Component
public class UserSettingMapper {

    public UserSetting toDefaultSetting(User user) {
        return UserSetting.builder()
                .isNewLeenkNotify(true)
                .isLeenkStatusNotify(true)
                .isNewFeedNotify(true)
                .isNewReactionNotify(true)
                .user(user)
                .build();
    }
}

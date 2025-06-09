package leets.leenk.domain.user.domain.service;

import leets.leenk.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

    public void updateProfileImage(User user, String profileImage) {
        user.updateProfileImage(profileImage);
    }

    public void updateIntroduction(User user, String introduction) {
        user.updateIntroduction(introduction);
    }

    public void updateMbti(User user, String mbti) {
        user.updateMbti(mbti);
    }
}

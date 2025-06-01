package leets.leenk.domain.user.application.mapper;

import leets.leenk.domain.user.application.dto.response.UserInfoResponse;
import leets.leenk.domain.user.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserInfoResponse toUserInfoResponse(User user) {
        return UserInfoResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .cardinal(user.getCardinal())
                .profileImage(user.getProfileImage())
                .introduction(user.getIntroduction())
                .kakaoTalkId(user.getKakaoTalkId())
                .introduction(user.getIntroduction())
                .mbti(user.getMbti())
                .totalReactionCount(user.getTotalReactionCount())
                .build();
    }
}

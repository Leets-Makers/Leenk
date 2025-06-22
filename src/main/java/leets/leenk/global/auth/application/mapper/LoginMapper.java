package leets.leenk.global.auth.application.mapper;

import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.global.auth.application.dto.response.LoginResponse;
import leets.leenk.global.auth.application.dto.response.OauthUserInfoResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public LoginResponse toLoginResponse(String accessToken, String refreshToken) {
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public LoginResponse toLoginResponse(User user, OauthUserInfoResponse userInfo, String accessToken, String refreshToken) {
        return LoginResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .cardinal(user.getCardinal())
                .position(userInfo.position())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

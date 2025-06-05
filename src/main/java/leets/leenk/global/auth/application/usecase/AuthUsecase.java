package leets.leenk.global.auth.application.usecase;

import leets.leenk.domain.user.application.mapper.UserMapper;
import leets.leenk.domain.user.application.mapper.UserSettingMapper;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserSetting;
import leets.leenk.domain.user.domain.service.UserGetService;
import leets.leenk.domain.user.domain.service.UserSaveService;
import leets.leenk.domain.user.domain.service.UserSettingSaveService;
import leets.leenk.global.auth.application.dto.request.KakaoAccessTokenRequest;
import leets.leenk.global.auth.application.dto.response.LoginResponse;
import leets.leenk.global.auth.application.dto.response.OauthTokenResponse;
import leets.leenk.global.auth.application.dto.response.OauthUserInfoResponse;
import leets.leenk.global.auth.application.mapper.LoginMapper;
import leets.leenk.global.auth.domain.service.KakaoOauthApiService;
import leets.leenk.global.auth.domain.service.OauthApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUsecase {
    private static final String JWT_USER_ID_CLAIM = "id";

    private final UserGetService userGetService;
    private final UserSaveService userSaveService;
    private final UserSettingSaveService userSettingSaveService;
    private final KakaoOauthApiService kakaoOauthApiService;
    private final OauthApiService oauthApiService;
    private final LoginMapper loginMapper;
    private final UserMapper userMapper;
    private final UserSettingMapper userSettingMapper;

    private final JwtDecoder jwtDecoder;

    @Transactional
    public LoginResponse kakaoLogin(KakaoAccessTokenRequest kakaoAccessTokenRequest) {
        OauthTokenResponse response = kakaoOauthApiService.getOauthToken(kakaoAccessTokenRequest.kakaoAccessToken());

        long userId = parseUserId(response);
        Optional<User> optionalUser = userGetService.existById(userId);

        if (optionalUser.isPresent()) {
            return loginMapper.toLoginResponse(response.access_token(), response.refresh_token());
        }

        return saveNewUser(response);
    }

    private long parseUserId(OauthTokenResponse response) {
        String idToken = response.id_token();
        Jwt jwt = jwtDecoder.decode(idToken);

        return Long.parseLong(jwt.getClaimAsString(JWT_USER_ID_CLAIM));
    }

    private LoginResponse saveNewUser(OauthTokenResponse response) {
        OauthUserInfoResponse userInfo = oauthApiService.getUserInfo(response.access_token());
        User user = userMapper.toUser(userInfo);
        UserSetting userSetting = userSettingMapper.toDefaultSetting(user);

        userSaveService.save(user);
        userSettingSaveService.save(userSetting);

        return loginMapper.toLoginResponse(user, response.access_token(), response.refresh_token());
    }
}

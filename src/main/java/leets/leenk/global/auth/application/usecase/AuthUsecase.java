package leets.leenk.global.auth.application.usecase;

import leets.leenk.domain.user.application.mapper.UserMapper;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.service.UserGetService;
import leets.leenk.domain.user.domain.service.UserSaveService;
import leets.leenk.global.auth.application.dto.request.KakaoAccessToken;
import leets.leenk.global.auth.application.dto.response.LoginResponse;
import leets.leenk.global.auth.application.dto.response.OauthTokenResponse;
import leets.leenk.global.auth.application.dto.response.OauthUserInfoResponse;
import leets.leenk.global.auth.application.mapper.LoginMapper;
import leets.leenk.global.auth.domain.service.KakaoOauthApiService;
import leets.leenk.global.auth.domain.service.OauthApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUsecase {
    private final UserGetService userGetService;
    private final UserSaveService userSaveService;
    private final KakaoOauthApiService kakaoOauthApiService;
    private final OauthApiService oauthApiService;
    private final JwtDecoder jwtDecoder;
    private final LoginMapper loginMapper;
    private final UserMapper userMapper;

    @Transactional
    public LoginResponse kakaoLogin(KakaoAccessToken kakaoAccessToken) {
        OauthTokenResponse response = kakaoOauthApiService.getOauthToken(kakaoAccessToken.kakaoAccessToken());
        String idToken = response.id_token();
        Jwt jwt = jwtDecoder.decode(idToken);

        long userId = Long.parseLong(jwt.getClaimAsString("id"));

        Optional<User> optionalUser = userGetService.existById(userId);

        if (optionalUser.isEmpty()) {
            // 인증 서버 유저 정보 조회
            OauthUserInfoResponse userInfo = oauthApiService.getUserInfo(response.access_token());
            // 회원가입
            User user = userMapper.toUser(userInfo);
            userSaveService.save(user);
            // return 유저 정보 및 임시 토큰
            return loginMapper.toLoginResponse(user, response.access_token(), response.refresh_token());
        }

        User user = optionalUser.get();
        return loginMapper.toLoginResponse(response.access_token(), response.refresh_token());
    }
}

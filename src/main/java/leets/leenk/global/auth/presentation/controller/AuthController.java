package leets.leenk.global.auth.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import leets.leenk.global.auth.application.dto.response.LoginResponse;
import leets.leenk.global.auth.application.usecase.AuthUsecase;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AUTH")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthUsecase authUsecase;

    @PostMapping("/kakao/login")
    @Operation(summary = "카카오 login api [for mobile]")
    public CommonResponse<LoginResponse> kakaoLogin(@RequestHeader("Kakao-Access-Token") String kakaoAccessToken) {
        LoginResponse response = authUsecase.kakaoLogin(kakaoAccessToken);

        if (response.userId() == null) {
            return CommonResponse.success(ResponseCode.LOGIN_SUCCESS, response);
        }

        return CommonResponse.success(ResponseCode.INITIAL_LOGIN_SUCCESS, response);
    }

    @PostMapping("/refresh")
    @Operation(summary = "토큰 재발급 API")
    public CommonResponse<LoginResponse> reissueToken(@RequestHeader("Authorization_refresh") String refreshToken) {
        LoginResponse response = authUsecase.reissueToken(refreshToken);

        return CommonResponse.success(ResponseCode.REFRESH_TOKEN_SUCCESS, response);
    }
}

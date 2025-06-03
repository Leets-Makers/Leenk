package leets.leenk.global.auth.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import leets.leenk.global.auth.application.dto.request.KakaoAccessTokenRequest;
import leets.leenk.global.auth.application.dto.response.LoginResponse;
import leets.leenk.global.auth.application.usecase.AuthUsecase;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AUTH")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthUsecase authUsecase;

    @PostMapping("/kakao/login")
    @Operation(summary = "카카오 login api [for mobile]")
    public CommonResponse<LoginResponse> kakaoLogin(@RequestBody @Valid KakaoAccessTokenRequest kakaoAccessTokenRequest) {
        LoginResponse response = authUsecase.kakaoLogin(kakaoAccessTokenRequest);

        if (response.userId() == null) {
            return CommonResponse.success(ResponseCode.LOGIN_SUCCESS, response);
        }

        return CommonResponse.success(ResponseCode.INITIAL_LOGIN_SUCCESS, response);
    }
}

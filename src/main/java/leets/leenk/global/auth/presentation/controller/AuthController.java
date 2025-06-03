package leets.leenk.global.auth.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import leets.leenk.global.auth.application.dto.request.KakaoAccessToken;
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
    public CommonResponse<LoginResponse> kakaoLogin(@RequestBody @Valid KakaoAccessToken kakaoAccessToken) {
        LoginResponse response = authUsecase.kakaoLogin(kakaoAccessToken);
        
        return CommonResponse.success(ResponseCode.LOGIN_SUCCESS, response);
    }
}

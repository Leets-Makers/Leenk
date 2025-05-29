package leets.leenk.domain.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import leets.leenk.domain.user.application.dto.response.UserInfoResponse;
import leets.leenk.domain.user.application.usecase.UserUsecase;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static leets.leenk.domain.user.presentation.ResponseCode.GET_MY_INFO;
import static leets.leenk.domain.user.presentation.ResponseCode.GET_USER_INFO;

@Tag(name = "USER")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUsecase userUsecase;

    @GetMapping
    @Operation(summary = "마이페이지 조회 API")
    public CommonResponse<UserInfoResponse> getMyInfo() {
        UserInfoResponse response = userUsecase.getUserInfo(1L);

        return CommonResponse.success(GET_MY_INFO, response);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "다른 사람 페이지 조회 API")
    public CommonResponse<UserInfoResponse> getUserInfo(@PathVariable long userId) {
        UserInfoResponse response = userUsecase.getUserInfo(userId);

        return CommonResponse.success(GET_USER_INFO, response);
    }
}

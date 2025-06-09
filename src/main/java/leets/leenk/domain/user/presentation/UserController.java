package leets.leenk.domain.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import leets.leenk.domain.user.application.dto.request.IntroductionRequest;
import leets.leenk.domain.user.application.dto.request.MbtiRequest;
import leets.leenk.domain.user.application.dto.request.ProfileImageRequest;
import leets.leenk.domain.user.application.dto.response.UserInfoResponse;
import leets.leenk.domain.user.application.usecase.UserUsecase;
import leets.leenk.global.auth.application.annotation.CurrentUserId;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static leets.leenk.domain.user.presentation.ResponseCode.*;

@Tag(name = "USER")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUsecase userUsecase;

    @GetMapping
    @Operation(summary = "마이페이지 조회 API")
    public CommonResponse<UserInfoResponse> getMyInfo(@Parameter(hidden = true) @CurrentUserId Long userId) {
        UserInfoResponse response = userUsecase.getUserInfo(userId);

        return CommonResponse.success(GET_MY_INFO, response);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "다른 사람 페이지 조회 API")
    public CommonResponse<UserInfoResponse> getUserInfo(@PathVariable long userId) {
        UserInfoResponse response = userUsecase.getUserInfo(userId);

        return CommonResponse.success(GET_USER_INFO, response);
    }

    @PatchMapping("/me/profile-image")
    @Operation(summary = "내 정보 수정 - 프로필 사진")
    public CommonResponse<Void> updateProfileImage(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                   @Valid @RequestBody ProfileImageRequest request) {
        userUsecase.updateProfileImage(userId, request);

        return CommonResponse.success(UPDATE_PROFILE_IMAGE);
    }

    @PatchMapping("/me/introduction")
    @Operation(summary = "내 정보 수정 - 자기소개")
    public CommonResponse<Void> updateIntroduction(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                   @Valid @RequestBody IntroductionRequest request) {
        userUsecase.updateIntroduction(userId, request);

        return CommonResponse.success(UPDATE_INTRODUCTION);
    }

    @PatchMapping("/me/mbti")
    @Operation(summary = "내 정보 수정 - MBTI")
    public CommonResponse<Void> updateMbti(@Parameter(hidden = true) @CurrentUserId Long userId,
                                           @Valid @RequestBody MbtiRequest request) {
        userUsecase.updateMbti(userId, request);

        return CommonResponse.success(UPDATE_MBTI);
    }
}

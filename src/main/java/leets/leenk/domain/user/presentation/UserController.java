package leets.leenk.domain.user.presentation;

import static leets.leenk.domain.user.presentation.ResponseCode.COMPLETE_PROFILE;
import static leets.leenk.domain.user.presentation.ResponseCode.DELETE_USER;
import static leets.leenk.domain.user.presentation.ResponseCode.GET_MY_INFO;
import static leets.leenk.domain.user.presentation.ResponseCode.GET_USER_INFO;
import static leets.leenk.domain.user.presentation.ResponseCode.UPDATE_AGREEMENT;
import static leets.leenk.domain.user.presentation.ResponseCode.UPDATE_INTRODUCTION;
import static leets.leenk.domain.user.presentation.ResponseCode.UPDATE_KAKAO_TALK_ID;
import static leets.leenk.domain.user.presentation.ResponseCode.UPDATE_MBTI;
import static leets.leenk.domain.user.presentation.ResponseCode.UPDATE_PROFILE_IMAGE;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import leets.leenk.domain.user.application.dto.request.AgreementRequest;
import leets.leenk.domain.user.application.dto.request.IntroductionRequest;
import leets.leenk.domain.user.application.dto.request.KakaoTalkIdRequest;
import leets.leenk.domain.user.application.dto.request.MbtiRequest;
import leets.leenk.domain.user.application.dto.request.ProfileImageRequest;
import leets.leenk.domain.user.application.dto.request.RegisterRequest;
import leets.leenk.domain.user.application.dto.response.UserInfoResponse;
import leets.leenk.domain.user.application.usecase.UserUsecase;
import leets.leenk.global.auth.application.annotation.CurrentUserId;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "USER")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUsecase userUsecase;

    @PatchMapping("/agreement")
    @Operation(summary = "약관 동의 입력")
    public CommonResponse<Void> initialAgreement(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                 @RequestBody @Valid AgreementRequest request) {
        userUsecase.initialAgreement(userId, request);

        return CommonResponse.success(UPDATE_AGREEMENT);
    }

    @PatchMapping("/me/profile")
    @Operation(summary = "기본 정보 입력")
    public CommonResponse<Void> completeProfile(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                @RequestBody @Valid RegisterRequest request) {
        userUsecase.completeProfile(userId, request);

        return CommonResponse.success(COMPLETE_PROFILE);
    }

    @GetMapping("/me")
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

    @PatchMapping("/me/kakao-talk-id")
    @Operation(summary = "내 정보 수정 - 카카오톡 id")
    public CommonResponse<Void> updateKakaoTalkId(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                  @Valid @RequestBody KakaoTalkIdRequest request) {
        userUsecase.updateKakaoTalkId(userId, request);

        return CommonResponse.success(UPDATE_KAKAO_TALK_ID);
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

    @DeleteMapping("/me")
    @Operation(summary = "회원 탈퇴 API")
    public CommonResponse<Void> deleteAccount(@Parameter(hidden = true) @CurrentUserId Long userId) {
        userUsecase.leave(userId);

        return CommonResponse.success(DELETE_USER);
    }
}

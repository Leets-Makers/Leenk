package leets.leenk.domain.user.application.usecase;

import leets.leenk.domain.user.application.dto.request.IntroductionRequest;
import leets.leenk.domain.user.application.dto.request.MbtiRequest;
import leets.leenk.domain.user.application.dto.request.ProfileImageRequest;
import leets.leenk.domain.user.application.dto.response.UserInfoResponse;
import leets.leenk.domain.user.application.mapper.UserMapper;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.service.UserGetService;
import leets.leenk.domain.user.domain.service.UserUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUsecase {

    private final UserMapper userMapper;
    private final UserGetService userGetService;
    private final UserUpdateService userUpdateService;

    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo(long userId) {
        User findUser = userGetService.findById(userId);

        return userMapper.toUserInfoResponse(findUser);
    }

    @Transactional
    public void updateProfileImage(long userId, ProfileImageRequest request) {
        User user = userGetService.findById(userId);

        userUpdateService.updateProfileImage(user, request.profileImage());
    }

    @Transactional
    public void updateIntroduction(long userId, IntroductionRequest request) {
        User user = userGetService.findById(userId);

        userUpdateService.updateIntroduction(user, request.introduction());
    }

    @Transactional
    public void updateMbti(long userId, MbtiRequest request) {
        User user = userGetService.findById(userId);

        userUpdateService.updateMbti(user, request.mbti());
    }
}

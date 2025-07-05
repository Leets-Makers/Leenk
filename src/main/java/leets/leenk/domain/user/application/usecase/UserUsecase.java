package leets.leenk.domain.user.application.usecase;

import leets.leenk.domain.user.application.dto.request.AgreementRequest;
import leets.leenk.domain.user.application.dto.request.IntroductionRequest;
import leets.leenk.domain.user.application.dto.request.KakaoTalkIdRequest;
import leets.leenk.domain.user.application.dto.request.MbtiRequest;
import leets.leenk.domain.user.application.dto.request.ProfileImageRequest;
import leets.leenk.domain.user.application.dto.request.RegisterRequest;
import leets.leenk.domain.user.application.dto.response.UserInfoResponse;
import leets.leenk.domain.user.application.exception.UserAlreadyLeaveException;
import leets.leenk.domain.user.application.mapper.UserBackupInfoMapper;
import leets.leenk.domain.user.application.mapper.UserMapper;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserBackupInfo;
import leets.leenk.domain.user.domain.service.user.UserDeleteService;
import leets.leenk.domain.user.domain.service.user.UserGetService;
import leets.leenk.domain.user.domain.service.user.UserUpdateService;
import leets.leenk.domain.user.domain.service.userbackup.UserBackupInfoGetService;
import leets.leenk.domain.user.domain.service.userbackup.UserBackupInfoSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUsecase {

    private final UserMapper userMapper;
    private final UserGetService userGetService;
    private final UserUpdateService userUpdateService;
    private final UserDeleteService userDeleteService;

    private final UserBackupInfoMapper userBackupInfoMapper;
    private final UserBackupInfoSaveService userBackupInfoSaveService;
    private final UserBackupInfoGetService userBackupInfoGetService;

    @Transactional
    public void initialAgreement(long userId, AgreementRequest request) {
        User user = userGetService.findById(userId);

        userUpdateService.updateAgreement(user, request);
    }

    @Transactional
    public void completeProfile(long userId, RegisterRequest request) {
        User user = userGetService.findById(userId);

        userUpdateService.completeProfile(user, request);
    }

    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo(long userId) {
        User findUser = userGetService.findById(userId);

        return userMapper.toUserInfoResponse(findUser);
    }

    @Transactional
    public void updateKakaoTalkId(long userId, KakaoTalkIdRequest request) {
        User user = userGetService.findById(userId);

        userUpdateService.updateKakaoTalkId(user, request.kakaoTalkId());
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

    @Transactional
    public void leave(long userId) {
        User user = userGetService.findById(userId);

        if (userBackupInfoGetService.existsByUser(user)) {
            throw new UserAlreadyLeaveException();
        }

        UserBackupInfo userBackupInfo = userBackupInfoMapper.toUserBackupInfo(user);

        userBackupInfoSaveService.save(userBackupInfo);
        userDeleteService.leave(user);
    }
}

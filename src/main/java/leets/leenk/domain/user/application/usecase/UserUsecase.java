package leets.leenk.domain.user.application.usecase;

import leets.leenk.domain.user.application.dto.response.UserInfoResponse;
import leets.leenk.domain.user.application.mapper.UserMapper;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUsecase {

    private final UserMapper userMapper;
    private final UserGetService userGetService;

    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo(long userId) {
        User findUser = userGetService.findById(userId);

        return userMapper.toUserInfoResponse(findUser);
    }
}

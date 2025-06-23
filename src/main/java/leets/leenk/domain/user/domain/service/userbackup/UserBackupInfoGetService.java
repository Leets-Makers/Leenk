package leets.leenk.domain.user.domain.service.userbackup;

import leets.leenk.domain.user.application.exception.UserBackupInfoException;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserBackupInfo;
import leets.leenk.domain.user.domain.repository.UserBackupInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBackupInfoGetService {

    private final UserBackupInfoRepository userBackupInfoRepository;

    public UserBackupInfo findByUser(User user) {
        return userBackupInfoRepository.findByUser(user)
                .orElseThrow(UserBackupInfoException::new);
    }
}

package leets.leenk.domain.user.domain.service.userbackup;

import leets.leenk.domain.user.domain.entity.UserBackupInfo;
import leets.leenk.domain.user.domain.repository.UserBackupInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBackupInfoDeleteService {
    private final UserBackupInfoRepository userBackupInfoRepository;

    public void delete(UserBackupInfo userBackupInfo) {
        userBackupInfoRepository.delete(userBackupInfo);
    }
}

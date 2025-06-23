package leets.leenk.domain.user.domain.service.userbackup;

import leets.leenk.domain.user.domain.entity.UserBackupInfo;
import leets.leenk.domain.user.domain.repository.UserBackupInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBackupInfoSaveService {

    private final UserBackupInfoRepository userBackupInfoRepository;

    public void save(UserBackupInfo userBackupInfo) {
        userBackupInfoRepository.save(userBackupInfo);
    }
}

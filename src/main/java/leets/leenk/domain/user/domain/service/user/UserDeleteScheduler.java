package leets.leenk.domain.user.domain.service.user;

import jakarta.transaction.Transactional;
import leets.leenk.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDeleteScheduler {
    private final UserGetService userGetService;
    private final UserDeleteService userDeleteService;

    @Transactional
    @Scheduled(cron = "0 0 3 * * *") // 새벽 3시
    public void deleteExpiredUsers() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(7);
        List<User> usersToDelete = userGetService.findAllDeleteUser(threshold);

        for (User user : usersToDelete) {
            try {
                userDeleteService.delete(user);
                log.info("✅ 유저 ID={} 및 관련 데이터 삭제 완료", user.getId());
            } catch (Exception e) {
                log.error("❌ 유저 ID={} 삭제 중 오류 발생", user.getId(), e);
            }
        }
    }
}

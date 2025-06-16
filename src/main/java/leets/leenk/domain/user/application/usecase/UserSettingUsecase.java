package leets.leenk.domain.user.application.usecase;

import leets.leenk.domain.user.domain.service.NotionDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingUsecase {

    private final NotionDatabaseService notionDatabaseService;

    // 알림 설정 변경


    // 의견 남기기 (슬랙 + 노션 DB)
    public void sendFeedback(String feedback) {
        notionDatabaseService.sendFeedback(feedback);
    }
}

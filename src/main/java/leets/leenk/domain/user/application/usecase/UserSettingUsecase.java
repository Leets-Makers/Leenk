package leets.leenk.domain.user.application.usecase;

import leets.leenk.domain.user.domain.service.NotionDatabaseService;
import leets.leenk.domain.user.domain.service.SlackWebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingUsecase {

    private final NotionDatabaseService notionDatabaseService;
    private final SlackWebhookService slackWebhookService;

    // 알림 설정 변경


    public void sendFeedback(String feedback) {
        notionDatabaseService.sendFeedback(feedback);
        slackWebhookService.sendFeedback(feedback);
    }
}

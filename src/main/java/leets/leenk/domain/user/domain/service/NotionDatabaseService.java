package leets.leenk.domain.user.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotionDatabaseService {

    private static final String NOTION_INSERT_URI = "https://api.notion.com/v1/pages/";

    @Value("${notion.token}")
    private String NOTION_TOKEN;

    @Value("${notion.database_id}")
    private String DATABASE_ID;

    @Value("${notion.version}")
    private String NOTION_VERSION;

    private final RestClient notionRestClient;

    @Async
    public void sendFeedback(String feedback) {
        String today = LocalDate.now().toString();

        Map<String, Object> requestBody = Map.of(
                "parent", Map.of(
                        "type", "database_id",
                        "database_id", DATABASE_ID
                ),
                "properties", Map.of(
                        "피드백", Map.of(
                                "title", List.of(
                                        Map.of("text", Map.of("content", feedback))
                                )
                        ),
                        "날짜", Map.of(
                                "date", Map.of("start", today)
                        )
                )
        );

        notionRestClient.post()
                .uri(NOTION_INSERT_URI)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + NOTION_TOKEN)
                .header("Notion-Version", NOTION_VERSION)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(requestBody)
                .retrieve()
                .toBodilessEntity();
    }
}

package leets.leenk.domain.feed.application.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FeedResponse(
        @Schema(description = "피드 Id", example = "1")
        long feedId,

        @Schema(description = "작성자 정보")
        FeedAuthorResponse author,

        @Schema(description = "썸네일 이미지", example = "https://s3.example.com/thumb_neil.jpg")
        String thumbNeil,

        @Schema(description = "총 공감 개수", example = "1004")
        long totalReactionCount
) {
}

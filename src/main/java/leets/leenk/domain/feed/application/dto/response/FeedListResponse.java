package leets.leenk.domain.feed.application.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import leets.leenk.global.common.dto.CommonPageableResponse;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FeedListResponse(

        @Schema(description = "내가 받은 총 공감 수", example = "1004")
        Long totalReactionCount,

        @Schema(description = "피드 목록")
        List<FeedResponse> feeds,

        @Schema(description = "페이징 정보")
        CommonPageableResponse pageable
) {
}

package leets.leenk.domain.feed.application.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import leets.leenk.global.common.dto.CommonPageableResponse;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FeedListResponse(

        @Schema(description = "내가 받은 총 공감 수 (실제 API에서는 /feed/me에서만 반환됩니다. 스웨거 예시 용)", example = "12345")
        Long totalReactionCount,

        @Schema(description = "피드 목록")
        List<FeedResponse> feeds,

        @Schema(description = "페이징 정보")
        CommonPageableResponse pageable
) {
}

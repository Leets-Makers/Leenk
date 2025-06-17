package leets.leenk.domain.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import leets.leenk.global.common.dto.CommonPageableResponse;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FeedUserListResponse(
        @Schema(description = "사용자 목록")
        List<FeedUserResponse> users,

        @Schema(description = "페이징 정보")
        CommonPageableResponse pageable
) {
}

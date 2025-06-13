package leets.leenk.global.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommonPageableResponse(
        @Schema(description = "페이지 번호 (0부터 시작)", example = "0")
        int pageNumber,

        @Schema(description = "페이지 크기", example = "10")
        int pageSize,

        @Schema(description = "현재 페이지의 요소 개수", example = "10")
        int numberOfElements,

        @Schema(description = "다음 페이지 존재 여부", example = "true")
        boolean hasNext,

        @Schema(description = "현재 페이지의 요소가 비어 있는지의 여부", example = "false")
        boolean empty
) {
}

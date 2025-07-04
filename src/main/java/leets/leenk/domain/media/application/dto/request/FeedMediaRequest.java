package leets.leenk.domain.media.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import leets.leenk.domain.media.domain.entity.enums.MediaType;

public record FeedMediaRequest(
        @NotNull
        @Positive
        @Schema(description = "1부터 시작하는 정수")
        Integer position,

        @NotBlank
        @Schema(description = "미디어 S3 URL", example = "https://s3.example.com/image.jpg")
        String mediaUrl,

        @NotNull
        @Schema(description = "이미지, 비디오", example = "IMAGE or VIDEO")
        MediaType mediaType
) {
}

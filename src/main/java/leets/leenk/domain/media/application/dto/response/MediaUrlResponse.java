package leets.leenk.domain.media.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record MediaUrlResponse(
        @Schema(description = "파일 이름", example = "image.jpg")
        String fileName,
        @Schema(description = "미디어 S3 URL", example = "https://s3.example.com/image.jpg")
        String mediaUrl
) {
}

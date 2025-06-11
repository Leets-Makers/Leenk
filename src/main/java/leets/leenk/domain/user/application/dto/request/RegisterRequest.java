package leets.leenk.domain.user.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank
        String kakaoTalkId,
        @Size(max = 200)
        String introduction,
        String profileImage,
        @Size(max = 4)
        String mbti
) {
}

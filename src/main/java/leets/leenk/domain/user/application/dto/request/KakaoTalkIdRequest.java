package leets.leenk.domain.user.application.dto.request;

import jakarta.validation.constraints.Size;

public record KakaoTalkIdRequest(
        @Size(max = 20)
        String kakaoTalkId
) {
}

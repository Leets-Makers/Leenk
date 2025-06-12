package leets.leenk.global.auth.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoginResponse(
        Long userId,
        String name,
        Integer cardinal,
        String accessToken,
        String refreshToken
) {
}

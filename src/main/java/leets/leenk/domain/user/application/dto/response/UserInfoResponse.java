package leets.leenk.domain.user.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserInfoResponse(
        long id,
        String name,
        int cardinal,
        String profileImage,
        String kakaoTalkId,
        String introduction,
        String mbti
) {
}

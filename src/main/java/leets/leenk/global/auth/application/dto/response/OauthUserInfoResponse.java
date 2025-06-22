package leets.leenk.global.auth.application.dto.response;

public record OauthUserInfoResponse(
        long userId,
        String name,
        int cardinal,
        String position
) {
}

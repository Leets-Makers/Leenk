package leets.leenk.global.auth.application.dto.response;

public record OauthUserInfoResponse(
        long userId,
        String name,
        String email,
        String tel,
        int cardinal
) {
}

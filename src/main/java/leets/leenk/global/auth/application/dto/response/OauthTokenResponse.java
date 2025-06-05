package leets.leenk.global.auth.application.dto.response;

public record OauthTokenResponse(
        String access_token,
        String refresh_token,
        String id_token,
        String token_type,
        Integer expires_in
) {
}

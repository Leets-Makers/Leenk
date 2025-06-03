package leets.leenk.global.auth.application.dto.response;

public record OauthErrorResponse(
        String error,
        String error_description
) {
}

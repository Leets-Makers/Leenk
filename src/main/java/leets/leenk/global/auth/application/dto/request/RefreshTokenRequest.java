package leets.leenk.global.auth.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest (
        @NotBlank
        String refreshToken
){
}

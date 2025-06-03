package leets.leenk.global.auth.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import leets.leenk.global.auth.application.exception.UserInActiveException;
import leets.leenk.domain.user.application.exception.UserNotFoundException;
import leets.leenk.global.auth.application.dto.response.OauthErrorResponse;
import leets.leenk.global.auth.application.dto.response.OauthTokenResponse;
import leets.leenk.global.auth.application.exception.OauthException;
import leets.leenk.global.auth.application.property.KakaoOauthProperty;
import leets.leenk.global.auth.application.property.OauthProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class KakaoOauthApiService {
    private final OauthProperty oauthProperty;
    private final KakaoOauthProperty kakaoOauthProperty;

    private final RestClient authRestClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OauthTokenResponse getOauthToken(String kakaoAccessToken) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", kakaoOauthProperty.getKakaoGrantType());
        body.add("client_id", oauthProperty.getClientId());
        body.add("client_secret", oauthProperty.getClientSecret());
        body.add(kakaoOauthProperty.getKakaoGrantTypeName(), kakaoAccessToken);

        return authRestClient.post()
                .uri(oauthProperty.getOauthServerTokenUri())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    OauthErrorResponse error = objectMapper.readValue(response.getBody(), OauthErrorResponse.class);

                    switch (error.error()) {
                        case "WAE-001" -> throw new UserInActiveException(error.error_description());
                        case "WAE-002" -> throw new UserNotFoundException(error.error_description());
                        default -> throw new OauthException();
                    }
                })
                .body(OauthTokenResponse.class);
    }
}

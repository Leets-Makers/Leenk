package leets.leenk.global.auth.application.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "auth.oauth2")
public class OauthProperty {
    private String clientId;
    private String clientSecret;
    private String oauthIss;
    private String oauthServerTokenUri;
    private String oauthJwkSetUri;
    private String oauthServerUserInfoUri;
}

package leets.leenk.global.auth.application.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "auth.oauth2.kakao")
public class KakaoOauthProperty {
    private String kakaoGrantType;
    private String kakaoGrantTypeName;
}

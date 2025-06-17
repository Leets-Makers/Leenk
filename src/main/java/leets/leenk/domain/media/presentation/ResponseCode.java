package leets.leenk.domain.media.presentation;

import leets.leenk.global.common.response.ResponseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode implements ResponseCodeInterface {

    GET_MEDIA_URL(1500, HttpStatus.OK, "프리사인드 url 발급에 성공했습니다.");


    private final int code;
    private final HttpStatus status;
    private final String message;
}

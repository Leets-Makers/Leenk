package leets.leenk.domain.feed.presentation;

import leets.leenk.global.common.response.ResponseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode implements ResponseCodeInterface {

    GET_ALL_FEED(1200, HttpStatus.OK, "전체 피드 조회에 성공했습니다."),
    GET_FEED_DETAIL(1201, HttpStatus.OK, "피드 상세 조회에 성공했습니다."),
    UPLOAD_FEED(1202, HttpStatus.OK, "피드 업로드에 성공했습니다."),
    UPDATE_FEED(1203, HttpStatus.OK, "피드 수정에 성공했습니다."),

    CREATE_REACTION(1204, HttpStatus.OK, "피드 공감에 성공했습니다."),
    GET_REACTED_USERS(1205, HttpStatus.OK, "피드에 공감한 사용자 조회에 성공했습니다."),
    GET_ALL_USERS(1206, HttpStatus.OK, "함께한 유저 추가를 위한 사용자 조회에 성공했습니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;
}

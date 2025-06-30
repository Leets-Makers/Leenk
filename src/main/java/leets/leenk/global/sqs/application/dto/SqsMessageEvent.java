package leets.leenk.global.sqs.application.dto;

import leets.leenk.domain.notification.application.exception.InvalidNotificationRequestException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SqsMessageEvent {

	private final String title;
	private final String content;
	private final String fcmToken;

	@Builder
	private SqsMessageEvent(String title, String content, String fcmToken) {
		this.title = title;
		this.content = content;
		this.fcmToken = fcmToken;
		validateSqsMessageEvent();
	}

	private void validateSqsMessageEvent(){
		if(isEmptyString(content) || isEmptyString(title) || isEmptyString(fcmToken)){
			throw new InvalidNotificationRequestException();
		}
	}

	private boolean isEmptyString(String string) {
		return string == null || string.isEmpty();
	}

}

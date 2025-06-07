package leets.leenk.domain.sqs.application.dto;

import leets.leenk.domain.notification.application.exception.InvalidNotificationRequestException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SqsMessageEvent {

	private final String title;
	private final String content;
	private final String deviceToken;

	@Builder
	private SqsMessageEvent(String title, String content, String deviceToken) {
		this.title = title;
		this.content = content;
		this.deviceToken = deviceToken;
		validateSqsMessageEvent();
	}

	private void validateSqsMessageEvent(){
		if(isEmptyString(content) || isEmptyString(title) || isEmptyString(deviceToken)){
			throw new InvalidNotificationRequestException();
		}
	}

	private boolean isEmptyString(String string) {
		return string == null || string.isEmpty();
	}

}

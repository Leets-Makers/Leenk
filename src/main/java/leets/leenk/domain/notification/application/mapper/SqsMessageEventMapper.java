package leets.leenk.domain.notification.application.mapper;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.application.dto.SqsMessageEvent;
import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.user.domain.entity.User;

@Component
public class SqsMessageEventMapper {

	public SqsMessageEvent toSqsMessageEvent(NotificationType notificationType,
		User user){

		return SqsMessageEvent.builder()
			.title(notificationType.getTitle())
			.content(notificationType.getFormattedContent("유저123"))	// Todo : Feed의 글쓴이로 변경
			.deviceToken(user.getFcmToken())
			.build();
	};
}

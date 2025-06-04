package leets.leenk.domain.notification.application.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import leets.leenk.domain.notification.domain.entity.LinkedUser;
import leets.leenk.domain.notification.domain.entity.TagNotificationEvent;

@Component
public class TagNotificationEventMapper {

	public TagNotificationEvent toTagNotificationEvent(List<LinkedUser>linkedUsers){
		return TagNotificationEvent.builder()
				.linkedUsers(linkedUsers)
				.build();
	}
}

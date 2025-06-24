package leets.leenk.domain.notification.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.global.sqs.application.dto.SqsMessageEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationSaveService {
	private final NotificationRepository notificationRepository;
	private final ApplicationEventPublisher eventPublisher;

	public void save(Notification notification, SqsMessageEvent sqsMessageEvent, boolean isNotificationEnabled) {
		notificationRepository.save(notification);
		if(isNotificationEnabled)
			eventPublisher.publishEvent(sqsMessageEvent);
	}
}

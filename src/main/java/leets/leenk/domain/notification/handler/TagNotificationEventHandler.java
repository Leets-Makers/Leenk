package leets.leenk.domain.notification.handler;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import leets.leenk.domain.notification.application.mapper.SqsMessageEventMapper;
import leets.leenk.domain.notification.domain.entity.Notification;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagNotificationEventHandler {

	private final ApplicationEventPublisher eventPublisher;
	private final SqsMessageEventMapper sqsMessageEventMapper;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handler(List<Notification> notificationEvents){
		notificationEvents.forEach((notification ->
			eventPublisher.publishEvent(
				sqsMessageEventMapper.toSqsMessageEvent(notification))
			)
		);
	}
}

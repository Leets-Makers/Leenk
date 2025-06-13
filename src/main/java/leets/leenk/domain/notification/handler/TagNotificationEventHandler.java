package leets.leenk.domain.notification.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import leets.leenk.global.sqs.application.mapper.SqsMessageEventMapper;
import leets.leenk.domain.notification.domain.entity.Notification;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagNotificationEventHandler {

	private final ApplicationEventPublisher eventPublisher;
	private final SqsMessageEventMapper sqsMessageEventMapper;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handler(Notification notificationEvent){
		eventPublisher.publishEvent(sqsMessageEventMapper.toSqsMessageEvent(notificationEvent));
	}
}

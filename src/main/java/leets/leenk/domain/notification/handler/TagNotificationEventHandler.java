package leets.leenk.domain.notification.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import leets.leenk.domain.notification.application.mapper.SqsMessageEventMapper;
import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.TagNotificationEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagNotificationEventHandler {

	private final ApplicationEventPublisher eventPublisher;
	private final SqsMessageEventMapper sqsMessageEventMapper;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handler(TagNotificationEvent event){
		event.getLinkedUsers().forEach(linkedUser ->
			eventPublisher.publishEvent(sqsMessageEventMapper
				.toSqsMessageEvent(NotificationType.FEED_TAG, linkedUser.getUser())
			));
	}
}

package leets.leenk.domain.notification.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import leets.leenk.domain.sqs.application.mapper.SqsMessageEventMapper;
import leets.leenk.domain.notification.domain.entity.event.FeedReactionCountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class FeedReactionCountEventHandler {

	private final ApplicationEventPublisher eventPublisher;
	private final SqsMessageEventMapper sqsMessageEventMapper;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handler(FeedReactionCountEvent feedReactionCountEvent){
		eventPublisher.publishEvent(sqsMessageEventMapper
			.fromFeedReactionCountEvent(feedReactionCountEvent));
	}
}

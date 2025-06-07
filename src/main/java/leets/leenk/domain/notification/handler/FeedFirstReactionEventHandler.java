package leets.leenk.domain.notification.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import leets.leenk.domain.notification.sqs.mapper.SqsMessageEventMapper;
import leets.leenk.domain.notification.domain.entity.event.FeedFirstReactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class FeedFirstReactionEventHandler {

	private final ApplicationEventPublisher eventPublisher;
	private final SqsMessageEventMapper sqsMessageEventMapper;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handler(FeedFirstReactionEvent feedFirstReactionEvent){
		eventPublisher.publishEvent(sqsMessageEventMapper
			.fromFeedFirstReaction(feedFirstReactionEvent));
	}
}

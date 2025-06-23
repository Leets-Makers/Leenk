package leets.leenk.global.sqs.application.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import leets.leenk.global.sqs.application.mapper.AwsSqsManager;
import leets.leenk.global.sqs.application.dto.SqsMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
@RequiredArgsConstructor
@Log4j2
public class SqsEventHandler {

	private final AwsSqsManager awsSqsManager;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleEvent(SqsMessageEvent event) {
		log.info("handleEvent event = {}", event);

		SendMessageRequest request = awsSqsManager.createPushAlarmMessageRequest(event);
		awsSqsManager.sendMessageToQueue(request);
	}
}

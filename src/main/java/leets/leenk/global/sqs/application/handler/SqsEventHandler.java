package leets.leenk.global.sqs.application.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import leets.leenk.global.sqs.application.mapper.AwsSqsManager;
import leets.leenk.global.sqs.application.dto.SqsMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqsEventHandler {

	private final AwsSqsManager awsSqsManager;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleEvent(SqsMessageEvent event) {
		try {
			SendMessageRequest request = awsSqsManager.createPushAlarmMessageRequest(event);
			awsSqsManager.sendMessageToQueue(request);
		} catch (Exception e){
			log.error("SQS 메세지 전송 실패 : {}", e.getMessage());
		}
	}
}

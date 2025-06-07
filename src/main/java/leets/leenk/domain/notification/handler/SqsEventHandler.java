package leets.leenk.domain.notification.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import leets.leenk.domain.sqs.application.mapper.AwsSqsManager;
import leets.leenk.domain.sqs.application.dto.SqsMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
@RequiredArgsConstructor
@Log4j2
public class SqsEventHandler {

	private final AwsSqsManager awsSqsManager;

	@EventListener
	public void handleEvent(SqsMessageEvent event) {
		log.info("handleEvent event = {}", event);

		SendMessageRequest request = awsSqsManager.createPushAlarmMessageRequest(event);
		awsSqsManager.sendMessageToQueue(request);
	}
}

package leets.leenk.domain.sqs.application.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import leets.leenk.domain.sqs.application.dto.SqsMessageEvent;
import leets.leenk.domain.sqs.config.AwsSqsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
@RequiredArgsConstructor
@Log4j2
public class AwsSqsManager {

	private final AwsSqsProperties awsSqsProperties;
	private final SqsAsyncClient sqsAsyncClient;

	public void sendMessageToQueue(SendMessageRequest event) {
		sqsAsyncClient.sendMessage(event);
	}

	public SendMessageRequest createPushAlarmMessageRequest(SqsMessageEvent event) {
		return SendMessageRequest.builder()
			.queueUrl(awsSqsProperties.getQueueUrl())
			.delaySeconds(awsSqsProperties.getMessageDelaySecs())
			.messageBody(event.getContent())
			.messageAttributes(Map.of(
				"title", convertToAttributeValue(event.getTitle()),
				"deviceToken", convertToAttributeValue(event.getDeviceToken())
			))
			.build();
	}


	private MessageAttributeValue convertToAttributeValue(String value) {
		log.debug("convertToAttributeValue = {}", value);
		return MessageAttributeValue.builder()
			.dataType("String")
			.stringValue(value)
			.build();
	}
}

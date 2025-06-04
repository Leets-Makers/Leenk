package leets.leenk.domain.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class AwsSqsProperties {

	@Value("${myapp.sqs.queue-name}")
	private String queueName;
	@Value("${myapp.sqs.queue-url}")
	private String queueUrl;;
	@Value("${myapp.sqs.messageDelaySecs}")
	private int messageDelaySecs;
}

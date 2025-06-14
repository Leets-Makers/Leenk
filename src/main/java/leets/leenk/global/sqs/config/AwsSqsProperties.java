package leets.leenk.global.sqs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "myapp.sqs")
@Component
public class AwsSqsProperties {

	private String queueName;
	private String queueUrl;
	private int messageDelaySecs;
}

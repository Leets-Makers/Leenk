package leets.leenk.global.sqs.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.retries.StandardRetryStrategy;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SqsClientConfig {

	@Value("${spring.cloud.aws.region.static}")
	private String region;

	@Bean
	public SqsAsyncClient sqsAsyncClient() {
		StandardRetryStrategy retryStrategy = StandardRetryStrategy.builder()
			.maxAttempts(3)
			.build();

		return SqsAsyncClient.builder()
			.credentialsProvider(DefaultCredentialsProvider.create())
			.region(Region.of(region))
			.overrideConfiguration(o -> o.retryStrategy(retryStrategy))
			.build();
	}
}

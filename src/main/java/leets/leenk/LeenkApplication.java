package leets.leenk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableMongoAuditing
@EnableJpaRepositories(basePackages = {
	"leets.leenk.domain.feed.domain.repository",
	"leets.leenk.domain.user.domain.repository",
})
@EnableMongoRepositories(basePackages = "leets.leenk.domain.notification.domain.repository")
public class LeenkApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeenkApplication.class, args);
	}

}

package projectB.meongbti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class MeongbtiApplication {
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "./app/config/meongbti/real-application.yml";
	public static void main(String[] args) {
		new SpringApplicationBuilder(MeongbtiApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
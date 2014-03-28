package purluno.shirobowl

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppConfigShiro)
class AppConfig {
	@Bean
	ShiroBowl shiroBowl() {
		new ShiroBowl()
	}

	@Bean
	BowlService bowlService() {
		new BowlService()
	}
}

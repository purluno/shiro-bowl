package purluno.shirobowl

import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * 부팅 메인 클래스 (JUnit 대용)
 * 
 * @author 송영환
 */
class Main {
	static main(args) {
		def ctx = new AnnotationConfigApplicationContext(AppConfig)
		def shiroBowl = ctx.getBean("shiroBowl") as ShiroBowl
		shiroBowl.start()
	}
}

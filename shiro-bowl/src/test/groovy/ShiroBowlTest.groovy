import javax.annotation.Resource

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import purluno.shirobowl.AppConfig
import purluno.shirobowl.ShiroBowl

@ContextConfiguration(classes = AppConfig)
@RunWith(SpringJUnit4ClassRunner)
class ShiroBowlTest {
	@Resource
	ShiroBowl shiroBowl

	@Test
	void test() {
		shiroBowl.start()
	}
}

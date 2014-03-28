package purluno.shirobowl

import javax.annotation.Resource

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authz.UnauthenticatedException
import org.apache.shiro.authz.UnauthorizedException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 애플리케이션의 시작 지점
 * 
 * @author 송영환
 */
class ShiroBowl {
	protected Logger logger = LoggerFactory.getLogger(ShiroBowl)

	@Resource
	BowlService bowlService

	void expectsNoException(Closure closure) {
		closure.call()
	}

	void expects(Class<? extends Throwable> throwable, Closure closure) {
		try {
			closure.call()
		} catch (Throwable t) {
			if (t in throwable) {
				logger.debug("expected: {}: {}", t.class.name, t.message)
			} else {
				throw t
			}
		}
	}

	def start() {
		def subject = SecurityUtils.subject
		logger.debug("\n\n=== 1. read a user named 'someone' ===\n")
		expects(UnauthenticatedException) { bowlService.readUser("someone") }
		
		logger.debug("\n\n=== 2. log in as 'jsmith' with a false password ===\n")
		expects(IncorrectCredentialsException) {
			subject.login(new UsernamePasswordToken("jsmith", "falsePassword"))
		}
		
		logger.debug("\n\n=== 3. log in as 'jsmith' correctly ===\n")
		expectsNoException {
			subject.login(new UsernamePasswordToken("jsmith", "jsmithPassword"))
		}
		
		logger.debug("\n\n=== 4. read a user named 'woman' ===\n")
		expectsNoException { bowlService.readUser("woman") }
		
		logger.debug("\n\n=== 5. view a server ===\n")
		expects(UnauthorizedException) { bowlService.viewServer() }
		
		logger.debug("\n\n=== 6. log in as 'root' correctly ===\n")
		expectsNoException {
			subject.login(new UsernamePasswordToken("root", "rootPassword"))
		}
		
		logger.debug("\n\n=== 7. view a server ===\n")
		expectsNoException { bowlService.viewServer() }
		
		logger.debug("\n\n=== 8. log out ===\n")
		expectsNoException { subject.logout() }
		
		logger.debug("\n\n=== 9. read a user named 'clown' ===\n")
		expects(UnauthenticatedException) { bowlService.readUser("clown") }
		
		logger.debug("\n\n=== THE END ===\n")
	}
}

package purluno.shirobowl

import org.apache.shiro.SecurityUtils
import org.apache.shiro.mgt.DefaultSecurityManager
import org.apache.shiro.mgt.SecurityManager
import org.apache.shiro.realm.Realm
import org.apache.shiro.realm.text.PropertiesRealm
import org.apache.shiro.spring.LifecycleBeanPostProcessor
import org.springframework.beans.factory.config.MethodInvokingFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfigShiro {
	@Bean
	Realm defaultRealm() {
		def realm = new PropertiesRealm()
		realm.resourcePath = "classpath:defaultRealm.properties"
		realm
	}

	@Bean
	SecurityManager securityManager() {
		new DefaultSecurityManager(defaultRealm())
	}

	@Bean
	LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		new LifecycleBeanPostProcessor()
	}

	@Bean
	MethodInvokingFactoryBean SecurityManagerRegistrar() {
		new MethodInvokingFactoryBean(
			targetClass: SecurityUtils,
			targetMethod: "setSecurityManager",
			arguments: securityManager())
	}
}

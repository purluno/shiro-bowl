package purluno.shirobowl

import org.apache.shiro.SecurityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BowlService {
	static Logger logger = LoggerFactory.getLogger(BowlService)

	void readUser(String name) {
		logger.debug("readUser: attempting to read a user '{}'...", name)
		def subject = SecurityUtils.subject
		subject.checkPermission("user:read")
		logger.debug("readUser: successfully read a user '{}'!", name)
	}

	void viewServer() {
		logger.debug("viewServer: attempting to view a server...")
		def subject = SecurityUtils.subject
		subject.checkPermission("server:view")
		logger.debug("viewServer: successfully viewed a server!")
	}
}

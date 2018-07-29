package com.schooldeal.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @
 * @
 */
public class PropertyConfigurer extends
		org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {

	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		try {
			// Mysql jdbc
			String jdbcUrl = props.getProperty("jdbcUrl").trim();
			String decryJdbcUrl = new String(EncryptionUtil.decode(EncryptionUtil.hex2byte(jdbcUrl.trim()), Constants.DBKEYEES.getBytes()));
			props.setProperty("jdbcUrl", decryJdbcUrl);
			// Mysql username
			String userame = props.getProperty("userame").trim();
			String decryUserame = new String(EncryptionUtil.decode(EncryptionUtil.hex2byte(userame.trim()), Constants.DBKEYEES.getBytes()));
			props.setProperty("userame", decryUserame);
			// Mysql password
			String password = props.getProperty("password").trim();
			String decryPassword = new String(EncryptionUtil.decode(EncryptionUtil.hex2byte(password.trim()), Constants.DBKEYEES.getBytes()));
			props.setProperty("password", decryPassword);
			

		} catch (Exception e) {
			logger.error("decode password in properties error!", e);
		}
	}
}
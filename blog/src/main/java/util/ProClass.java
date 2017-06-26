package util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ProClass {

	@Value("#{prop.accessUrl}")
	private String accessUrl;

	@Value("#{prop.redisUrl}")
	private String redisUrl;

	@Value("#{prop.redisPort}")
	private int redisPort;

	@Value("#{prop.loginHtml}")
	private String loginHtml;
	
	@Value("#{prop.userIconUrl}")
	private String userIconUrl;
	
	@Value("#{prop.userIconPath}")
	private String userIconPath;

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public String getRedisUrl() {
		return redisUrl;
	}

	public void setRedisUrl(String redisUrl) {
		this.redisUrl = redisUrl;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	public String getLoginHtml() {
		return loginHtml;
	}

	public void setLoginHtml(String loginHtml) {
		this.loginHtml = loginHtml;
	}

	public String getUserIconUrl() {
		return userIconUrl;
	}

	public void setUserIconUrl(String userIconUrl) {
		this.userIconUrl = userIconUrl;
	}

	public String getUserIconPath() {
		return userIconPath;
	}

	public void setUserIconPath(String userIconPath) {
		this.userIconPath = userIconPath;
	}
	
	
}

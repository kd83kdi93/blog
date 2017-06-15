package util;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ProClass implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7829570553556890104L;

	@Value("#{prop.accessUrl}")
	private String accessUrl;

	@Value("#{prop.redisUrl}")
	private String redisUrl;

	@Value("#{prop.redisPort}")
	private int redisPort;

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

}

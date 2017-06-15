package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

@Repository
public class RedisManager {

	@Autowired
	private ProClass proClass;

	private Jedis jedis = null;
	
	@PostConstruct
	private void initJedis() {
		if (jedis == null) {
			jedis = new Jedis(proClass.getRedisUrl(), proClass.getRedisPort());
		}
	}

	public <T> T getObjectByKey(String key) {
		byte[] keyByte = jedis.get(key.getBytes());
		T t = null;
		if (keyByte != null) {
			ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(keyByte);
			ObjectInputStream objectInput = null;

			byteArrayInput = new ByteArrayInputStream(keyByte);
			try {
				objectInput = new ObjectInputStream(byteArrayInput);
				t = (T) objectInput.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	public <T> void setObjectByKey(String key, T t) {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		ObjectOutputStream objectOutput = null;

		try {
			objectOutput = new ObjectOutputStream(byteOutput);
			objectOutput.writeObject(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String s = jedis.set(key.getBytes(), byteOutput.toByteArray());
		System.out.println(s);
	}

}

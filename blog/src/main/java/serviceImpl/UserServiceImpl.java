package serviceImpl;

import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.User;
import mapper.UserMapper;
import service.BlogService;
import service.UserService;
import util.Email;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BlogService blogService;

	@Override
	public User find(String name, String password) {
		User u = new User();
		u.setName(name);
		u.setPassword(password);
		u = userMapper.get(u);
		return u;
	}

	@Override
	public void register(String name, String password) {
		User u = new User();
		u.setName(name);
		String authCode = UUID.randomUUID().toString();
		u.setAuthcode(authCode);
		u.setPassword(password);
		try {
			userMapper.add(u);
			String text = "http://localhost:8080/blog/user/activited?name=" + name + "&authCode=" + authCode;
			Email.send(name, "账户激活", text);
			blogService.initBlogUserInfo(u.getId());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void activited(String name, String authCode) {
		User u = new User();
		u.setName(name);
		u.setAuthcode(authCode);
		userMapper.setActivited(u);

	}

	@Override
	public boolean resetPassword(String name) {
		User u = userMapper.getByName(name);
		boolean result = false;
		if (u != null) {
			String password = UUID.randomUUID().toString().substring(0, 4);
			u.setPassword(password);
			userMapper.setPassword(u);
			String text = "新的密码为   " + password;
			try {
				Email.send(name, "密码重置", text);
			} catch (MessagingException e) {
				e.printStackTrace();
			} finally {
				result = true;
			}
		}
		return result;
	}

	@Override
	public User findByName(String name) {
		User u = userMapper.getByName(name);
		return u;
	}

}

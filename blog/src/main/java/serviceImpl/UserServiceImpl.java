package serviceImpl;

import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.User;
import mapper.UserMapper;
import service.UserService;
import util.Email;
import util.Result;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
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
			String text = "http://localhost:8080/blog/user/activited?name="+name+"&authCode="+authCode;
			Email.send(name, "º§ªÓ¡¥Ω”" ,text);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		userMapper.add(u);
	}

	@Override
	public void activited(String name, String authCode) {
		User u = new User();
		u.setName(name);
		u.setAuthcode(authCode);
		userMapper.setActivited(u);
		
	}

	@Override
	public String resetPassword(String name) {
		User u = find(name, null);
		String result = null;
		if (u!=null) {
			String password = UUID.randomUUID().toString().substring(0, 4);
			u.setPassword(password);
			userMapper.setPassword(u);
			result = password;
		}
		return result;
	}

}

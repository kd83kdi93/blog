package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.User;
import mapper.UserMapper;

@Controller
@ResponseBody
@RequestMapping("/test")
public class HelloWorld {
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/hello")
	public Object hello() {
		return userMapper.get1();
	}

	@RequestMapping("/hello1")
	public Object hello1() {
		User user = new User();
		user.setId(3);
		user.setName("7763");
		userMapper.add(user);
		return "success";
	}
}

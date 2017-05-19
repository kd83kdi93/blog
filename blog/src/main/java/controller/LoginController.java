package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.User;
import service.UserService;
import util.Result;

@Controller
@ResponseBody
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping("/register")
	public Object register(String name, String password) {
		User u = userService.find(name);
		Result result = new Result();
		if (u==null) {
			userService.register(name, password);
			result.setSuccess(true);
			result.setData(u);
		}
		return result;
	}

	@RequestMapping("/login")
	public Object login(String name, String password) {
		User u = userService.find(name);
		Result result = new Result();
		if (u!=null) {
			result.setSuccess(true);
			result.setData(u.toString());
		}
		return result;
	}
	
	
	@RequestMapping("/activited")
	public Object activited(String name, String authCode) {
		userService.activited(name, authCode);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}
	
	
	@RequestMapping("/resetpassword")
	public Object activited(String name) {
		String newPassWord = userService.resetPassword(name);
		Result result = new Result();
		if (newPassWord != null) {
			result.setSuccess(true);
			result.setData(newPassWord);
		}
		return result;
	}
}

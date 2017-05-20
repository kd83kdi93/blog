package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.User;
import service.UserService;
import util.CheckAndResult;
import util.Result;
import util.StringMatch;

@Controller
@ResponseBody
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping("/register")
	public Object register(String name, String password) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		if (result.isSuccess()) {
			User u = userService.find(name);
			if (u==null) {
				userService.register(name, password);
				result.setData(u);
			}
		}
		return result;
	}

	@RequestMapping("/login")
	public Object login(String name, String password) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		if (result.isSuccess()) {
			User u = userService.find(name);
			if (u!=null) {
				result.setData(u.toString());
			}
		}
		return result;
	}
	
	
	@RequestMapping("/activited")
	public Object activited(String name, String authCode) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		if (result.isSuccess()) {
			userService.activited(name, authCode);
		}
		return result;
	}
	
	
	@RequestMapping("/resetpassword")
	public Object activited(String name) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		if (result.isSuccess()) {
			String newPassWord = userService.resetPassword(name);
			if (newPassWord != null) {
				result.setStateAndData(true, newPassWord);
			} else {
				result.setStateAndData(false, "√‹¬Î÷ÿ÷√ ß∞‹");
			}
		}
		return result;
	}
}

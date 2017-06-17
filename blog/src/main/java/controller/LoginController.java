package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.User;
import service.UserService;
import util.CheckAndResult;
import util.ProClass;
import util.Result;

@Controller
@ResponseBody
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private UserService userService;

	@Autowired
	private ProClass proClass;

	@RequestMapping("/register")
	public Object register(String name, String password) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		if (result.isSuccess()) {
			User u = userService.findByName(name);
			if (u == null) {
				userService.register(name, password);
			} else {
				result.setStateAndData(false, "用户名已存在");
			}
		}
		return result;
	}

	@RequestMapping("/login")
	public Object login(String name, String password, HttpSession session) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		User u = null;
		if (result.isSuccess()) {
			session.removeAttribute("user");
			u = userService.find(name, password);
			do {
				if (u == null) {
					result.setStateAndData(false, "用户名或密码错误");
					break;
				}
				if (u.getActivited() != 1) {
					result.setStateAndData(false, "用户还未激活");
				} else {
					result.setData(u);
					session.setAttribute("user", u);
				}
			} while (false);
		}
		return result;
	}

	@RequestMapping("/logout")
	public Object logout(HttpSession session) {
		Result result = new Result();
		session.removeAttribute("user");
		result.setSuccess(true);
		return result;
	}

	@RequestMapping("/activited")
	public Object activited(String name, String authCode) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		if (result.isSuccess()) {
			userService.activited(name, authCode);
		}
		return "<a href='" + proClass.getLoginHtml() + "'> return login </a>";
	}

	@RequestMapping("/resetpassword")
	public Object activited(String name, HttpSession session) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		if (result.isSuccess()) {
			boolean isChanged = userService.resetPassword(name);
			if (isChanged) {
				result.setStateAndData(true, "密码已经重置成功,请查看邮箱");
			} else {
				result.setStateAndData(false, "密码重置失败");
			}
		}
		return result;
	}

	@RequestMapping("who")
	public Object who(HttpSession session) {
		User u = (User) session.getAttribute("user");
		Result result = new Result();
		if (u != null) {
			result.setData(u);
			result.setSuccess(true);
		}
		return result;
	}
}

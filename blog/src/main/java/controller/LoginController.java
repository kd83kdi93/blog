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
			User u = userService.find(name, password);
			if (u == null) {
				userService.register(name, password);
			} else {
				result.setStateAndData(false, "�û��Ѵ���");
			}
		}
		return result;
	}

	@RequestMapping("/login")
	public Object login(String name, String password) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		if (result.isSuccess()) {
			User u = userService.find(name, password);
			while (true) {
				if (u == null) {
					result.setStateAndData(false, "�û�������ȷ");
					break;
				}
				if (u.getActivited() != 1) {
					result.setStateAndData(false, "����ʺŻ�δ����");
				} else {
					result.setData(u);
				}
				break;
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
			boolean isChanged = userService.resetPassword(name);
			if (isChanged) {
				result.setStateAndData(true, "���������Ѿ��������������鿴�������޸�");
			} else {
				result.setStateAndData(false, "��������ʧ��");
			}
		}
		return result;
	}
}

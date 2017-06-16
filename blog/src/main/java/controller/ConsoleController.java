package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ConsoleService;
import util.Result;

@Controller
@ResponseBody
public class ConsoleController {

	@Autowired
	private ConsoleService consoleService;

	@RequestMapping("/console")
	public Object console(String command) {
		Result result = new Result();
		String text = consoleService.execute(command);
		if (text != null) {
			result.setSuccess(true);
			result.setData(text);
		}
		return result;
	}
}

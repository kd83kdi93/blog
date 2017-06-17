package interceptor;

import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import domain.User;
import util.Result;

public class LevelOneUserInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		User user = (User) arg0.getSession().getAttribute("user");
		if (user != null && user.getLevel()>1) {
			return true;
		}
		Result result = new Result();
		ObjectOutputStream out = new ObjectOutputStream(arg1.getOutputStream());
		out.writeObject(result);
		out.close();
		return false;
	}

}

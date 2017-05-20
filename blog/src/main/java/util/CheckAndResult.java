package util;

public class CheckAndResult {
	private static StringMatch match;
	
	public static Result checkEmailBackResult(String email) {
		boolean emailFlag = match.checkEmail(email);
		Result result = new Result();
		if (emailFlag) {
			result.setSuccess(true);
		} else {
			result.setStateAndData(false, "邮箱格式不正确");
		}
		return result;
	}
	
}

package util;

public class CheckAndResult {
	private static StringMatch match;
	
	public static boolean checkNotNull(String parm) {
		boolean result = false;
		if (parm != null) {
			result = true;
		}
		return result;
	}
	
	
	public static Result checkEmailBackResult(String email) {
		boolean notNullFlag = checkNotNull(email);
		boolean emailFlag = match.checkEmail(email);
		Result result = new Result();
		if (emailFlag && notNullFlag) {
			result.setSuccess(true);
		} else {
			result.setStateAndData(false, "邮箱格式不正确");
		}
		return result;
	}
	
}

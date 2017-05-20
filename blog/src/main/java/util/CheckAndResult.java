package util;

public class CheckAndResult {
	private static StringMatch match;
	
	public static Result checkEmailBackResult(String email) {
		boolean emailFlag = match.checkEmail(email);
		Result result = new Result();
		if (emailFlag) {
			result.setSuccess(true);
		} else {
			result.setStateAndData(false, "�����ʽ����ȷ");
		}
		return result;
	}
	
}

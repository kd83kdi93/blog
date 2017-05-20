package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatch {
	private static String emailReg = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * —È÷§” œ‰
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		Pattern regex = Pattern.compile(emailReg);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}
}

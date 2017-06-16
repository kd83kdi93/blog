package util;

public class HtmlLabelWriter {
	public static int writeLessThan(char[] buf, int index) {
		buf[index] = '&';
		buf[index+1] = 'l';
		buf[index+2] = 't';
		buf[index+3] = ';';
		return index+3;
	}

	public static int writeGreaterThan(char[] buf, int index) {
		buf[index] = '&';
		buf[index+1] = 'g';
		buf[index+2] = 't';
		buf[index+3] = ';';
		return index+3;
	}

	public static int writeBr(char[] buf, int index) {
		buf[index] = '<';
		buf[index+1] = 'b';
		buf[index+2] = 'r';
		buf[index+3] = '>';
		return index+3;
	}
}

package serviceImpl;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import service.ConsoleService;
import util.HtmlLabelWriter;

@Service
public class ConsoleServiceImpl implements ConsoleService {

	@Override
	public String execute(String command) {
		String result = null;
		StringBuilder sb = new StringBuilder();
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		InputStreamReader input = null;
		int size = 0;
		try {
			process = runtime.exec("cmd.exe /C " + command);
			input = new InputStreamReader(process.getInputStream());
			char[] buf = new char[256];
			char[] changedBuf = null;
			while ((size = input.read(buf)) != -1) {
				changedBuf = new char[512];
				for (int i = 0, j = 0; i < size && j < 512; i++, j++) {
					if (buf[i] == '<') {
						j = HtmlLabelWriter.writeLessThan(changedBuf, j);
					} else if (buf[i] == '>') {
						j = HtmlLabelWriter.writeGreaterThan(changedBuf, j);
					} else if (buf[i] == '\n') {
						j = HtmlLabelWriter.writeBr(changedBuf, j);
					} else {
						changedBuf[j] = buf[i];
					}
				}
				sb.append(changedBuf);
			}
			if (sb.length() != 0) {
				result = sb.toString();
			}
		} catch (

		IOException e) {
			result = e.toString();
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}

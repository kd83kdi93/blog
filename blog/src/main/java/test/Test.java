package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import redis.clients.jedis.Jedis;
import util.StringMatch;

public class Test{
	
	@org.junit.Test
	public void test() {
		String reg = "^[0-9a-zA-Z]+.*@.*\\.[a-zA-Z]{2}$";
		List<String> list= new ArrayList<>();
		list.add("460720965@qq.com");
		list.add("liyuanheng_4444@126.com");
		list.add("-23342dsf");
		list.add("12323@c.om");
		list.add("0293fdkl.com");
		list.add("123265kl'@..com");
		Pattern pattern = Pattern.compile(reg);
		for(String tmp : list) {
			System.out.println(tmp+" "+StringMatch.checkEmail(tmp));
		}
		
	}
	
	
	@org.junit.Test
	public void test1(){
		Jedis jd = new Jedis("localhost",6380);
        System.out.println(jd.ping());
        jd.set("abc", "123");
	}
	
	
	@org.junit.Test
	public void test2() {
		

	}
	
	public static void main(String[] args) {
		int i=1;
		int j =2;
		System.out.println(++i == j);
		System.out.println(i+" "+j);
	}

}

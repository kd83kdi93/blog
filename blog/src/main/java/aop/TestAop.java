package aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Repository;

@Aspect
@Repository
public class TestAop {
	@Before("execution(public domain.User serviceImpl.UserServiceImpl.find(String,String))")
	public void test() {
		System.out.println("-----AOP-----");
	}
}

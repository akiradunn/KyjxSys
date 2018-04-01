package com.brave.test;
import java.util.Date;

import org.junit.Test;
//@RunWith(SpringRunner.class) // 此注解开启spring框架环境，保证测试类拥有spring的环境，这样@autowired注解功能才能使用
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration // 开启注解
public class Case1 {
	@Test
	public void TestCase(){
		System.out.println(new Date().toLocaleString());
	}
}

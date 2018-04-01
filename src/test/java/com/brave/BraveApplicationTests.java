package com.brave;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) // 此注解开启spring框架环境，保证测试类拥有spring的环境，这样@autowired注解功能才能使用
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BraveApplicationTests {
	@Before
	public void Test2() {
		System.out.println("测试");
	}

	@Test
	public void Test() {
		System.out.println("测试");
	}
}

package com.brave.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MyUserDetailsServiceTest {
	@Autowired
	private MyUserDetailsService myuserdetailsService;
	private String username = "admin";

	@Before
	public void init() {
		System.out.println("测试开始：");
	}

	@Test
	public void testLoadUserByUsername() {
		System.out.println("这是测试类的结果");
		System.out.println(myuserdetailsService.loadUserByUsername(username));
	}

}

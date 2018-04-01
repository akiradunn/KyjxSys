package com.brave.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.brave.service.UsersDaoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsersApiTest {
	@Autowired
	private UsersDaoService usersDaoService;

	@Test
	public void test() {
		System.out.println(usersDaoService.getAllUsers());
	}

}

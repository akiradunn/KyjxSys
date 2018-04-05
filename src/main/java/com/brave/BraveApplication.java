package com.brave;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.boot.SpringApplication;
@Controller
@SpringBootApplication
@MapperScan("com.brave.Dao")
public class BraveApplication {
	public static void main(String[] args) {
		SpringApplication.run(BraveApplication.class, args);
		System.out.println("test after module hot start");;
		// for(int i=0 ; i<10 ; i++){
		// GoEasy goEasy = new GoEasy("BC-b83a739800b54333af8ad728b90eb695");
		// goEasy.publish("test", "helloworld");
		// System.out.println("第"+i+"次广播:");
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
	}
}

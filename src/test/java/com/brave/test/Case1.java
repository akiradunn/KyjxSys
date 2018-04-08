package com.brave.test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
//@RunWith(SpringRunner.class) // 此注解开启spring框架环境，保证测试类拥有spring的环境，这样@autowired注解功能才能使用
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration // 开启注解
public class Case1 {
	@Test
	public void TestCase(){
		SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    Calendar calendar = java.util.Calendar.getInstance();
	    try {
	    	calendar.setTime(simpleDateFormat.parse(new Date().toLocaleString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    calendar.add(java.util.Calendar.DATE, 0); 
	    String setTime = simpleDateFormat.format(calendar.getTime());
	    calendar.add(java.util.Calendar.DATE, 100); // 向前一周；如果需要向后一周，用正数即可
	    String endTime = simpleDateFormat.format(calendar.getTime());
	}
}

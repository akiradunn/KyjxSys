package com.brave.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brave.entity.Users;
import com.brave.service.UsersDaoService;
import com.brave.service.VisitDaoService;
import com.brave.show.ShowMyInfo;

@RestController
public class UsersApi {
	@Autowired
	private UsersDaoService usersDaoService;
	@Autowired
	private VisitDaoService visitDaoService;
	
	/**管理员查看所有真实用户
	 * @return
	 */
	@RequestMapping(value = "/api/users/all_users", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Users> getAllUsers() {
		return usersDaoService.getAllUsers();
	}
	//查看我的信息时的返回操作
	@RequestMapping(value = "/api/users/now_users", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ShowMyInfo getNowUsers() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDaoService.getVisitUID(username);
		ShowMyInfo ret = new ShowMyInfo();
		Users users = usersDaoService.getUsersFromId(u_id);
		ret.setU_name(users.getU_name());
		ret.setU_identity(users.getU_identity());
		ret.setU_sex(users.getU_sex());
		ret.setU_wholePoints(users.getU_wholePoints());
		ret.setUsername(username);
		ret.setPassword(visitDaoService.getVisit(username).getPassword());
		return ret;
	}
	
	/**通过用户名来查找真实用户
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/api/users/others/{username}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Users getUsers(@PathVariable("username") String username) {
		int u_id = visitDaoService.getVisitUID(username);
		System.out.println("enter users api module");
		return usersDaoService.getUsersFromId(u_id);
	}
	
	/**通过真实姓名来删除真实用户
	 * @param u_name
	 */
	@RequestMapping(value = "/api/users/{u_name}", method = RequestMethod.DELETE)
	public void deleteUsers(@PathVariable("u_name") String u_name) {
		usersDaoService.deleteUsers(u_name);
	}
}

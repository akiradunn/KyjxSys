package com.brave.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brave.dao.VisitDao;
import com.brave.entity.Users;
import com.brave.service.UsersDaoService;

@RestController
public class UsersApi {
	@Autowired
	private UsersDaoService usersDaoService;
	@Autowired
	private VisitDao visitDao;
	
	@RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Users> getAllUsers() {
		return usersDaoService.getAllUsers();
	}

	@RequestMapping(value = "/api/users/others/{username}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Users> getUsers(@PathVariable("username") String username) {
		int u_id = visitDao.getVisitUID(username);
		List<Users> ret = new ArrayList<Users>();
		ret.add(usersDaoService.getUsersFromId(u_id));
		return ret;
	}
	
	@RequestMapping(value = "/api/users/{u_name}", method = RequestMethod.DELETE)
	public void deleteUsers(@PathVariable("u_name") String u_name) {
		usersDaoService.deleteUsers(u_name);
	}
}

package com.brave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.brave.dao.UsersDao;
import com.brave.entity.Users;

@Service("usersDaoService")
public class UsersDaoService implements UsersDao{
	@Autowired
	@Qualifier("usersDao")
	private UsersDao usersDao;

	public Users getUsersFromName(String u_name) {
		// TODO Auto-generated method stub
		return usersDao.getUsersFromName(u_name);
	}

	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return usersDao.getAllUsers();
	}

	public void deleteUsers(String u_name) {
		// TODO Auto-generated method stub
		usersDao.deleteUsers(u_name);
	}

	@Override
	public Users getUsersFromId(int u_id) {
		// TODO Auto-generated method stub
		return usersDao.getUsersFromId(u_id);
	}

	@Override
	public void insertUsers(int u_id, String u_name, String u_identity, int u_wholePoints) {
		// TODO Auto-generated method stub
		
	}

}

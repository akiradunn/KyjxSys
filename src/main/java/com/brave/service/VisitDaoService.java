package com.brave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.brave.dao.VisitDao;
import com.brave.entity.Visit;
@Service("visitDaoService")
public class VisitDaoService implements VisitDao {
	@Autowired
	@Qualifier("visitDao")
	private VisitDao visitDao;
	@Override
	public Visit getVisit(String username) {
		// TODO Auto-generated method stub
		return visitDao.getVisit(username);
	}

	@Override
	public int getVisitUID(String username) {
		// TODO Auto-generated method stub
		return visitDao.getVisitUID(username);
	}

	@Override
	public void updateUsers(int u_id, String username, String modifiedusername, String password) {
		// TODO Auto-generated method stub
		visitDao.updateUsers(u_id, username, modifiedusername, password);
	}

	@Override
	public List<Visit> getVisitList() {
		// TODO Auto-generated method stub
		return visitDao.getVisitList();
	}

	@Override
	public Visit getSpecifiedVisit(int u_id, String username) {
		// TODO Auto-generated method stub
		return visitDao.getSpecifiedVisit(u_id, username);
	}

	@Override
	public void deleteSpecifiedVisit(int u_id, String username) {
		// TODO Auto-generated method stub
		visitDao.deleteSpecifiedVisit(u_id, username);
	}

	@Override
	public void insertVisit(int u_id, String username, String password) {
		// TODO Auto-generated method stub
		visitDao.insertVisit(u_id, username, password);
	}
}

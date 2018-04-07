package com.brave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.brave.dao.UsersKyxmDao;
import com.brave.entity.UsersKyxm;

@Service("usersKyxmDaoService")
public class UsersKyxmDaoService implements UsersKyxmDao {
	@Autowired
	@Qualifier("usersKyxmDao")
	private UsersKyxmDao usersKyxmDao;

	@Override
	public List<UsersKyxm> getAllUsersKyxm() {
		// TODO Auto-generated method stub
		return usersKyxmDao.getAllUsersKyxm();
	}

	@Override
	public List<UsersKyxm> getUsersKyxmFromUid(int u_id) {
		// TODO Auto-generated method stub
		return usersKyxmDao.getUsersKyxmFromUid(u_id);
	}

	@Override
	public List<UsersKyxm> getUsersKyxmFromKid(int k_id) {
		// TODO Auto-generated method stub
		return usersKyxmDao.getUsersKyxmFromKid(k_id);
	}

	@Override
	public UsersKyxm getUsersKyxmFromUidKid(int u_id, int k_id) {
		// TODO Auto-generated method stub
		return usersKyxmDao.getUsersKyxmFromUidKid(u_id, k_id);
	}

	@Override
	public void insertKyxm(int u_id, int k_id, String k_status, String k_applyTime, String k_setTime, String k_endTime,
			boolean k_scoreApplied, boolean k_completed) {
		// TODO Auto-generated method stub
		usersKyxmDao.insertKyxm(u_id, k_id, k_status, k_applyTime, k_setTime, k_endTime, k_scoreApplied, k_completed);
	}

	@Override
	public List<UsersKyxm> getAppliedKyxm() {
		// TODO Auto-generated method stub
		return usersKyxmDao.getAppliedKyxm();
	}

	@Override
	public List<UsersKyxm> getApplingKyxm() {
		// TODO Auto-generated method stub
		return usersKyxmDao.getApplingKyxm();
	}

	@Override
	public void setAppliedKyxm(int u_id, int k_id, String k_setTime) {
		// TODO Auto-generated method stub
		usersKyxmDao.setAppliedKyxm(u_id, k_id, k_setTime);
	}

	@Override
	public void deleteUsersKyxm(int u_id, int k_id) {
		// TODO Auto-generated method stub
		usersKyxmDao.deleteUsersKyxm(u_id, k_id);
	}

}

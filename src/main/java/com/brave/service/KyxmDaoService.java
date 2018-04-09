package com.brave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.brave.dao.KyxmDao;
import com.brave.entity.Kyxm;

@Service("kyxmDaoService")
public class KyxmDaoService implements KyxmDao {
	@Autowired
	@Qualifier("kyxmDao")
	private KyxmDao kyxmDao;

	@Override
	public List<Kyxm> getAllKyxm() {
		// TODO Auto-generated method stub
		return kyxmDao.getAllKyxm();
	}

	@Override
	public List<Kyxm> getKyxmFromName(String k_name) {
		// TODO Auto-generated method stub
		return kyxmDao.getKyxmFromName(k_name);
	}

	@Override
	public void deleteKyxm(String k_name) {
		// TODO Auto-generated method stub
		kyxmDao.deleteKyxm(k_name);
	}

	@Override
	public void insertKyxm(int k_id, String k_name, String k_category, int k_score) {
		// TODO Auto-generated method stub
		kyxmDao.insertKyxm(k_id, k_name, k_category, k_score);
	}

	@Override
	public Kyxm getKyxmFromId(int k_id) {
		// TODO Auto-generated method stub
		return kyxmDao.getKyxmFromId(k_id);
	}

	@Override
	public void updateKyxm(int k_id, String k_name, String k_category, int k_score) {
		// TODO Auto-generated method stub
		kyxmDao.updateKyxm(k_id, k_name, k_category, k_score);
	}

	@Override
	public void deleteSpecifiedKyxm(int k_id) {
		// TODO Auto-generated method stub
		kyxmDao.deleteSpecifiedKyxm(k_id);
	}

}

package com.brave.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brave.dao.VisitDao;
import com.brave.service.UsersKyxmDaoService;

public class UsersKyxmApi {
	@Autowired
	private UsersKyxmDaoService usersKyxmDaoService;
	@Autowired
	private VisitDao visitDao;
	/**通过k_name将课题插入我的课题列表之中
	 * /api/usersKyxm/name/{k_name},Method=POST代表用名字添加课题
	 * /api/usersKyxm/id/{k_id},Method=POST代表用id添加课题
	 * @param k_name
	 * @return
	 */
	@RequestMapping(value = "/api/usersKyxm/id/{k_id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String addMyKyxm(@PathVariable("k_id") int k_id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDao.getVisitUID(username);
		Date date = new Date();
		usersKyxmDaoService.insertKyxm(u_id, k_id, "已申报", date.toLocaleString(), null, null, false, false);
		return "ok";
	}
}

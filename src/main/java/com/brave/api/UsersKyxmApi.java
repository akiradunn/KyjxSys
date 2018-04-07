package com.brave.api;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.brave.dao.VisitDao;
import com.brave.entity.UsersKyxm;
import com.brave.service.UsersKyxmDaoService;
@RestController
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
	@RequestMapping(value = "/api/usersKyxm/id/{k_id}", method = RequestMethod.POST)
	public String addMyKyxm(@PathVariable("k_id") int k_id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDao.getVisitUID(username);
		Date date = new Date();
		usersKyxmDaoService.insertKyxm(u_id, k_id, "已申报", date.toLocaleString(), null, null, false, false);
		return "add ok";
	}
	@RequestMapping(value = "/api/usersKyxm/others/applying/", method = RequestMethod.GET)
	public List<UsersKyxm> getApplingKyxm(){
		return usersKyxmDaoService.getApplingKyxm();
	}
	@RequestMapping(value = "/api/usersKyxm/others/applyied/", method = RequestMethod.GET)
	public List<UsersKyxm> getAppliedKyxm(){
		return usersKyxmDaoService.getAppliedKyxm();
	}
}

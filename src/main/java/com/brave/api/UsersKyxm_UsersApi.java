package com.brave.api;
import java.util.Date;
import java.util.LinkedList;
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
import com.brave.service.KyxmDaoService;
import com.brave.service.UsersKyxmDaoService;
import com.brave.show.ShowKyxms;
import com.brave.utils.Transform;
@RestController
public class UsersKyxm_UsersApi {
	@Autowired
	private UsersKyxmDaoService usersKyxmDaoService;
	@Autowired
	private VisitDao visitDao;
	@Autowired
	private KyxmDaoService kyxmDaoService;
	/**用户查看我的课题列表-我的课题模块
	 * @return
	 */
	@RequestMapping(value = "/api/usersKyxm/myclass", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<ShowKyxms> getMyKyxm() {// 用户获取我的课题列表
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDao.getVisitUID(username);
		List<UsersKyxm> store = usersKyxmDaoService.getUsersKyxmFromUid(u_id);
		return Transform.transfromUsersKyxmList(store, kyxmDaoService);
	}
	/**用户申请课题结项-我的课题模块
	 * @param k_id
	 * @return
	 */
	@RequestMapping(value = "/api/userkyxm/applySetMyKyxm/{k_id}", method = RequestMethod.PUT)
	public String applySetMyKyxm(@PathVariable("k_id") int k_id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDao.getVisitUID(username);
		UsersKyxm check = usersKyxmDaoService.getUsersKyxmFromUidKid(u_id, k_id);
		if(check.getK_status()=="已立项" || "已立项".equals(check.getK_status())){
			usersKyxmDaoService.applySetMyKyxm(u_id, k_id, "待审核课题结项");
		}else{
			return "failed";
		}
		return "success";
	}
	/**用户申请课题积分-我的课题模块
	 * @param k_id
	 * @return
	 */
	@RequestMapping(value = "/api/userkyxm/applyMyKyxmScore/{k_id}", method = RequestMethod.PUT)
	public String applyMyKyxmScore(@PathVariable("k_id") int k_id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDao.getVisitUID(username);
		UsersKyxm check = usersKyxmDaoService.getUsersKyxmFromUidKid(u_id, k_id);
		if(check.getK_status()=="已结项" || "已结项".equals(check.getK_status())){
			usersKyxmDaoService.applySetMyKyxm(u_id, k_id, "待审核积分申报");
		}else{
			return "failed";
		}
		return "success";
	}
	/**用户添加课题至我的列表-课题广场,搜索模块
	 * @param k_id
	 * @return
	 */
	@RequestMapping(value = "/api/usersKyxm/addMyKyxm/{k_id}", method = RequestMethod.POST)
	public String addMyKyxm(@PathVariable("k_id") int k_id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDao.getVisitUID(username);
		UsersKyxm check = usersKyxmDaoService.getUsersKyxmFromUidKid(u_id, k_id);
		if(check!=null){
			return "failed";
		}
		Date date = new Date();
		usersKyxmDaoService.insertKyxm(u_id, k_id, "待审核课题立项", date.toLocaleString(), null, null, false, false);
		return "success";
	}
}

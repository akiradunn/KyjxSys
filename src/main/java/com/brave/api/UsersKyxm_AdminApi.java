package com.brave.api;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.brave.service.KyxmDaoService;
import com.brave.service.UsersKyxmDaoService;
import com.brave.show.ShowKyxms;
import com.brave.utils.Transform;
@RestController
public class UsersKyxm_AdminApi {
	@Autowired
	private UsersKyxmDaoService usersKyxmDaoService;
	@Autowired
	private VisitDao visitDao;
	@Autowired
	private KyxmDaoService kyxmDaoService;
	//管理员查看申报课题立项的列表-审批课题立项模块
	@RequestMapping(value = "/api/userkyxm/getApplyingKyxmList", method = RequestMethod.GET)
	public List<ShowKyxms> getApplyingKyxmList(){
		List<UsersKyxm> store = usersKyxmDaoService.getApplyingKyxmList("待审核课题立项");
		return Transform.transfromUsersKyxmList(store, kyxmDaoService);
	}
	//管理员查看申报课题结项的列表-审批课题结项模块
	@RequestMapping(value = "/api/userkyxm/getApplyingCompletingKyxmList", method = RequestMethod.GET)
	public List<ShowKyxms> getApplyingCompletingKyxmList(){
		List<UsersKyxm> store = usersKyxmDaoService.getApplyingKyxmList("待审核课题结项");
		return Transform.transfromUsersKyxmList(store, kyxmDaoService);
	}
	//管理员查看申报课题积分的列表-审批课题积分模块
		@RequestMapping(value = "/api/userkyxm/getApplyingScoreKyxmList", method = RequestMethod.GET)
		public List<ShowKyxms> getApplyingScoreKyxmList(){
			List<UsersKyxm> store = usersKyxmDaoService.getApplyingKyxmList("待审核积分申报");
			return Transform.transfromUsersKyxmList(store, kyxmDaoService);
		}
		
	//管理员通过课题立项申请-审批课题立项模块
	@RequestMapping(value = "/api/userkyxm/passApplyingKyxm/{k_id}", method = RequestMethod.PUT)
	public List<ShowKyxms> passApplyingKyxm(@PathVariable("k_id") int k_id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDao.getVisitUID(username);
		SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    Calendar calendar = java.util.Calendar.getInstance();
	    try {
			calendar.setTime(simpleDateFormat.parse(new Date().toLocaleString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    calendar.add(java.util.Calendar.DATE, 0); 
	    String k_setTime = simpleDateFormat.format(calendar.getTime());
	    calendar.add(java.util.Calendar.DATE, 100); // 结项时间默认为立项时间之后100天
	    String k_endTime = simpleDateFormat.format(calendar.getTime());
	    String k_status = "已立项";
		usersKyxmDaoService.operateApplyingKyxm(u_id, k_id, k_status, k_setTime, k_endTime, false, false);;
		List<UsersKyxm> store = usersKyxmDaoService.getApplyingKyxmList("待审核课题立项");
		return Transform.transfromUsersKyxmList(store, kyxmDaoService);
	}
	//管理员通过课题结项申请-审批课题结项模块
	@RequestMapping(value = "/api/userkyxm/passApplyingCompletingKyxm/{k_id}", method = RequestMethod.PUT)
	public List<ShowKyxms> passApplyingCompletingKyxm(@PathVariable("k_id") int k_id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDao.getVisitUID(username);
		UsersKyxm check = usersKyxmDaoService.getUsersKyxmFromUidKid(u_id, k_id);
		if(check.getK_status()=="待审核课题结项" || "待审核课题结项".equals(check.getK_status())){
			usersKyxmDaoService.operateApplyingKyxm(u_id, k_id, "已结项", check.getK_setTime(), check.getK_endTime(), false, true);;
		}
		List<UsersKyxm> store = usersKyxmDaoService.getApplyingKyxmList("待审核课题立项");
		return Transform.transfromUsersKyxmList(store, kyxmDaoService);
	}
	//管理员通过课题积分申请-审批课题积分模块
		@RequestMapping(value = "/api/userkyxm/passApplyingScoreKyxm/{k_id}", method = RequestMethod.PUT)
		public List<ShowKyxms> passApplyingScoreKyxm(@PathVariable("k_id") int k_id){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			int u_id = visitDao.getVisitUID(username);
			UsersKyxm check = usersKyxmDaoService.getUsersKyxmFromUidKid(u_id, k_id);
			if((check.getK_status()=="待审核积分申报" || "待审核积分申报".equals(check.getK_status()))&&!check.isK_scoreApplied()){
				usersKyxmDaoService.operateApplyingKyxm(u_id, k_id, "已获分", check.getK_setTime(), check.getK_endTime(), true, true);;
			}
			List<UsersKyxm> store = usersKyxmDaoService.getApplyingKyxmList("待审核积分申报");
			return Transform.transfromUsersKyxmList(store, kyxmDaoService);
		}
	//管理员拒绝课题立项申请-审批课题立项模块
		@RequestMapping(value = "/api/userkyxm/rejectApplyingKyxm/{k_id}", method = RequestMethod.PUT)
		public List<ShowKyxms> rejectApplyingKyxm(@PathVariable("k_id") int k_id){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			int u_id = visitDao.getVisitUID(username);
			usersKyxmDaoService.operateApplyingKyxm(u_id, k_id, "拒绝课题立项", null, null, false, false);
			List<UsersKyxm> store = usersKyxmDaoService.getApplyingKyxmList("待审核课题立项");
			return Transform.transfromUsersKyxmList(store, kyxmDaoService);
		}
		//管理员拒绝课题结项申请-审批课题结项模块
		@RequestMapping(value = "/api/userkyxm/rejectApplyingCompletingKyxm/{k_id}", method = RequestMethod.PUT)
		public List<ShowKyxms> rejectApplyingCompletingKyxm(@PathVariable("k_id") int k_id){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			int u_id = visitDao.getVisitUID(username);
			UsersKyxm check = usersKyxmDaoService.getUsersKyxmFromUidKid(u_id, k_id);
			if(check.getK_status()=="待审核课题结项" || "待审核课题结项".equals(check.getK_status())){
				usersKyxmDaoService.operateApplyingKyxm(u_id, k_id, "拒绝课题结项", check.getK_setTime(), check.getK_endTime(), false, true);;
			}
			List<UsersKyxm> store = usersKyxmDaoService.getApplyingKyxmList("待审核课题结项");
			return Transform.transfromUsersKyxmList(store, kyxmDaoService);
		}
		//管理员拒绝课题积分请-审批课题积分模块
		@RequestMapping(value = "/api/userkyxm/rejectApplyingScoreKyxm/{k_id}", method = RequestMethod.PUT)
		public List<ShowKyxms> rejectApplyingScoreKyxm(@PathVariable("k_id") int k_id){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			int u_id = visitDao.getVisitUID(username);
			UsersKyxm check = usersKyxmDaoService.getUsersKyxmFromUidKid(u_id, k_id);
			if(check.getK_status()=="待审核积分申报" || "待审核积分申报".equals(check.getK_status())){
				usersKyxmDaoService.operateApplyingKyxm(u_id, k_id, "拒绝积分申报", check.getK_setTime(), check.getK_endTime(), false, true);;
			}
			List<UsersKyxm> store = usersKyxmDaoService.getApplyingKyxmList("待审核积分申报");
			return Transform.transfromUsersKyxmList(store, kyxmDaoService);
		}	
}

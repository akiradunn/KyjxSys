package com.brave.api;
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
import com.brave.entity.Kyxm;
import com.brave.entity.UsersKyxm;
import com.brave.service.KyxmDaoService;
import com.brave.service.UsersKyxmDaoService;
import com.brave.show.ShowKyxms;
@RestController
public class KyxmApi {
	@Autowired
	private KyxmDaoService kyxmDaoService;
	@Autowired
	private UsersKyxmDaoService usersKyxmDaoService;
	@Autowired
	private VisitDao visitDao;
	//private Jedis jedis = JedisCache.getMyRedis();
	@RequestMapping(value = "/api/kyxm", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<com.brave.entity.Kyxm> getAllKyxm() {
//		String kyxmSet = "kyxmSet";
//		if (jedis.exists(kyxmSet)) {// 如果redis中存在指定的数据则直接从缓存中获取
//			Set<byte[]> byteStore = JedisCache.getMyRedis().smembers("kyxmSet".getBytes());
//			List<Kyxm> kyxmList = new ArrayList<Kyxm>();
//			for (byte[] temp : byteStore) {
//				kyxmList.add((Kyxm) SerializableUtil.deserialize(temp));
//			}
//			return kyxmList;
//		} else {// 如果redis中不存在查询的数据直接从数据库中获取,并更新redis缓存中的值
//			List<Kyxm> list = kyxmDaoService.getAllKyxm();
//			for (Kyxm kyxm : list) {
//				jedis.sadd(kyxmSet.getBytes(), SerializableUtil.serialize(kyxm));
//			}
//			return list;
//		}
		return kyxmDaoService.getAllKyxm();
	}
	@RequestMapping(value = "/api/kyxm/{k_name}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Kyxm> getKyxm(@PathVariable("k_name") String k_name) {
		return kyxmDaoService.getKyxmFromName(k_name);
	}

	@RequestMapping(value = "/api/kyxm/myclass", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<ShowKyxms> getMyKyxm() {// 通过其他渠道用户名进行查询课题
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDao.getVisitUID(username);
		List<UsersKyxm> store = usersKyxmDaoService.getUsersKyxmFromUid(u_id);
		List<ShowKyxms> ret = new LinkedList<ShowKyxms>();
		for(UsersKyxm tempUsersKyxm : store){
			ShowKyxms tempShowKyxms = new ShowKyxms();
			tempShowKyxms.setU_id(tempUsersKyxm.getU_id());
			tempShowKyxms.setK_name(kyxmDaoService.getKyxmFromId(tempUsersKyxm.getK_id()).getK_name());
			tempShowKyxms.setK_score(kyxmDaoService.getKyxmFromId(tempUsersKyxm.getK_id()).getK_score());
			tempShowKyxms.setK_status(tempUsersKyxm.getK_status());
			tempShowKyxms.setK_applyTime(tempUsersKyxm.getK_applyTime());
			tempShowKyxms.setK_setTime(tempUsersKyxm.getK_setTime());
			tempShowKyxms.setK_endTime(tempUsersKyxm.getK_endTime());
			tempShowKyxms.setK_scoreApplied(tempUsersKyxm.isK_scoreApplied());
			tempShowKyxms.setK_completed(tempUsersKyxm.isK_completed());
			ret.add(tempShowKyxms);
		}
		return ret;
	}
}

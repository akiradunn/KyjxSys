package com.brave.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brave.entity.Kyxm;
import com.brave.service.KyxmDaoService;
@RestController
public class KyxmApi {
	@Autowired
	private KyxmDaoService kyxmDaoService;
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
	//用户根据课题名称搜索指定课题-用户课题广场模块
	@RequestMapping(value = "/api/kyxm/{k_name}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Kyxm> getKyxm(@PathVariable("k_name") String k_name) {
		return kyxmDaoService.getKyxmFromName(k_name);
	}
	//管理员通过课题编号查询指定课题-管理员编辑课题模块
	@RequestMapping(value = "/api/kyxm/editKyxm/{k_id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Kyxm getKyxm(@PathVariable("k_id") int k_id) {
		return kyxmDaoService.getKyxmFromId(k_id);
	}
	//管理员编辑课题信息-管理员课题管理模块,include添加和编辑两个功能
	@RequestMapping(value = "/api/kyxm/saveEditKyxm/{markadd}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public List<Kyxm> saveEditKyxm(@PathVariable("markadd") boolean markadd,@RequestParam("k_id") int k_id,@RequestParam("k_name") String k_name,@RequestParam("k_category") String k_category,@RequestParam("k_score") int k_score) {
		if(markadd){//添加状态则插入课题
			System.out.println("add kyxm!");
			kyxmDaoService.insertKyxm(k_id, k_name, k_category, k_score);
		}else{//编辑状态则更新课题
			Kyxm check = kyxmDaoService.getKyxmFromId(k_id);
			if(check!=null){
				System.out.println("update");
				kyxmDaoService.updateKyxm(k_id, k_name, k_category, k_score);
			}
		}
		return kyxmDaoService.getAllKyxm();
	}
	//管理员删除指定课题-管理员课题管理模块
	@RequestMapping(value = "/api/kyxm/deleteKyxm/{k_id}", method = RequestMethod.DELETE)
	public List<Kyxm> deleteVisit(@PathVariable("k_id") int k_id){	
		kyxmDaoService.deleteSpecifiedKyxm(k_id);
		return kyxmDaoService.getAllKyxm();
	}
}

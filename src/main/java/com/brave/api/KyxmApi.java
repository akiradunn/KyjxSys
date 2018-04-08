package com.brave.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "/api/kyxm/{k_name}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Kyxm> getKyxm(@PathVariable("k_name") String k_name) {
		return kyxmDaoService.getKyxmFromName(k_name);
	}
}

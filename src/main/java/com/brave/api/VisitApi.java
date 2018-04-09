package com.brave.api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.brave.entity.Visit;
import com.brave.service.VisitDaoService;
@RestController
public class VisitApi {
	@Autowired
	private VisitDaoService visitDaoService;
	//管理员查看visit列表-用户管理模块
	@RequestMapping(value = "/api/visit/getVisitList", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Visit> getVisitList() {
		return visitDaoService.getVisitList();
	}
	//管理员查看要编辑的visit-用户管理模块
	@RequestMapping(value = "/api/visit/editVisit/{u_id}&&{username}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Visit editVisit(@PathVariable("u_id") int u_id,@PathVariable("username") String username) {
		return visitDaoService.getSpecifiedVisit(u_id, username);
	}
	//管理员保存用户账号信息-用户管理模块
	@RequestMapping(value = "/api/visit/saveEditVisit&&{oldusername}&&{markadd}", method = RequestMethod.PUT)
	public List<Visit> saveEditVisit(@RequestParam("u_id") int u_id,@PathVariable("oldusername") String oldusername,@PathVariable("markadd") boolean markadd,@RequestParam("username") String modifiedusername,@RequestParam("password") String password){
		if(markadd){//添加visit请求状态
			System.out.println(markadd);
			visitDaoService.insertVisit(u_id, modifiedusername, password);
		}else{
			Visit check = visitDaoService.getSpecifiedVisit(u_id, oldusername);
			if(check!=null){//找得到一定是更新信息的状态
				visitDaoService.updateUsers(u_id, oldusername, modifiedusername, password);
			}
		}
		System.out.println("record");
		return visitDaoService.getVisitList();
	}
	//管理员删除用户账号信息-用户管理模块
	@RequestMapping(value = "/api/visit/deleteVisit/{u_id}&&{username}", method = RequestMethod.DELETE)
	public List<Visit> deleteVisit(@PathVariable("u_id") int u_id,@PathVariable("username") String username){	
		Visit check = visitDaoService.getSpecifiedVisit(u_id, username);
		if(check!=null){
			visitDaoService.deleteSpecifiedVisit(u_id, username);
		}
		return visitDaoService.getVisitList();
	}
}

package com.brave.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.brave.entity.Users;
import com.brave.service.UsersDaoService;
import com.brave.service.VisitDaoService;
import com.brave.show.ShowMyInfo;
@RestController
public class AdditionApi {
	@Autowired
	private UsersDaoService usersDaoService;
	@Autowired
	private VisitDaoService visitDaoService;
	@RequestMapping(value = "/api/addition/update_usersvisit", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ShowMyInfo updateUsers(@RequestParam("u_name") String u_name,@RequestParam("u_sex") String u_sex,@RequestParam("username") String modifiedusername,@RequestParam("password") String password) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDaoService.getVisitUID(username);
		usersDaoService.updateUsers(u_id, u_name, u_sex);
		visitDaoService.updateUsers(u_id, username, modifiedusername,password);
		ShowMyInfo ret = new ShowMyInfo();
		Users users = usersDaoService.getUsersFromId(u_id);
		ret.setU_name(users.getU_name());
		ret.setU_identity(users.getU_identity());
		ret.setU_sex(users.getU_sex());
		ret.setU_wholePoints(users.getU_wholePoints());
		ret.setUsername(username);
		ret.setPassword(password);
		return ret;
	}
}

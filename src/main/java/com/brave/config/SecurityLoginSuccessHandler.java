package com.brave.config;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.brave.service.VisitDaoService;
@Component(value = "authenticationSuccessHandler")
public class SecurityLoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private VisitDaoService visitDaoService; 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication arg2)
			throws IOException, ServletException {
		// 登陆成功后要做的事情
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int u_id = visitDaoService.getVisitUID(username);
		String now_users_role = visitDaoService.getSpecifiedVisit(u_id, username).getR_id() == 1 ? "ROLE_ADMIN" : "ROLE_USER";
		response.getWriter().print(now_users_role);
	}
}
